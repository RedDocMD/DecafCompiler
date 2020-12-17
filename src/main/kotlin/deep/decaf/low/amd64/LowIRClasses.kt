package deep.decaf.low.amd64

import deep.decaf.ir.*
import java.util.*

fun getUUID(): String = UUID.randomUUID().toString().replace("-", "")

sealed class Offset
data class NumberOffset(val offset: Int) : Offset()
data class StringOffset(val name: String) : Offset()
data class ArrayOffset(val name: String, val offsetRegister: Register) : Offset()

sealed class Location
data class ImmediateVal(val num: Int) : Location()
data class Register(val name: String, val offset: Offset?) : Location() {
    companion object {
        fun basePointer() = Register("rbp", null)
        fun stackPointer() = Register("rsp", null)
        fun instructionPointer() = Register("rip", null)
        fun returnRegister() = Register("rax", null)
        fun r10() = Register("r10", null)
        fun r10b() = Register("r10b", null)
        fun r11() = Register("r11", null)
        fun rax() = Register("rax", null)
        fun rdx() = Register("rdx", null)
    }
}

data class Label(val label: String) : Location()
data class MemLoc(val reg: Register, val offset: NumberOffset) : Location()

enum class AsmCMoveOp {
    CMOVE,
    CMOVNE,
    CMOVG,
    CMOVL,
    CMOVGE,
    CMOVLE
}

enum class AsmJumpOp {
    JMP,
    JE,
    JNE
}

sealed class Statement
data class AddStatement(val src: Location, val dest: Location) : Statement()
data class SubStatement(val src: Location, val dest: Location) : Statement()
data class IMulStatement(val src: Location, val dest: Location) : Statement()
data class IDivStatement(val src: Location) : Statement()
data class JumpStatement(val type: AsmJumpOp, val target: String) : Statement()
data class MoveStatement(val src: Location, val dest: Location) : Statement()
data class CMoveStatement(val type: AsmCMoveOp, val src: Register, val dest: Register) : Statement()
object SignedExtendStatement : Statement()
object ReturnStatement : Statement()
data class CallStatement(val label: String) : Statement()
data class PushStatement(val src: Location) : Statement()
data class PopStatement(val src: Location?) : Statement()
object LeaveStatement : Statement()
data class EnterStatement(val size: ImmediateVal) : Statement()

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

fun irBinOpExprToLow(expr: IRBinOpExpr): List<Statement> {
    val statements = mutableListOf<Statement>()

    fun traverse(expr: IRExpr): Location? {
        return when (expr) {
            is IRIntLiteral -> ImmediateVal(expr.lit)
            is IRBoolLiteral -> ImmediateVal(if (expr.lit) 1 else 0)
            is IRMethodCallExpr -> {
                val argLocations = expr.argList.map { traverse(it) }
                argLocations.forEach { statements.add(PushStatement(it!!)) }
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
                statements.add(MoveStatement(leftLocation!!, Register.r10()))
                statements.add(MoveStatement(rightLocation!!, Register.r11()))
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
                    BinOp.LESS -> TODO()
                    BinOp.MORE -> TODO()
                    BinOp.LESS_OR_EQ -> TODO()
                    BinOp.MORE_OR_EQ -> TODO()
                    BinOp.EQ -> TODO()
                    BinOp.NOT_EQ -> TODO()
                    BinOp.AND -> TODO()
                    BinOp.OR -> TODO()
                }
            }
            is IRUnaryOpExpr -> TODO()
            is IRLocationExpression -> TODO()
        }
    }

    return statements
}