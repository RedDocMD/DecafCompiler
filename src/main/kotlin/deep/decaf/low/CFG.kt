package deep.decaf.low

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
    val prevs: MutableList<CFGNode>,
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
                val noOp = NoOpNode(mutableListOf(ifCFG.exit, elseCFG.exit), null)
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
                val noOp = NoOpNode(mutableListOf(ifCFG.exit, branchNode), null)
                branchNode.falsePath = noOp
                CFG(branchNode, noOp)
            } else if (elseCFG != null) {
                val branchNode = ConditionalNode(
                    null, null, elseCFG.entry, statement.condition
                )
                val noOp = NoOpNode(mutableListOf(branchNode, elseCFG.exit), null)
                branchNode.truePath = noOp
                CFG(branchNode, noOp)
            } else {
                val branchNode = ConditionalNode(
                    null, null, null, statement.condition
                )
                val noOp = NoOpNode(mutableListOf(branchNode, branchNode), null)
                branchNode.falsePath = noOp
                branchNode.truePath = noOp
                CFG(branchNode, noOp)
            }
        }
        is IRForStatement -> {
            val declaration = IRVarDeclaration(Type.INT, statement.loopVar, statement.pos)
            val loopVar = IRIDLocation(statement.loopVar, statement.pos)
            val init = IRDirectAssignStatement(loopVar, statement.initExpr, statement.pos)
            val termination = IRBinOpExpr(
                IRLocationExpression(loopVar, statement.pos),
                statement.condition, BinOp.NOT_EQ, statement.pos
            )
            val declarationNode = DeclarationNode(null, null,  declaration)
            val initNode = RegularNode(null, null)
            initNode.statements.add(init)
            val conditionNode = ConditionalNode(null, null, null, termination)
            val noOp = NoOpNode(mutableListOf(), null)
            val blockCFG = constructCFG(statement.body)

            declarationNode.next = initNode
            initNode.prev = declarationNode
            initNode.next = noOp
            noOp.prevs.add(initNode)
            noOp.next = conditionNode
            conditionNode.prev = noOp
            if (blockCFG != null) {
                conditionNode.truePath = blockCFG.entry
                blockCFG.entry.prev = conditionNode
                blockCFG.exit.next = noOp
                noOp.prevs.add(blockCFG.exit)
            } else {
                conditionNode.truePath = noOp
                noOp.prevs.add(conditionNode)
            }

            val exitNoOp = NoOpNode(mutableListOf(conditionNode), null)
            conditionNode.falsePath = exitNoOp

            CFG(declarationNode, exitNoOp)
        }
        is IRReturnStatement -> {
            val node = ReturnNode(null, null)
            CFG(node, node)
        }
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
        else -> return null
    }
}

