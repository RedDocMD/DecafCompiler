package deep.decaf.low.amd64

import deep.decaf.ir.*

interface CFGNode

interface SingleInput : CFGNode {
    var prev: CFGNode?
}

interface SingleOutput : CFGNode {
    var next: CFGNode?
}

class RegularNode(
    override var prev: CFGNode?,
    override var next: CFGNode?
) : SingleInput, SingleOutput {
    val statements = mutableListOf<IRStatement>()
}

class ConditionalNode(
    override var prev: CFGNode?,
    var truePath: CFGNode?,
    var falsePath: CFGNode?,
    val condition: IRExpr
) : SingleInput

class NoOpNode(
    var prevs: List<CFGNode>,
    override var next: CFGNode?
) : SingleOutput

data class CFG(val entry: SingleInput, val exit: SingleOutput)

fun constructCFG(statement: IRStatement): CFG {
    return when (statement) {
        is IRAssignStatement -> {
            val node = RegularNode(null, null)
            node.statements.add(statement)
            CFG(node, node)
        }
        is IRBreakStatement -> TODO()
        is IRContinueStatement -> TODO()
        is IRIfStatement -> {
            val ifCFG = constructCFG(statement.ifBlock)
            if (statement.elseBlock != null) {
                val elseCFG = constructCFG(statement.elseBlock)
                val noOP = NoOpNode(listOf(ifCFG.exit, elseCFG.exit), null)
                ifCFG.exit.next = noOP
                elseCFG.exit.next = noOP
                val branchNode = ConditionalNode(null, ifCFG.entry, elseCFG.exit, statement.condition)
                CFG(branchNode, noOP)
            } else {
                val branchNode = ConditionalNode(null, ifCFG.entry, null, statement.condition)
                val noOP = NoOpNode(listOf(ifCFG.exit, branchNode), null)
                branchNode.falsePath = noOP
                CFG(branchNode, noOP)
            }
        }
        is IRForStatement -> TODO()
        is IRReturnStatement -> TODO()
        is IRInvokeStatement -> TODO()
        is IRBlockStatement -> TODO()
    }
}

fun constructCFG(block: IRBlock): CFG {
    TODO()
}
