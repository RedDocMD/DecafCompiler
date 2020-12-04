package deep.decaf.ir

enum class Type {
    INT,
    BOOL,
    VOID,
    ERROR
}

data class Position(val line: Int, val column: Int)

sealed class IR

abstract class IRExpr(val pos: Position) : IR()

abstract class IRLiteral(val type: Type, pos: Position) : IRExpr(pos)
class IRIntLiteral(val lit: Int, pos: Position) : IRLiteral(Type.INT, pos)
class IRBoolLiteral(val lit: Boolean, pos: Position) : IRLiteral(Type.BOOL, pos)

abstract class IRCallExpr(val name: String, pos: Position) : IRExpr(pos)
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

abstract class IRLocation(val name: String, val pos: Position) : IR()
class IRIDLocation(name: String, pos: Position) : IRLocation(name, pos)
class IRArrayLocation(name: String, indexExpr: IRExpr, pos: Position) : IRLocation(name, pos)

class IRBlock(val fieldDeclarations: List<IRVarDeclaration>, val statements: List<IRStatement>) : IR()

abstract class IRMemberDeclaration(val type: Type, val name: String, val pos: Position) : IR()
class IRVarDeclaration(type: Type, name: String, pos: Position) : IRMemberDeclaration(type, name, pos)
abstract class IRFieldDeclaration(type: Type, name: String, pos: Position) : IRMemberDeclaration(type, name, pos)
class IRRegularFieldDeclaration(type: Type, name: String, pos: Position) : IRFieldDeclaration(type, name, pos)
class IRArrayFieldDeclaration(type: Type, name: String, size: Int, pos: Position) : IRFieldDeclaration(type, name, pos)
class IRMethodDeclaration(
    returnType: Type,
    name: String,
    val argList: List<Arg>,
    val block: IRBlock,
    pos: Position
) : IRMemberDeclaration(returnType, name, pos)

data class Arg(val type: Type, val name: String)

abstract class IRStatement(val pos: Position) : IR()

abstract class IRAssignStatement(val location: IRLocation, val expr: IRExpr, pos: Position) : IRStatement(pos)
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
    fieldDeclarations: List<IRFieldDeclaration>,
    methodDeclarations: List<IRMethodDeclaration>
) : IR()

object IRNone : IR() // A nil node