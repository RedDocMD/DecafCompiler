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

sealed class JumpNodes(
    override var next: CFGNode?,
    override var prev: CFGNode?
) : SingleInput, SingleOutput

class BreakNode(next: CFGNode?, prev: CFGNode?) : JumpNodes(next, prev)
class ContinueNode(next: CFGNode?, prev: CFGNode?) : JumpNodes(next, prev)
class ReturnNode(next: CFGNode?, prev: CFGNode?) : JumpNodes(next, prev)

class DeclarationNode(
    override var next: CFGNode?,
    override var prev: CFGNode?,
    val declaration: IRMemberDeclaration
) : SingleInput, SingleOutput

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
        is IRBreakStatement -> {
            val node = BreakNode(null, null)
            CFG(node, node)
        }
        is IRContinueStatement -> {
            val node = ContinueNode(null, null)
            CFG(node, node)
        }
        is IRIfStatement -> {
            val ifCFG = constructCFG(statement.ifBlock)
            val elseCFG = constructCFG(statement.elseBlock)
            if (ifCFG != null && elseCFG != null) {
                val noOp = NoOpNode(listOf(ifCFG.exit, elseCFG.exit), null)
                ifCFG.exit.next = noOp
                elseCFG.exit.next = noOp
                val branchNode = ConditionalNode(
                    null, ifCFG.entry, elseCFG.entry, statement.condition
                )
                CFG(branchNode, noOp)
            } else if (ifCFG != null) {
                val branchNode = ConditionalNode(
                    null, ifCFG.entry, null, statement.condition
                )
                val noOp = NoOpNode(listOf(ifCFG.exit, branchNode), null)
                branchNode.falsePath = noOp
                CFG(branchNode, noOp)
            } else if (elseCFG != null) {
                val branchNode = ConditionalNode(
                    null, null, elseCFG.entry, statement.condition
                )
                val noOp = NoOpNode(listOf(branchNode, elseCFG.exit), null)
                branchNode.truePath = noOp
                CFG(branchNode, noOp)
            } else {
                val branchNode = ConditionalNode(
                    null, null, null, statement.condition)
                val noOp = NoOpNode(listOf(branchNode, branchNode), null)
                branchNode.falsePath = noOp
                branchNode.truePath = noOp
                CFG(branchNode, noOp)
            }
        }
        is IRForStatement -> TODO()
        is IRReturnStatement -> TODO()
        is IRInvokeStatement -> TODO()
        is IRBlockStatement -> TODO()
    }
}

fun constructCFG(block: IRBlock?): CFG? {
    when {
        block == null -> return null
        block.fieldDeclarations.isNotEmpty() -> {
            val fields = block.fieldDeclarations
            var node = DeclarationNode(null, null, fields[0])
            val entry = node
            var pt = node
            for (field in fields.subList(1, fields.size)) {
                node = DeclarationNode(null, pt, field)
                pt.next = node
                pt = node
            }
            var npt: SingleOutput = pt
            for (statement in block.statements) {
                val cfg = constructCFG(statement)
                npt.next = cfg.entry
                cfg.entry.prev = npt
                npt = cfg.exit
            }
            return CFG(entry, npt)
        }
        block.statements.isNotEmpty() -> {
            val statements = block.statements
            var cfg = constructCFG(statements[0])
            val entry = cfg.entry
            var pt = cfg.exit
            for (statement in statements.subList(1, statements.size)) {
                cfg = constructCFG(statement)
                pt.next = cfg.entry
                cfg.entry.prev = pt
                pt = cfg.exit
            }
            return CFG(entry, pt)
        }
        else -> {
            return null
        }
    }
}

