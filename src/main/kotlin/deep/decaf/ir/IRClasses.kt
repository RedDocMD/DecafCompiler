package deep.decaf.ir

enum class Type {
    INT,
    BOOL,
    VOID,
    ERROR
}

data class Position(val line: Int, val column: Int)

sealed class IR

sealed class IRExpr(val pos: Position) : IR()

sealed class IRLiteral(val type: Type, pos: Position) : IRExpr(pos)
class IRIntLiteral(val lit: Int, pos: Position) : IRLiteral(Type.INT, pos)
class IRBoolLiteral(val lit: Boolean, pos: Position) : IRLiteral(Type.BOOL, pos)

sealed class IRCallExpr(val name: String, pos: Position) : IRExpr(pos)
class IRMethodCallExpr(name: String, val argList: List<IRExpr>, pos: Position) : IRCallExpr(name, pos)
class IRCallOutExpr(name: String, val argList: List<CallOutArg>, pos: Position) : IRCallExpr(name, pos)

sealed class CallOutArg
data class StringCallOutArg(val arg: String) : CallOutArg()
data class ExprCallOutArg(val arg: IRExpr) : CallOutArg()

class IRBinOpExpr(val left: IRExpr, val right: IRExpr, val op: BinOp, pos: Position) : IRExpr(pos)

enum class BinOp {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    REMAINDER,
    LESS,
    MORE,
    LESS_OR_EQ,
    MORE_OR_EQ,
    EQ,
    NOT_EQ,
    AND,
    OR
}

class IRUnaryOpExpr(val expr: IRExpr, val op: UnaryOp, pos: Position) : IRExpr(pos)

enum class UnaryOp {
    MINUS,
    NOT
}

sealed class IRLocation(val name: String, val pos: Position) : IR()
class IRIDLocation(name: String, pos: Position) : IRLocation(name, pos)
class IRArrayLocation(name: String, val indexExpr: IRExpr, pos: Position) : IRLocation(name, pos)

class IRBlock(val fieldDeclarations: List<IRVarDeclaration>, val statements: List<IRStatement>) : IR()

sealed class IRMemberDeclaration(val type: Type, val name: String, val pos: Position) : IR()
class IRVarDeclaration(type: Type, name: String, pos: Position) : IRMemberDeclaration(type, name, pos)
sealed class IRFieldDeclaration(type: Type, name: String, pos: Position) : IRMemberDeclaration(type, name, pos)
class IRRegularFieldDeclaration(type: Type, name: String, pos: Position) : IRFieldDeclaration(type, name, pos)
class IRArrayFieldDeclaration(type: Type, name: String, val size: Int, pos: Position) :
    IRFieldDeclaration(type, name, pos)

class IRMethodDeclaration(
    returnType: Type,
    name: String,
    val argList: List<Arg>,
    val block: IRBlock,
    pos: Position
) : IRMemberDeclaration(returnType, name, pos)

data class Arg(val type: Type, val name: String)

sealed class IRStatement(val pos: Position) : IR()

sealed class IRAssignStatement(val location: IRLocation, val expr: IRExpr, pos: Position) : IRStatement(pos)
class IRDirectAssignStatement(location: IRLocation, expr: IRExpr, pos: Position) :
    IRAssignStatement(location, expr, pos)

class IRIncrementStatement(location: IRLocation, expr: IRExpr, pos: Position) : IRAssignStatement(location, expr, pos)
class IRDecrementStatement(location: IRLocation, expr: IRExpr, pos: Position) : IRAssignStatement(location, expr, pos)

class IRBreakStatement(pos: Position) : IRStatement(pos)
class IRContinueStatement(pos: Position) : IRStatement(pos)

class IRIfStatement(val condition: IRExpr, val ifBlock: IRBlock, val elseBlock: IRBlock?, pos: Position) :
    IRStatement(pos)

class IRForStatement(
    val loopVar: String,
    val initExpr: IRExpr,
    val condition: IRExpr,
    val body: IRBlock,
    pos: Position
) : IRStatement(pos)

class IRReturnStatement(val expr: IRExpr?, pos: Position) : IRStatement(pos)
class IRInvokeStatement(val expr: IRCallExpr, pos: Position) : IRStatement(pos)

class IRProgram(
    val name: String,
    val fieldDeclarations: List<IRFieldDeclaration>,
    val methodDeclarations: List<IRMethodDeclaration>
) : IR()

object IRNone : IR() // A nil node

fun irToString(irNode: IR, noOfTabs: Int): String {
    val output = StringBuilder()
    val tabPref = StringBuilder()
    for (i in 1..noOfTabs) {
        tabPref.append('\t')
    }
    when (irNode) {
        is IRIntLiteral -> output.append("${irNode.lit}")
        is IRBoolLiteral -> output.append("${irNode.lit}")
        is IRMethodCallExpr -> {
            output.append("(${irNode.name}( ")
            val argStrings = irNode.argList.map { irToString(it, noOfTabs) }
            output.append(argStrings.joinToString()).append("))")
        }
        is IRCallOutExpr -> {
            output.append("(callout ${irNode.name}(")
            val argStrings = irNode.argList.map {
                when (it) {
                    is StringCallOutArg -> it.arg
                    is ExprCallOutArg -> irToString(it.arg, noOfTabs)
                }
            }
            output.append(argStrings.joinToString()).append("))")
        }
        is IRBinOpExpr -> {
            val leftExpr = irToString(irNode.left, noOfTabs)
            val rightExpr = irToString(irNode.right, noOfTabs)
            val op = when (irNode.op) {
                BinOp.ADD -> "+"
                BinOp.SUBTRACT -> "-"
                BinOp.MULTIPLY -> "*"
                BinOp.DIVIDE -> "/"
                BinOp.REMAINDER -> "%"
                BinOp.LESS -> "<"
                BinOp.MORE -> ">"
                BinOp.LESS_OR_EQ -> "<="
                BinOp.MORE_OR_EQ -> ">="
                BinOp.EQ -> "=="
                BinOp.NOT_EQ -> "!="
                BinOp.AND -> "&&"
                BinOp.OR -> "||"
            }
            output.append("($leftExpr $op $rightExpr)")
        }
        is IRUnaryOpExpr -> {
            val expr = irToString(irNode.expr, noOfTabs)
            val op = when (irNode.op) {
                UnaryOp.NOT -> '!'
                UnaryOp.MINUS -> '-'
            }
            output.append("$op $expr")
        }
        is IRIDLocation -> output.append(irNode.name)
        is IRArrayLocation -> output.append("${irNode.name}[${irToString(irNode.indexExpr, noOfTabs)}]")
        is IRBlock -> {
            for (varDeclaration in irNode.fieldDeclarations) {
                output.append(irToString(varDeclaration, noOfTabs + 1))
            }
            for (statement in irNode.statements) {
                output.append(irToString(statement, noOfTabs + 1))
            }
        }
        is IRVarDeclaration -> {
            val type = when (irNode.type) {
                Type.BOOL -> "bool"
                else -> "int"
            }
            output.append(tabPref).append("$type ${irNode.name}").append("\n")
        }
        is IRRegularFieldDeclaration -> {
            val type = when (irNode.type) {
                Type.BOOL -> "bool"
                else -> "int"
            }
            output.append(tabPref).append("$type ${irNode.name}").append("\n")
        }
        is IRArrayFieldDeclaration -> {
            val type = when (irNode.type) {
                Type.BOOL -> "bool"
                else -> "int"
            }
            output.append(tabPref).append("$type ${irNode.name}[${irNode.size}]").append("\n")
        }
        is IRMethodDeclaration -> {
            val returnType = when (irNode.type) {
                Type.BOOL -> "bool"
                else -> "int"
            }
            val argStrings = irNode.argList.map {
                val type = when (it.type) {
                    Type.BOOL -> "bool"
                    else -> "int"
                }
                "$type ${it.name}"
            }
            val argList = argStrings.joinToString()
            output.append(tabPref).append("$returnType ${irNode.name}($argList)").append("\n")
                .append(irToString(irNode.block, noOfTabs + 1))
        }
        is IRDirectAssignStatement -> output.append(tabPref).append(irToString(irNode.location, noOfTabs)).append(" = ")
            .append(irToString(irNode.expr, noOfTabs)).append("\n").append(" = ")
            .append(irToString(irNode.expr, noOfTabs)).append("\n")
        is IRIncrementStatement -> output.append(tabPref).append(irToString(irNode.location, noOfTabs)).append(" = ")
            .append(irToString(irNode.expr, noOfTabs)).append("\n").append(" += ")
            .append(irToString(irNode.expr, noOfTabs)).append("\n")
        is IRDecrementStatement -> output.append(tabPref).append(irToString(irNode.location, noOfTabs)).append(" = ")
            .append(irToString(irNode.expr, noOfTabs)).append("\n").append(" -= ")
            .append(irToString(irNode.expr, noOfTabs)).append("\n")
        is IRBreakStatement -> output.append(tabPref).append("break").append("\n")
        is IRContinueStatement -> output.append(tabPref).append("continue").append("\n")
        is IRIfStatement -> {
            output.append(tabPref).append("if (${irToString(irNode.condition, noOfTabs)})").append("\n")
            output.append(irToString(irNode.ifBlock, noOfTabs + 1))
            if (irNode.elseBlock != null) {
                output.append(tabPref).append("else").append("\n")
                output.append(irToString(irNode.elseBlock, noOfTabs + 1))
            }
        }
        is IRForStatement -> {
            output.append(tabPref).append(
                "for ${irNode.loopVar} = ${
                    irToString(
                        irNode.initExpr,
                        noOfTabs
                    )
                } until ${irToString(irNode.condition, noOfTabs)}"
            ).append("\n")
            output.append(irToString(irNode.body, noOfTabs + 1))
        }
        is IRReturnStatement -> {
            output.append(tabPref).append("return")
            if (irNode.expr != null) {
                output.append(irToString(irNode.expr, noOfTabs))
            }
            output.append("\n")
        }
        is IRInvokeStatement -> output.append(tabPref).append(irToString(irNode.expr, noOfTabs)).append("\n")
        is IRProgram -> {
            output.append(tabPref).append("class ${irNode.name}").append("\n")
            for (fieldDeclaration in irNode.fieldDeclarations) {
               output.append(irToString(fieldDeclaration, noOfTabs + 1))
            }
            for (methodDeclaration in irNode.methodDeclarations) {
                output.append(irToString(methodDeclaration, noOfTabs + 1))
            }
        }
        is IRNone -> {
            output.append("*NONE*")
        }
    }
    return output.toString()
}