package deep.decaf.ir

import deep.decaf.parser.*
import org.antlr.v4.runtime.*

class ParseTreeToIR : DecafBaseVisitor<IR>() {
    override fun visitProgram(ctx: DecafParser.ProgramContext?): IR {
        return if (ctx != null) {
            val fieldsDeclarations = ctx.field_decl()
            val fieldDeclarationList = mutableListOf<IRFieldDeclaration>()
            for (fieldDeclaration in fieldsDeclarations) {
                val type = if (fieldDeclaration.type().text == "int") Type.INT else Type.BOOL
                val position = positionOfContext(fieldDeclaration)
                for (field in fieldDeclaration.field_name()) {
                    val name = field.ID().text
                    fieldDeclarationList.add(
                        if (field.LSQUARE() != null) {
                            val numText = field.NUMBER().text
                            val num = if (numText.startsWith("0x")) Integer.valueOf(numText.substring(2), 16)
                            else Integer.valueOf(numText, 10)
                            IRArrayFieldDeclaration(
                                type,
                                name,
                                num,
                                position
                            )
                        } else IRRegularFieldDeclaration(type, name, position)
                    )
                }
            }
            val methodDeclarations = ctx.method_decl().map { visit(it) as IRMethodDeclaration }
            IRProgram(ctx.ID().text, fieldDeclarationList, methodDeclarations)
        } else IRNone
    }

    override fun visitMethod_decl(ctx: DecafParser.Method_declContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val methodName = ctx.ID().text
            val returnType = when {
                ctx.TK_VOID() != null -> Type.VOID
                ctx.type().text == "int" -> Type.INT
                else -> Type.BOOL
            }
            val argsOriginalList = ctx.arglist()
            val argList = mutableListOf<Arg>()
            if (argsOriginalList != null) {
                val args = argsOriginalList.arg()
                argList.addAll(args.map {
                    Arg(
                        if (it.type().text == "int") Type.INT else Type.BOOL,
                        it.ID().text
                    )
                })
            }
            val block = visit(ctx.block()) as IRBlock
            IRMethodDeclaration(returnType, methodName, argList, block, position)
        } else IRNone
    }

    override fun visitBlock(ctx: DecafParser.BlockContext?): IR {
        return if (ctx != null) {
            val varDeclarations = ctx.var_decl()
            val varDeclarationList = mutableListOf<IRVarDeclaration>()
            for (varDeclaration in varDeclarations) {
                val type = if (varDeclaration.type().text == "int") Type.INT else Type.BOOL
                val position = positionOfContext(varDeclaration)
                for (id in varDeclaration.ID()) {
                    varDeclarationList.add(IRVarDeclaration(type, id.text, position))
                }
            }
            val statements = ctx.statement().map { visit(it) as IRStatement }
            IRBlock(varDeclarationList, statements)
        } else IRNone
    }

    override fun visitAssignStmt(ctx: DecafParser.AssignStmtContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val location = visit(ctx.location()) as IRLocation
            val expression = visit(ctx.expr()) as IRExpr
            when (ctx.assign_op().text) {
                "=" -> IRDirectAssignStatement(location, expression, position)
                "+=" -> IRIncrementStatement(location, expression, position)
                else -> IRDecrementStatement(location, expression, position)
            }
        } else IRNone
    }

    override fun visitMethodCallStmt(ctx: DecafParser.MethodCallStmtContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val callExpression = visit(ctx.method_call()) as IRCallExpr
            IRInvokeStatement(callExpression, position)
        } else IRNone
    }

    override fun visitIfStmt(ctx: DecafParser.IfStmtContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val condition = visit(ctx.expr()) as IRExpr
            val ifBlock = visit(ctx.block(0)) as IRBlock
            if (ctx.block().size == 1) {
                IRIfStatement(condition, ifBlock, null, position)
            } else {
                val elseBlock = visit(ctx.block(1)) as IRBlock
                IRIfStatement(condition, ifBlock, elseBlock, position)
            }
        } else IRNone
    }

    override fun visitForStmt(ctx: DecafParser.ForStmtContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val loopVarName = ctx.ID().text
            val initExpr = visit(ctx.expr(0)) as IRExpr
            val loopCondition = visit(ctx.expr(1)) as IRExpr
            val body = visit(ctx.block()) as IRBlock
            IRForStatement(loopVarName, initExpr, loopCondition, body, position)
        } else IRNone
    }

    override fun visitReturnStmt(ctx: DecafParser.ReturnStmtContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val expr = visit(ctx.expr())
            if (expr == IRNone) IRReturnStatement(null, position) else IRReturnStatement(expr as IRExpr, position)
        } else IRNone
    }

    override fun visitBreakStmt(ctx: DecafParser.BreakStmtContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            IRBreakStatement(position)
        } else IRNone
    }

    override fun visitContinueStmt(ctx: DecafParser.ContinueStmtContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            IRContinueStatement(position)
        } else IRNone
    }

    override fun visitSimpleMethodCall(ctx: DecafParser.SimpleMethodCallContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val methodName = ctx.method_name().ID().text
            val exprList = ctx.expr_list()?.expr()
            val argList = mutableListOf<IRExpr>()
            if (exprList != null) {
                argList.addAll(exprList.map { visit(it) as IRExpr })
            }
            IRMethodCallExpr(methodName, argList, position)
        } else IRNone
    }

    override fun visitCalloutMethodCall(ctx: DecafParser.CalloutMethodCallContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val callOutName = ctx.STRING().text
            val callOutList = ctx.callout_list()
            val callOutArgs = mutableListOf<CallOutArg>()
            if (callOutList != null) {
                callOutArgs.addAll(callOutList.callout_arg().map {
                    if (it.STRING() != null) StringCallOutArg(it.STRING().text)
                    else ExprCallOutArg(visit(it.expr()) as IRExpr)
                })
            }
            IRCallOutExpr(callOutName, callOutArgs, position)
        } else IRNone
    }

    override fun visitIdLocation(ctx: DecafParser.IdLocationContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val name = ctx.ID().text
            IRIDLocation(name, position)
        } else IRNone
    }

    override fun visitArrayLocation(ctx: DecafParser.ArrayLocationContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val name = ctx.ID().text
            val indexExpr = visit(ctx.expr()) as IRExpr
            IRArrayLocation(name, indexExpr, position)
        } else IRNone
    }

    override fun visitAndExpr(ctx: DecafParser.AndExprContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val leftExpr = visit(ctx.expr(0)) as IRExpr
            val rightExpr = visit(ctx.expr(1)) as IRExpr
            IRBinOpExpr(leftExpr, rightExpr, BinOp.AND, position)
        } else IRNone
    }

    override fun visitMultGrpExpr(ctx: DecafParser.MultGrpExprContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val leftExpr = visit(ctx.expr(0)) as IRExpr
            val rightExpr = visit(ctx.expr(1)) as IRExpr
            val op = when (ctx.op.text) {
                "*" -> BinOp.MULTIPLY
                "/" -> BinOp.DIVIDE
                else -> BinOp.REMAINDER
            }
            IRBinOpExpr(leftExpr, rightExpr, op, position)
        } else IRNone
    }

    override fun visitCmpExpr(ctx: DecafParser.CmpExprContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val leftExpr = visit(ctx.expr(0)) as IRExpr
            val rightExpr = visit(ctx.expr(1)) as IRExpr
            val op = when (ctx.op.text) {
                "<" -> BinOp.LESS
                ">" -> BinOp.MORE
                "<=" -> BinOp.LESS_OR_EQ
                else -> BinOp.MORE_OR_EQ
            }
            IRBinOpExpr(leftExpr, rightExpr, op, position)
        } else IRNone
    }

    override fun visitNegExpr(ctx: DecafParser.NegExprContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val expr = visit(ctx.expr()) as IRExpr
            IRUnaryOpExpr(expr, UnaryOp.MINUS, position)
        } else IRNone
    }

    override fun visitEqOpExpr(ctx: DecafParser.EqOpExprContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val leftExpr = visit(ctx.expr(0)) as IRExpr
            val rightExpr = visit(ctx.expr(1)) as IRExpr
            val op = if (ctx.op.text == "==") BinOp.EQ else BinOp.NOT_EQ
            IRBinOpExpr(leftExpr, rightExpr, op, position)
        } else IRNone
    }

    override fun visitNotExpr(ctx: DecafParser.NotExprContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val expr = visit(ctx.expr()) as IRExpr
            IRUnaryOpExpr(expr, UnaryOp.NOT, position)
        } else IRNone
    }

    override fun visitAddGrpExpr(ctx: DecafParser.AddGrpExprContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val leftExpr = visit(ctx.expr(0)) as IRExpr
            val rightExpr = visit(ctx.expr(1)) as IRExpr
            val op = if (ctx.op.text == "+") BinOp.ADD else BinOp.SUBTRACT
            IRBinOpExpr(leftExpr, rightExpr, op, position)
        } else IRNone
    }

    override fun visitLocationExpr(ctx: DecafParser.LocationExprContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val location = visit(ctx.location()) as IRLocation
            IRLocationExpression(location, position)
        } else IRNone
    }

    override fun visitParenExpr(ctx: DecafParser.ParenExprContext?): IR {
        return if (ctx != null) {
            visit(ctx.expr())
        } else IRNone
    }

    override fun visitLiteralExpr(ctx: DecafParser.LiteralExprContext?): IR {
        return if (ctx != null) {
            visit(ctx.literal())
        } else IRNone
    }

    override fun visitBlockStmt(ctx: DecafParser.BlockStmtContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val block = visit(ctx.block()) as IRBlock
            IRBlockStatement(block, position)
        } else IRNone
    }

    override fun visitMethodCallExpr(ctx: DecafParser.MethodCallExprContext?): IR {
        return if (ctx != null) {
            visit(ctx.method_call())
        } else IRNone
    }

    override fun visitOrExpr(ctx: DecafParser.OrExprContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val leftExpr = visit(ctx.expr(0)) as IRExpr
            val rightExpr = visit(ctx.expr(1)) as IRExpr
            IRBinOpExpr(leftExpr, rightExpr, BinOp.OR, position)
        } else IRNone
    }

    override fun visitLiteral(ctx: DecafParser.LiteralContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            when {
                ctx.BOOL() != null -> {
                    val lit = ctx.BOOL().text == "true"
                    IRBoolLiteral(lit, position)
                }
                ctx.NUMBER() != null -> {
                    val numText = ctx.NUMBER().text
                    val num = if (numText.startsWith("0x")) Integer.valueOf(numText.substring(2), 16)
                    else Integer.valueOf(numText, 10)
                    IRIntLiteral(num, position)
                }
                ctx.CHAR() != null -> {
                    val char = ctx.CHAR().text[1]
                    IRIntLiteral(Character.getNumericValue(char), position)
                }
                else -> IRNone
            }
        } else IRNone
    }
}

fun positionOfContext(ctx: ParserRuleContext): Position {
    val startToken = ctx.start
    return Position(startToken.line, startToken.charPositionInLine)
}

fun parseTreeToIR(tree: DecafParser.ProgramContext): IR = ParseTreeToIR().visit(tree)