package deep.decaf.low

import deep.decaf.ir.*
import java.util.*

interface CFGNode {
    val uuid: String
}

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
    override val uuid = UUID.randomUUID().toString().replace("-", "")
}

class ConditionalNode(
    override var prev: CFGNode?,
    var truePath: CFGNode?,
    var falsePath: CFGNode?,
    val condition: IRExpr,
    val type: ConditionalNodeType
) : SingleInput {
    override val uuid = UUID.randomUUID().toString().replace("-", "")
}

sealed class ConditionalNodeType
object IfElse : ConditionalNodeType()
data class For(val id: Int) : ConditionalNodeType()

sealed class JumpNodes(
    override var next: CFGNode?,
    override var prev: CFGNode?
) : SingleInput, SingleOutput {
    override val uuid = UUID.randomUUID().toString().replace("-", "")
}

class BreakNode(next: CFGNode?, prev: CFGNode?, val loopId: Int) : JumpNodes(next, prev)
class ContinueNode(next: CFGNode?, prev: CFGNode?, val loopId: Int) : JumpNodes(next, prev)
class ReturnNode(next: CFGNode?, prev: CFGNode?, val expr: IRExpr?) : JumpNodes(next, prev)

class DeclarationNode(
    override var next: CFGNode?,
    override var prev: CFGNode?,
    val declaration: IRMemberDeclaration
) : SingleInput, SingleOutput {
    override val uuid = UUID.randomUUID().toString().replace("-", "")
}

sealed class NoOpNode(
    val prevs: MutableList<CFGNode>,
    override var next: CFGNode?
) : SingleOutput {
    override val uuid = UUID.randomUUID().toString().replace("-", "")
}

class EntryNoOpNode(
    prevs: MutableList<CFGNode>,
    next: CFGNode?,
    type: For
) : NoOpNode(prevs, next)

class ExitNoOpNode(
    prevs: MutableList<CFGNode>,
    next: CFGNode?,
    type: ConditionalNodeType
) : NoOpNode(prevs, next)

data class CFG(val entry: SingleInput, val exit: SingleOutput)

class CFGCreationInfo {
    private val forLoopStack = mutableListOf<Int>()
    private var loopIndex = 0
    val lastMethod = ""

    fun enterLoop() {
        forLoopStack.add(loopIndex)
        loopIndex++
    }

    fun leaveLoop() {
        forLoopStack.removeAt(forLoopStack.size - 1)
    }

    fun loopId(): Int {
        return forLoopStack[forLoopStack.size - 1]
    }
}

fun constructCFG(statement: IRStatement, info: CFGCreationInfo): CFG {
    return when (statement) {
        is IRAssignStatement -> {
            val node = RegularNode(null, null)
            node.statements.add(statement)
            CFG(node, node)
        }
        is IRBreakStatement -> {
            val node = BreakNode(null, null, info.loopId())
            CFG(node, node)
        }
        is IRContinueStatement -> {
            val node = ContinueNode(null, null, info.loopId())
            CFG(node, node)
        }
        is IRIfStatement -> {
            val ifCFG = constructCFG(statement.ifBlock, info)
            val elseCFG = constructCFG(statement.elseBlock, info)
            if (ifCFG != null && elseCFG != null) {
                val noOp = ExitNoOpNode(
                    mutableListOf(ifCFG.exit, elseCFG.exit), null, IfElse)
                ifCFG.exit.next = noOp
                elseCFG.exit.next = noOp
                val branchNode = ConditionalNode(
                    null, ifCFG.entry, elseCFG.entry, statement.condition, IfElse
                )
                CFG(branchNode, noOp)
            } else if (ifCFG != null) {
                val branchNode = ConditionalNode(
                    null, ifCFG.entry, null, statement.condition, IfElse
                )
                val noOp = ExitNoOpNode(
                    mutableListOf(ifCFG.exit, branchNode), null, IfElse)
                ifCFG.exit.next = noOp
                branchNode.falsePath = noOp
                CFG(branchNode, noOp)
            } else if (elseCFG != null) {
                val branchNode = ConditionalNode(
                    null, null, elseCFG.entry, statement.condition, IfElse
                )
                val noOp = ExitNoOpNode(
                    mutableListOf(branchNode, elseCFG.exit), null, IfElse)
                elseCFG.exit.next = noOp
                branchNode.truePath = noOp
                CFG(branchNode, noOp)
            } else {
                val branchNode = ConditionalNode(
                    null, null, null, statement.condition, IfElse
                )
                val noOp = ExitNoOpNode(
                    mutableListOf(branchNode, branchNode), null, IfElse)
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
            val declarationNode = DeclarationNode(null, null, declaration)
            val initNode = RegularNode(null, null)
            initNode.statements.add(init)
            info.enterLoop()
            val conditionNode = ConditionalNode(
                null, null, null, termination, For(info.loopId()))
            val noOp = EntryNoOpNode(mutableListOf(), null, For(info.loopId()))
            val blockCFG = constructCFG(statement.body, info)

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

            val exitNoOp = ExitNoOpNode(mutableListOf(conditionNode), null, For(info.loopId()))
            conditionNode.falsePath = exitNoOp
            info.leaveLoop()

            CFG(declarationNode, exitNoOp)
        }
        is IRReturnStatement -> {
            val node = ReturnNode(null, null, statement.expr)
            CFG(node, node)
        }
        is IRInvokeStatement -> {
            val node = RegularNode(null, null)
            node.statements.add(statement)
            CFG(node, node)
        }
        is IRBlockStatement -> {
            constructCFG(statement.block, info)!!
        }
    }
}

fun constructCFG(block: IRBlock?, info: CFGCreationInfo): CFG? {
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
                val cfg = constructCFG(statement, info)
                npt.next = cfg.entry
                cfg.entry.prev = npt
                npt = cfg.exit
            }
            return CFG(entry, npt)
        }
        block.statements.isNotEmpty() -> {
            val statements = block.statements
            var cfg = constructCFG(statements[0], info)
            val entry = cfg.entry
            var pt = cfg.exit
            for (statement in statements.subList(1, statements.size)) {
                cfg = constructCFG(statement, info)
                pt.next = cfg.entry
                cfg.entry.prev = pt
                pt = cfg.exit
            }
            return CFG(entry, pt)
        }
        else -> return null
    }
}

private fun cfgNodeLabel(node: CFGNode): String {
    return when (node) {
        is RegularNode -> node.statements.joinToString("\n") { irToString(it) }
        is ConditionalNode -> irToString(node.condition)
        is BreakNode -> "break"
        is ContinueNode -> "continue"
        is ReturnNode -> "return" + if (node.expr != null) irToString(node.expr) else ""
        is DeclarationNode -> irToString(node.declaration)
        is EntryNoOpNode -> "Xin"
        is ExitNoOpNode -> "Xout"
        else -> throw IllegalArgumentException("I don't know this variant of node")
    }
}

fun dotFileFromCFG(cfg: CFG): String {
    val done = mutableMapOf<String, Boolean>()
    val nodeProps = mutableListOf<String>()
    val links = mutableListOf<String>()

    fun visit(node: CFGNode) {
        val isDone = done[node.uuid] ?: false
        if (!isDone) {
            var label = cfgNodeLabel(node)
            if (label.last() == '\n') label = label.removeRange(label.length - 1, label.length)
            label = label.replace("\"", "\\\"")
            val shape = when (node) {
                is RegularNode, is DeclarationNode -> "box"
                is ConditionalNode -> "diamond"
                is BreakNode, is ContinueNode, is ReturnNode -> "parallelogram"
                is NoOpNode -> "circle"
                else -> throw IllegalArgumentException("I don't know this variant of node")
            }
            nodeProps.add("\"${node.uuid}\" [label=\"$label\",shape=$shape];")
            done[node.uuid] = true
            if (node is SingleOutput) {
                val next = node.next
                if (next != null) {
                    links.add("\"${node.uuid}\" -> \"${next.uuid}\";")
                    visit(next)
                }
            } else if (node is ConditionalNode) {
                val truePath = node.truePath
                val falsePath = node.falsePath
                if (truePath != null) {
                    links.add("\"${node.uuid}\" -> \"${truePath.uuid}\";")
                    visit(truePath)
                }
                if (falsePath != null) {
                    links.add("\"${node.uuid}\" -> \"${falsePath.uuid}\";")
                    visit(falsePath)
                }
            }
        }
    }

    visit(cfg.entry)

    val lines = mutableListOf("digraph H {")
    lines.addAll(links)
    lines.addAll(nodeProps)
    lines.add("}")

    return lines.joinToString("\n")
}