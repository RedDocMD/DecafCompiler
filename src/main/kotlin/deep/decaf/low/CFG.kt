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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RegularNode

        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }

}

class ConditionalNode(
    override var prev: CFGNode?,
    var truePath: CFGNode?,
    var falsePath: CFGNode?,
    var condition: IRExpr,
    var type: ConditionalNodeType
) : SingleInput {
    override val uuid = UUID.randomUUID().toString().replace("-", "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConditionalNode

        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }
}

sealed class ConditionalNodeType
object IfElse : ConditionalNodeType()
data class For(val id: Int) : ConditionalNodeType()
data class MethodEnd(val name: String) : ConditionalNodeType()

sealed class JumpNodes(
    override var next: CFGNode?,
    override var prev: CFGNode?
) : SingleInput, SingleOutput {
    override val uuid = UUID.randomUUID().toString().replace("-", "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JumpNodes

        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DeclarationNode

        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }
}

sealed class NoOpNode(
    val prevs: MutableList<CFGNode>,
    override var next: CFGNode?
) : SingleOutput {
    override val uuid = UUID.randomUUID().toString().replace("-", "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NoOpNode

        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }
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

data class CFGProgram(
    val mainCFG: CFG,
    val cfgMap: Map<String, CFG>,
    val declarations: List<IRFieldDeclaration>
)

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
                    mutableListOf(ifCFG.exit, elseCFG.exit), null, IfElse
                )
                ifCFG.exit.next = noOp
                elseCFG.exit.next = noOp
                val branchNode = ConditionalNode(
                    null, ifCFG.entry, elseCFG.entry, statement.condition, IfElse
                )
                shortCircuit(branchNode)
                CFG(branchNode, noOp)
            } else if (ifCFG != null) {
                val branchNode = ConditionalNode(
                    null, ifCFG.entry, null, statement.condition, IfElse
                )
                val noOp = ExitNoOpNode(
                    mutableListOf(ifCFG.exit, branchNode), null, IfElse
                )
                ifCFG.exit.next = noOp
                branchNode.falsePath = noOp
                shortCircuit(branchNode)
                CFG(branchNode, noOp)
            } else if (elseCFG != null) {
                val branchNode = ConditionalNode(
                    null, null, elseCFG.entry, statement.condition, IfElse
                )
                val noOp = ExitNoOpNode(
                    mutableListOf(branchNode, elseCFG.exit), null, IfElse
                )
                elseCFG.exit.next = noOp
                branchNode.truePath = noOp
                shortCircuit(branchNode)
                CFG(branchNode, noOp)
            } else {
                val branchNode = ConditionalNode(
                    null, null, null, statement.condition, IfElse
                )
                val noOp = ExitNoOpNode(
                    mutableListOf(branchNode, branchNode), null, IfElse
                )
                branchNode.falsePath = noOp
                branchNode.truePath = noOp
                shortCircuit(branchNode)
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
                null, null, null, termination, For(info.loopId())
            )
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

            // Re-wire the break and continue properly
            val done = mutableMapOf<String, Boolean>()
            fun dfs(node: CFGNode) {
                val isDone = done[node.uuid] ?: false
                if (!isDone) {
                    if (node is BreakNode && node.loopId == info.loopId()) {
                        val next = node.next
                        node.next = exitNoOp
                        exitNoOp.prevs.add(node)
                        if (next != null) {
                            if (next is SingleInput) {
                                next.prev = null
                            } else if (next is NoOpNode) {
                                next.prevs.remove(node)
                            }
                        }
                    }
                    if (node is ContinueNode && node.loopId == info.loopId()) {
                        val next = node.next
                        node.next = noOp
                        noOp.prevs.add(node)
                        if (next != null) {
                            if (next is SingleInput) {
                                next.prev = null
                            } else if (next is NoOpNode) {
                                next.prevs.remove(node)
                            }
                        }
                    }
                    done[node.uuid] = true
                    if (node is SingleOutput) {
                        if (node.next != null) {
                            dfs(node.next!!)
                        }
                    } else if (node is ConditionalNode) {
                        if (node.truePath != null) {
                            dfs(node.truePath!!)
                        }
                        if (node.falsePath != null) {
                            dfs(node.falsePath!!)
                        }
                    }
                }
            }
            dfs(declarationNode)
            info.leaveLoop()
            shortCircuit(conditionNode)

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

private fun shortCircuit(node: ConditionalNode) {
    val expr = node.condition
    if (expr is IRBinOpExpr) {
        if (expr.op == BinOp.AND) {
            val rightNode = ConditionalNode(
                null, null, null, expr.right, node.type
            )

            rightNode.truePath = node.truePath
            if (node.truePath != null && node.truePath is SingleInput) {
                (node.truePath!! as SingleInput).prev = rightNode
            } else if (node.truePath != null && node.truePath is NoOpNode) {
                val prevs = (node.truePath!! as NoOpNode).prevs
                prevs.remove(node)
                prevs.add(rightNode)
            }

            if (node.falsePath != null && node.falsePath is NoOpNode) {
                val fNode = node.falsePath!! as NoOpNode
                fNode.prevs.add(rightNode)
                rightNode.falsePath = fNode
            } else {
                val noOp = ExitNoOpNode(
                    mutableListOf(rightNode, node), node.falsePath, IfElse
                )
                if (node.falsePath != null) {
                    (node.falsePath!! as SingleInput).prev = noOp
                }
                node.falsePath = noOp
                rightNode.falsePath = noOp
            }

            node.truePath = rightNode
            rightNode.prev = node

            node.condition = expr.left

            shortCircuit(node)
            shortCircuit(rightNode)
        } else if (expr.op == BinOp.OR) {
            val rightNode = ConditionalNode(
                null, null, null, expr.right, node.type
            )

            rightNode.falsePath = node.falsePath
            if (node.falsePath != null && node.falsePath is SingleInput) {
                (node.falsePath!! as SingleInput).prev = rightNode
            } else if (node.falsePath != null && node.falsePath is NoOpNode) {
                val prevs = (node.falsePath!! as NoOpNode).prevs
                prevs.remove(node)
                prevs.add(rightNode)
            }

            if (node.truePath != null && node.truePath is NoOpNode) {
                val tNode = node.truePath!! as NoOpNode
                tNode.prevs.add(rightNode)
                rightNode.truePath = tNode
            } else {
                val noOp = ExitNoOpNode(
                    mutableListOf(rightNode, node), node.truePath, IfElse
                )
                if (node.truePath != null) {
                    (node.truePath!! as SingleInput).prev = noOp
                }
                node.truePath = noOp
                rightNode.truePath = noOp
            }

            node.falsePath = rightNode
            rightNode.prev = node

            node.condition = expr.left

            shortCircuit(node)
            shortCircuit(rightNode)
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

fun constructCFG(program: IRProgram): CFGProgram {
    val methodCFGMap = mutableMapOf<String, CFG>()
    var mainCFG: CFG? = null
    for (method in program.methodDeclarations) {
        val info = CFGCreationInfo()
        val cfg = constructCFG(method.block, info)
        if (cfg != null) {
            // Rewire returns to point to end of method
            val endMethod = ExitNoOpNode(mutableListOf(cfg.exit), null, MethodEnd(method.name))
            cfg.exit.next = endMethod

            val visited = mutableMapOf<String, Boolean>()
            fun dfs(node: CFGNode) {
                val done = visited[node.uuid] ?: false
                if (!done) {
                    visited[node.uuid] = true
                    if (node is ReturnNode) {
                        val next = node.next
                        if (next != null) {
                            if (next is SingleInput) {
                                next.prev = null
                            } else if (next is NoOpNode) {
                                next.prevs.remove(node)
                            }
                        }
                        node.next = endMethod
                        endMethod.prevs.add(node)
                    } else {
                        if (node is SingleOutput && node.next != null) {
                            dfs(node.next!!)
                        } else if (node is ConditionalNode) {
                            if (node.truePath != null) {
                                dfs(node.truePath!!)
                            }
                            if (node.falsePath != null) {
                                dfs(node.falsePath!!)
                            }
                        }
                    }
                }
            }

            dfs(cfg.entry)
            methodCFGMap[method.name] = CFG(cfg.entry, endMethod)
            if (method.name == "main") {
                mainCFG = cfg
            }
        }
    }
    if (mainCFG != null) {
        return CFGProgram(mainCFG, methodCFGMap, program.fieldDeclarations)
    } else {
        throw IllegalStateException("semantic checker screwed up")
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