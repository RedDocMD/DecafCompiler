package deep.decaf.low.amd64

import deep.decaf.ir.*
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
    val statements = mutableListOf<Instruction>()

    fun traverse(expr: IRExpr): Location {
        return when (expr) {
            is IRIntLiteral -> ImmediateVal(expr.lit)
            is IRBoolLiteral -> ImmediateVal(if (expr.lit) 1 else 0)
            is IRMethodCallExpr -> {
                val argLocations = expr.argList.map { traverse(it) }
                argLocations.forEach { statements.add(PushInstruction(it)) }
                statements.add(CallInstruction(expr.name))
                for (i in 1..expr.argList.size) {
                    statements.add(PopInstruction(null))
                }
                Register.returnRegister()
            }
            is IRCallOutExpr -> TODO()
            is IRBinOpExpr -> {
                val leftLocation = traverse(expr.left)
                val rightLocation = traverse(expr.right)
                statements.add(MoveInstruction(leftLocation, Register.r10()))
                statements.add(MoveInstruction(rightLocation, Register.r11()))
                when (expr.op) {
                    BinOp.ADD -> {
                        statements.add(AddInstruction(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.SUBTRACT -> {
                        statements.add(SubInstruction(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MULTIPLY -> {
                        statements.add(IMulInstruction(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.DIVIDE -> {
                        statements.add(MoveInstruction(Register.r10(), Register.rax()))
                        statements.add(SignedExtendInstruction)
                        statements.add(IDivInstruction(Register.r11()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.rax(), tmp))
                        tmp
                    }
                    BinOp.REMAINDER -> {
                        statements.add(MoveInstruction(Register.r10(), Register.rax()))
                        statements.add(SignedExtendInstruction)
                        statements.add(IDivInstruction(Register.r11()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.rdx(), tmp))
                        tmp
                    }
                    BinOp.LESS -> {
                        statements.add(CmpInstruction(Register.r11(), Register.r10()))
                        statements.add(SetInstruction(SetType.SETL, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MORE -> {
                        statements.add(CmpInstruction(Register.r11(), Register.r10()))
                        statements.add(SetInstruction(SetType.SETG, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.LESS_OR_EQ -> {
                        statements.add(CmpInstruction(Register.r11(), Register.r10()))
                        statements.add(SetInstruction(SetType.SETLE, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MORE_OR_EQ -> {
                        statements.add(CmpInstruction(Register.r11(), Register.r10()))
                        statements.add(SetInstruction(SetType.SETGE, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.EQ -> {
                        statements.add(CmpInstruction(Register.r11(), Register.r10()))
                        statements.add(SetInstruction(SetType.SETE, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.NOT_EQ -> {
                        statements.add(CmpInstruction(Register.r11(), Register.r10()))
                        statements.add(SetInstruction(SetType.SETNE, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.AND -> {
                        statements.add(AndInstruction(Register.r10(), Register.r11()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r11(), tmp))
                        tmp
                    }
                    BinOp.OR -> {
                        statements.add(OrInstruction(Register.r10(), Register.r11()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r11(), tmp))
                        tmp
                    }
                }
            }
            is IRUnaryOpExpr -> {
                val loc = traverse(expr.expr)
                when (expr.op) {
                    UnaryOp.MINUS -> {
                        statements.add(MoveInstruction(loc, Register.r10()))
                        statements.add(NegInstruction(Register.r10()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    UnaryOp.NOT -> {
                        statements.add(MoveInstruction(loc, Register.r10()))
                        statements.add(NotInstruction(Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                }
            }
            is IRLocationExpression -> {
                when (val location = expr.location) {
                    is IRIDLocation -> Label(location.name)
                    is IRArrayLocation -> {
                        val index = traverse(location.indexExpr)
                        statements.add(MoveInstruction(index, Register.r10()))
                        ArrayAsm(location.name, Register.r10())
                    }
                }
            }
        }
    }

    traverse(expr)
    return statements
}

fun irStatementToLower(statements: IRStatement) {

}