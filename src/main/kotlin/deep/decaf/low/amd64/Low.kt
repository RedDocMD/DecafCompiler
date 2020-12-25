package deep.decaf.low.amd64

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

data class MemLoc(val reg: Register, val offset: Offset) : Location() {
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

data class PopInstruction(val src: Location) : Instruction() {
    override fun toString() = "pop ${src ?: ""}"
}

object LeaveInstruction : Instruction() {
    override fun toString() = "leave"
}

data class EnterInstruction(val size: Int) : Instruction() {
    override fun toString() = "enter \$($size*8), $0"
}

data class Block(val label: String?, val instructions: MutableList<Instruction>) {
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        val pref: String = if (label != null) {
            stringBuilder.append("$label:").append('\n')
            "\t"
        } else {
            ""
        }
        for (instruction in instructions) {
            stringBuilder.append(pref).append(instruction.toString()).append('\n')
        }
        return stringBuilder.toString()
    }
}

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