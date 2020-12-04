package deep.decaf.ir

enum class Type {
    INT,
    BOOL,
    VOID,
    ERROR
}

abstract class IR


abstract class IRExpr : IR()

abstract class IRLiteral(val type: Type) : IRExpr()
data class IRIntLiteral(val lit: Int) : IRLiteral(Type.INT)


data class IRBoolLiteral(val lit: Boolean) : IRLiteral(Type.BOOL)

abstract class IRCallExpr(val name: String, private val returnType: Type) : IRExpr()

class IRMethodCallExpr(name: String, returnType: Type, val argList: List<IRExpr>) : IRCallExpr(name, returnType)
class IRCallOutExpr(name: String, val argList: List<CallOutArg>) : IRCallExpr(name, Type.INT)

sealed class CallOutArg
data class StringCallOutArg(val arg: String) : CallOutArg()
data class ExprCallOutArg(val arg: IRExpr) : CallOutArg()

data class IRBinOpExpr(val left: IRExpr, val right: IRExpr, val op: BinOp) : IRExpr()

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

data class IRUnaryOpExpr(val expr: IRExpr, val op: UnaryOp) : IRExpr()

enum class UnaryOp {
    MINUS,
    NOT
}

class IRLocationExpression(val location: IRLocation) : IRExpr()

abstract class IRLocation(val name: String) : IR()
class IRIDLocation(name: String) : IRLocation(name)
class IRArrayLocation(name: String, indexExpr: IRExpr) : IRLocation(name)

class IRBlock(val fieldDeclarations: List<IRVarDeclaration>, val statements: List<IRStatement>) : IR()

abstract class IRMemberDeclaration(val type: Type, val name: String) : IR()
class IRVarDeclaration(type: Type, name: String) : IRMemberDeclaration(type, name)
class IRRegularFieldDeclaration(type: Type, name: String) : IRMemberDeclaration(type, name)
class IRArrayFieldDeclaration(type: Type, name: String, size: Int) : IRMemberDeclaration(type, name)
class IRMethodDeclaration(returnType: Type, name: String, val argList: List<Arg>) : IRMemberDeclaration(returnType, name)

data class Arg(val type: Type, val name: String)

abstract class IRStatement : IR()

abstract class IRAssignStatement(val location: IRLocation, val expr: IRExpr)
class IRDirectAssignStatement(location: IRLocation, expr: IRExpr) : IRAssignStatement(location, expr)
class IRIncrementStatement(location: IRLocation, expr: IRExpr) : IRAssignStatement(location, expr)
class IRDecrementStatement(location: IRLocation, expr: IRExpr) : IRAssignStatement(location, expr)

class IRBreakStatement() : IRStatement()
class IRContinueStatement(): IRStatement()

class IRIfStatement(val condition: IRExpr, val ifBlock: IRBlock, val elseBlock: IRBlock?) : IRStatement()
class IRForStatement(val loopVar: String, val initExpr: IRExpr, val condition: IRExpr, val body: IRBlock) : IRStatement()

class IRReturnStatement(val expr: IRExpr?) : IRStatement()
class IRInvokeStatement(val expr: IRCallExpr) : IRStatement()
