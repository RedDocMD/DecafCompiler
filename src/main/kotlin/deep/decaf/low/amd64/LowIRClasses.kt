package deep.decaf.low.amd64

import deep.decaf.ir.*
import java.lang.IllegalStateException
import java.util.*

fun getUUID(): String = UUID.randomUUID().toString().replace("-", "")

sealed class Offset
data class NumberOffset(val offset: Int) : Offset() {
    override fun toString(): String {
        return "$offset"
    }
}

data class StringOffset(val name: String) : Offset() {
    override fun toString(): String {
        return name
    }
}


sealed class Location
data class ImmediateVal(val num: Int) : Location() {
    override fun toString(): String {
        return "\$$num"
    }
}

data class Register(val name: String) : Location() {
    companion object {
        fun basePointer() = Register("rbp")
        fun stackPointer() = Register("rsp")
        fun instructionPointer() = Register("rip")
        fun returnRegister() = Register("rax")
        fun r10() = Register("r10")
        fun r10b() = Register("r10b")
        fun r11() = Register("r11")
        fun rax() = Register("rax")
        fun rdx() = Register("rdx")
    }

    override fun toString(): String {
        return "%$name"
    }
}

data class Label(val label: String) : Location() {
    override fun toString(): String {
        return label
    }
}

data class MemLoc(val reg: Register, val offset: NumberOffset) : Location() {
    override fun toString(): String {
        return "$offset($reg)"
    }
}

data class ArrayAsm(val name: String, val offsetRegister: Register) : Location() {
    override fun toString(): String {
        return "$name(, $offsetRegister, 8)"
    }
}

enum class AsmCMoveOp {
    CMOVE,
    CMOVNE,
    CMOVG,
    CMOVL,
    CMOVGE,
    CMOVLE;

    override fun toString(): String {
        return when (this) {
            CMOVE -> "cmove"
            CMOVNE -> "cmovne"
            CMOVG -> "cmovg"
            CMOVL -> "cmovl"
            CMOVGE -> "cmovge"
            CMOVLE -> "cmovle"
        }
    }
}

enum class AsmJumpOp {
    JMP,
    JE,
    JNE;

    override fun toString(): String {
        return when (this) {
            JMP -> "jmp"
            JE -> "je"
            JNE -> "jne"
        }
    }
}

enum class SetType {
    SETE,
    SETNE,
    SETG,
    SETL,
    SETGE,
    SETLE;

    override fun toString(): String {
        return when (this) {
            SETE -> "sete"
            SETNE -> "setne"
            SETG -> "setg"
            SETL -> "setl"
            SETGE -> "setge"
            SETLE -> "setle"
        }
    }
}

sealed class Instruction
data class AddInstruction(val src: Location, val dest: Location) : Instruction() {
    override fun toString(): String {
        return "add $src, $dest"
    }
}

data class SubInstruction(val src: Location, val dest: Location) : Instruction() {
    override fun toString(): String {
        return "sub $src, $dest"
    }
}

data class IMulInstruction(val src: Location, val dest: Location) : Instruction() {
    override fun toString(): String {
        return "imul $src, $dest"
    }
}

data class IDivInstruction(val src: Location) : Instruction() {
    override fun toString(): String {
        return "idivq $src"
    }
}

data class CmpInstruction(val src: Location, val dest: Location) : Instruction() {
    override fun toString(): String {
        return "cmp $src, $dest"
    }
}

data class AndInstruction(val src: Location, val dest: Location) : Instruction() {
    override fun toString(): String {
        return "and $src, $dest"
    }
}

data class OrInstruction(val src: Location, val dest: Location) : Instruction() {
    override fun toString(): String {
        return "or $src, $dest"
    }
}

data class NotInstruction(val src: Location) : Instruction() {
    override fun toString(): String {
        return "not $src"
    }
}

data class NegInstruction(val src: Location) : Instruction() {
    override fun toString(): String {
        return "nge $src"
    }
}

data class JumpInstruction(val type: AsmJumpOp, val target: String) : Instruction() {
    override fun toString(): String {
        return "$type $target"
    }
}

data class MoveInstruction(val src: Location, val dest: Location) : Instruction() {
    override fun toString(): String {
        return "mov $src, $dest"
    }
}

data class CMoveInstruction(val type: AsmCMoveOp, val src: Register, val dest: Register) : Instruction() {
    override fun toString(): String {
        return "$type $src $dest"
    }
}

data class SetInstruction(val type: SetType, val reg: Register) : Instruction() {
    override fun toString(): String {
        return "$type $reg"
    }
}

object SignedExtendInstruction : Instruction() {
    override fun toString() = "cqto"
}

object ReturnInstruction : Instruction() {
    override fun toString() = "ret"
}

data class CallInstruction(val label: String) : Instruction() {
    override fun toString() = "call $label"
}

data class PushInstruction(val src: Location) : Instruction() {
    override fun toString() = "push $src"
}

data class PopInstruction(val src: Location?) : Instruction() {
    override fun toString() = "pop ${src ?: ""}"
}

object LeaveInstruction : Instruction() {
    override fun toString() = "leave"
}

data class EnterInstruction(val size: ImmediateVal) : Instruction() {
    override fun toString() = "enter \$($size*8), $0"
}

data class Block(val label: String?, val instructions: List<Instruction>)

data class Method(
    var stackSize: Int,
    var formalParams: Map<String, Location>,
    var localVars: Map<String, Location>,
    var blocks: List<Block>,
    var program: Program
)

data class Program(
    var globalVars: List<String>,
    var globalArrays: List<String>,
    var methods: List<Method>
)

fun irExprToLow(expr: IRExpr): List<Instruction> {
    val instructions = mutableListOf<Instruction>()

    fun traverse(expr: IRExpr): Location {
        return when (expr) {
            is IRIntLiteral -> ImmediateVal(expr.lit)
            is IRBoolLiteral -> ImmediateVal(if (expr.lit) 1 else 0)
            is IRMethodCallExpr -> {
                val argLocations = expr.argList.map { traverse(it) }
                argLocations.forEach { instructions.add(PushInstruction(it)) }
                instructions.add(CallInstruction(expr.name))
                for (i in 1..expr.argList.size) {
                    instructions.add(PopInstruction(null))
                }
                Register.returnRegister()
            }
            is IRCallOutExpr -> TODO()
            is IRBinOpExpr -> {
                val leftLocation = traverse(expr.left)
                val rightLocation = traverse(expr.right)
                instructions.add(MoveInstruction(leftLocation, Register.r10()))
                instructions.add(MoveInstruction(rightLocation, Register.r11()))
                when (expr.op) {
                    BinOp.ADD -> {
                        instructions.add(AddInstruction(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.SUBTRACT -> {
                        instructions.add(SubInstruction(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MULTIPLY -> {
                        instructions.add(IMulInstruction(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.DIVIDE -> {
                        instructions.add(MoveInstruction(Register.r10(), Register.rax()))
                        instructions.add(SignedExtendInstruction)
                        instructions.add(IDivInstruction(Register.r11()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.rax(), tmp))
                        tmp
                    }
                    BinOp.REMAINDER -> {
                        instructions.add(MoveInstruction(Register.r10(), Register.rax()))
                        instructions.add(SignedExtendInstruction)
                        instructions.add(IDivInstruction(Register.r11()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.rdx(), tmp))
                        tmp
                    }
                    BinOp.LESS -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETL, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MORE -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETG, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.LESS_OR_EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETLE, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MORE_OR_EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETGE, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETE, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.NOT_EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETNE, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.AND -> {
                        instructions.add(AndInstruction(Register.r10(), Register.r11()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r11(), tmp))
                        tmp
                    }
                    BinOp.OR -> {
                        instructions.add(OrInstruction(Register.r10(), Register.r11()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r11(), tmp))
                        tmp
                    }
                }
            }
            is IRUnaryOpExpr -> {
                val loc = traverse(expr.expr)
                when (expr.op) {
                    UnaryOp.MINUS -> {
                        instructions.add(MoveInstruction(loc, Register.r10()))
                        instructions.add(NegInstruction(Register.r10()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    UnaryOp.NOT -> {
                        instructions.add(MoveInstruction(loc, Register.r10()))
                        instructions.add(NotInstruction(Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                }
            }
            is IRLocationExpression -> {
                when (val location = expr.location) {
                    is IRIDLocation -> Label(location.name)
                    is IRArrayLocation -> {
                        val index = traverse(location.indexExpr)
                        instructions.add(MoveInstruction(index, Register.r10()))
                        ArrayAsm(location.name, Register.r10())
                    }
                }
            }
        }
    }

    traverse(expr)
    return instructions
}

fun irStatementToLow(statement: IRStatement): List<Instruction> {
    val instructions = mutableListOf<Instruction>()

    when (statement) {
        is IRDirectAssignStatement -> {
            val exprInstructions = irExprToLow(statement.expr)
            instructions.addAll(exprInstructions)
            val exprValLocation = (exprInstructions.last() as MoveInstruction).dest
            val rhs = when (val location = statement.location) {
                is IRIDLocation -> Label(location.name)
                is IRArrayLocation -> {
                    val indexInstructions = irExprToLow(location.indexExpr)
                    instructions.addAll(indexInstructions)
                    val index = (indexInstructions.last() as MoveInstruction).dest
                    instructions.add(MoveInstruction(index, Register.r10()))
                    ArrayAsm(location.name, Register.r10())
                }
            }
            instructions.add(MoveInstruction(exprValLocation, Register.r10()))
            instructions.add(MoveInstruction(Register.r10(), rhs))
        }
        is IRIncrementStatement -> {
            val exprInstructions = irExprToLow(statement.expr)
            instructions.addAll(exprInstructions)
            val exprValLocation = (exprInstructions.last() as MoveInstruction).dest
            val rhs = when (val location = statement.location) {
                is IRIDLocation -> Label(location.name)
                is IRArrayLocation -> {
                    val indexInstructions = irExprToLow(location.indexExpr)
                    instructions.addAll(indexInstructions)
                    val index = (indexInstructions.last() as MoveInstruction).dest
                    instructions.add(MoveInstruction(index, Register.r10()))
                    ArrayAsm(location.name, Register.r10())
                }
            }
            instructions.add(MoveInstruction(rhs, Register.r10()))
            instructions.add(AddInstruction(exprValLocation, Register.r10()))
            instructions.add(MoveInstruction(Register.r10(), rhs))
        }
        is IRDecrementStatement -> {
            val exprInstructions = irExprToLow(statement.expr)
            instructions.addAll(exprInstructions)
            val exprValLocation = (exprInstructions.last() as MoveInstruction).dest
            val rhs = when (val location = statement.location) {
                is IRIDLocation -> Label(location.name)
                is IRArrayLocation -> {
                    val indexInstructions = irExprToLow(location.indexExpr)
                    instructions.addAll(indexInstructions)
                    val index = (indexInstructions.last() as MoveInstruction).dest
                    instructions.add(MoveInstruction(index, Register.r10()))
                    ArrayAsm(location.name, Register.r10())
                }
            }
            instructions.add(MoveInstruction(rhs, Register.r10()))
            instructions.add(SubInstruction(exprValLocation, Register.r10()))
            instructions.add(MoveInstruction(Register.r10(), rhs))
        }
        is IRInvokeStatement -> {
            val invokeInstructions = irExprToLow(statement.expr)
            instructions.addAll(invokeInstructions)
        }
        else -> throw IllegalStateException("shouldn't be calling for this statement: ${irToString(statement)}")
    }

    return instructions
}