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

sealed class Statement
data class AddStatement(val src: Location, val dest: Location) : Statement() {
    override fun toString(): String {
        return "add $src, $dest"
    }
}

data class SubStatement(val src: Location, val dest: Location) : Statement() {
    override fun toString(): String {
        return "sub $src, $dest"
    }
}

data class IMulStatement(val src: Location, val dest: Location) : Statement() {
    override fun toString(): String {
        return "imul $src, $dest"
    }
}

data class IDivStatement(val src: Location) : Statement() {
    override fun toString(): String {
        return "idivq $src"
    }
}

data class CmpStatement(val src: Location, val dest: Location) : Statement() {
    override fun toString(): String {
        return "cmp $src, $dest"
    }
}

data class AndStatement(val src: Location, val dest: Location) : Statement() {
    override fun toString(): String {
        return "and $src, $dest"
    }
}

data class OrStatement(val src: Location, val dest: Location) : Statement() {
    override fun toString(): String {
        return "or $src, $dest"
    }
}

data class NotStatement(val src: Location) : Statement() {
    override fun toString(): String {
        return "not $src"
    }
}

data class NegStatement(val src: Location) : Statement() {
    override fun toString(): String {
        return "nge $src"
    }
}

data class JumpStatement(val type: AsmJumpOp, val target: String) : Statement() {
    override fun toString(): String {
        return "$type $target"
    }
}

data class MoveStatement(val src: Location, val dest: Location) : Statement() {
    override fun toString(): String {
        return "mov $src, $dest"
    }
}

data class CMoveStatement(val type: AsmCMoveOp, val src: Register, val dest: Register) : Statement() {
    override fun toString(): String {
        return "$type $src $dest"
    }
}

data class SetStatement(val type: SetType, val reg: Register) : Statement() {
    override fun toString(): String {
        return "$type $reg"
    }
}

object SignedExtendStatement : Statement() {
    override fun toString() = "cqto"
}

object ReturnStatement : Statement() {
    override fun toString() = "ret"
}

data class CallStatement(val label: String) : Statement() {
    override fun toString() = "call $label"
}

data class PushStatement(val src: Location) : Statement() {
    override fun toString() = "push $src"
}

data class PopStatement(val src: Location?) : Statement() {
    override fun toString() = "pop ${src ?: ""}"
}

object LeaveStatement : Statement() {
    override fun toString() = "leave"
}

data class EnterStatement(val size: ImmediateVal) : Statement() {
    override fun toString() = "enter \$($size*8), $0"
}

data class Block(val label: String?, val statements: List<Statement>)

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

fun irExprToLow(expr: IRExpr): List<Statement> {
    val statements = mutableListOf<Statement>()

    fun traverse(expr: IRExpr): Location {
        return when (expr) {
            is IRIntLiteral -> ImmediateVal(expr.lit)
            is IRBoolLiteral -> ImmediateVal(if (expr.lit) 1 else 0)
            is IRMethodCallExpr -> {
                val argLocations = expr.argList.map { traverse(it) }
                argLocations.forEach { statements.add(PushStatement(it)) }
                statements.add(CallStatement(expr.name))
                for (i in 1..expr.argList.size) {
                    statements.add(PopStatement(null))
                }
                Register.returnRegister()
            }
            is IRCallOutExpr -> TODO()
            is IRBinOpExpr -> {
                val leftLocation = traverse(expr.left)
                val rightLocation = traverse(expr.right)
                statements.add(MoveStatement(leftLocation, Register.r10()))
                statements.add(MoveStatement(rightLocation, Register.r11()))
                when (expr.op) {
                    BinOp.ADD -> {
                        statements.add(AddStatement(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.SUBTRACT -> {
                        statements.add(SubStatement(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MULTIPLY -> {
                        statements.add(IMulStatement(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.DIVIDE -> {
                        statements.add(MoveStatement(Register.r10(), Register.rax()))
                        statements.add(SignedExtendStatement)
                        statements.add(IDivStatement(Register.r11()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.rax(), tmp))
                        tmp
                    }
                    BinOp.REMAINDER -> {
                        statements.add(MoveStatement(Register.r10(), Register.rax()))
                        statements.add(SignedExtendStatement)
                        statements.add(IDivStatement(Register.r11()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.rdx(), tmp))
                        tmp
                    }
                    BinOp.LESS -> {
                        statements.add(CmpStatement(Register.r11(), Register.r10()))
                        statements.add(SetStatement(SetType.SETL, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MORE -> {
                        statements.add(CmpStatement(Register.r11(), Register.r10()))
                        statements.add(SetStatement(SetType.SETG, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.LESS_OR_EQ -> {
                        statements.add(CmpStatement(Register.r11(), Register.r10()))
                        statements.add(SetStatement(SetType.SETLE, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MORE_OR_EQ -> {
                        statements.add(CmpStatement(Register.r11(), Register.r10()))
                        statements.add(SetStatement(SetType.SETGE, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.EQ -> {
                        statements.add(CmpStatement(Register.r11(), Register.r10()))
                        statements.add(SetStatement(SetType.SETE, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.NOT_EQ -> {
                        statements.add(CmpStatement(Register.r11(), Register.r10()))
                        statements.add(SetStatement(SetType.SETNE, Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.AND -> {
                        statements.add(AndStatement(Register.r10(), Register.r11()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r11(), tmp))
                        tmp
                    }
                    BinOp.OR -> {
                        statements.add(OrStatement(Register.r10(), Register.r11()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r11(), tmp))
                        tmp
                    }
                }
            }
            is IRUnaryOpExpr -> {
                val loc = traverse(expr.expr)
                when (expr.op) {
                    UnaryOp.MINUS -> {
                        statements.add(MoveStatement(loc, Register.r10()))
                        statements.add(NegStatement(Register.r10()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r10(), tmp))
                        tmp
                    }
                    UnaryOp.NOT -> {
                        statements.add(MoveStatement(loc, Register.r10()))
                        statements.add(NotStatement(Register.r10b()))
                        val tmp = Label(getUUID())
                        statements.add(MoveStatement(Register.r10(), tmp))
                        tmp
                    }
                }
            }
            is IRLocationExpression -> {
                when (val location = expr.location) {
                    is IRIDLocation -> Label(location.name)
                    is IRArrayLocation -> {
                        val index = traverse(location.indexExpr)
                        statements.add(MoveStatement(index, Register.r10()))
                        ArrayAsm(location.name, Register.r10())
                    }
                }
            }
        }
    }

    traverse(expr)
    return statements
}