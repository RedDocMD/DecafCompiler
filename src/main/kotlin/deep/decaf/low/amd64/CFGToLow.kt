package deep.decaf.low.amd64

import deep.decaf.low.*
import java.lang.IllegalStateException

fun methodCFGToLow(name: String, cfg: CFG, blockCountStart: Int = 0, info: AsmProgramInfo) {
    val blocks = mutableListOf<Block>()
    var stackSize = 0
    var blockCounts = blockCountStart

    val visited = mutableMapOf<String, Boolean>()
    fun convert(node: CFGNode, block: Block) {
        val done = visited[node.uuid] ?: false
        if (!done) {
            visited[node.uuid] = true
            when (node) {
                is RegularNode -> {
                    for (statement in node.statements) {
                        block.instructions.addAll(irStatementToLow(statement, info))
                    }
                    if (node.next != null) {
                        convert(node.next!!, block)
                    }
                }
                is BlockEntryNode -> {
                    if (node.next != null) {
                        convert(node.next!!, block)
                    }
                }
                is BlockExitNode -> {
                    if (node.next != null) {
                        convert(node.next!!, block)
                    }
                }
                is ConditionalNode -> {
                    val instructions = irExprToLow(node.condition, info)
                    val res = (instructions.last() as MoveInstruction).dest
                    blockCounts++
                    val newLabel = "BLOCK$blockCounts"
                    block.instructions.add(MoveInstruction(res, Register.r10()))
                    block.instructions.add(CmpInstruction(Register.r10(), ImmediateVal(1)))
                    block.instructions.add(JumpInstruction(AsmJumpOp.JNE, newLabel))
                    if (node.truePath != null) {
                        convert(node.truePath!!, block)
                    }
                    val newBlock = Block(newLabel, mutableListOf())
                    if (node.falsePath != null) {
                        convert(node.falsePath!!, newBlock)
                    }
                    blocks.add(newBlock)
                }
                else -> throw IllegalStateException("wrong CFG")
            }
        }
    }
}