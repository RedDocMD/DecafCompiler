package deep.decaf.low.amd64

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
    }
}

object Unknown : Location()
data class MemLoc(val reg: Register, val offset: NumberOffset) : Location()

enum class AsmArithOp {
    ADD,
    SUB,
    IMUL,
    IDIV,
    CMP
}

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
data class ArithStatement(val op: AsmArithOp, val src: Location, val dest: Location) : Statement()
data class JumpStatement(val type: AsmJumpOp, val target: String) : Statement()
data class MoveStatement(val src: Location, val dest: Location) : Statement()
data class CMoveStatement(val type: AsmCMoveOp, val src: Register, val dest: Register) : Statement()
object ReturnStatement : Statement()
data class CallStatement(val label: String) : Statement()
data class PushStatement(val src: Location) : Statement()
data class PopStatement(val src: Location) : Statement()
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