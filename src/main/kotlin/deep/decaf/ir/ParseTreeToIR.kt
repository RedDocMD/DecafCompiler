package deep.decaf.ir

import deep.decaf.parser.*
import org.antlr.v4.runtime.*
import kotlin.math.*

class ParseTreeToIR : DecafBaseVisitor<IR>() {
    override fun visitProgram(ctx: DecafParser.ProgramContext?): IR {
        return super.visitProgram(ctx)
    }

    override fun visitField_decl(ctx: DecafParser.Field_declContext?): IR {
        return super.visitField_decl(ctx)
    }

    override fun visitField_name(ctx: DecafParser.Field_nameContext?): IR {
        return super.visitField_name(ctx)
    }

    override fun visitType(ctx: DecafParser.TypeContext?): IR {
        return super.visitType(ctx)
    }

    override fun visitMethod_decl(ctx: DecafParser.Method_declContext?): IR {
        return super.visitMethod_decl(ctx)
    }

    override fun visitArglist(ctx: DecafParser.ArglistContext?): IR {
        return super.visitArglist(ctx)
    }

    override fun visitArg(ctx: DecafParser.ArgContext?): IR {
        return super.visitArg(ctx)
    }

    override fun visitBlock(ctx: DecafParser.BlockContext?): IR {
        return super.visitBlock(ctx)
    }

    override fun visitVar_decl(ctx: DecafParser.Var_declContext?): IR {
        return super.visitVar_decl(ctx)
    }

    override fun visitAssignStmt(ctx: DecafParser.AssignStmtContext?): IR {
        return super.visitAssignStmt(ctx)
    }

    override fun visitMethodCallStmt(ctx: DecafParser.MethodCallStmtContext?): IR {
        return super.visitMethodCallStmt(ctx)
    }

    override fun visitIfStmt(ctx: DecafParser.IfStmtContext?): IR {
        return super.visitIfStmt(ctx)
    }

    override fun visitForStmt(ctx: DecafParser.ForStmtContext?): IR {
        return super.visitForStmt(ctx)
    }

    override fun visitReturnStmt(ctx: DecafParser.ReturnStmtContext?): IR {
        return super.visitReturnStmt(ctx)
    }

    override fun visitBreakStmt(ctx: DecafParser.BreakStmtContext?): IR {
        return super.visitBreakStmt(ctx)
    }

    override fun visitContinueStmt(ctx: DecafParser.ContinueStmtContext?): IR {
        return super.visitContinueStmt(ctx)
    }

    override fun visitBlockStmt(ctx: DecafParser.BlockStmtContext?): IR {
        return super.visitBlockStmt(ctx)
    }

    override fun visitAssign_op(ctx: DecafParser.Assign_opContext?): IR {
        return super.visitAssign_op(ctx)
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
            val callOutList = ctx.callout_list().callout_arg()
            val callOutArgs = mutableListOf<CallOutArg>()
            if (callOutList != null) {
               callOutArgs.addAll(callOutList.map {
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

    override fun visitLocationExpr(ctx: DecafParser.LocationExprContext?): IR {
        return super.visitLocationExpr(ctx)
    }

    override fun visitMultGrpExpr(ctx: DecafParser.MultGrpExprContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val leftExpr = visit(ctx.expr(0)) as IRExpr
            val rightExpr = visit(ctx.expr(1)) as IRExpr
            val op = when(ctx.op.text) {
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
            val op = when(ctx.op.text) {
                "<" -> BinOp.LESS
                ">" -> BinOp.MORE
                "<=" -> BinOp.LESS_OR_EQ
                else -> BinOp.MORE_OR_EQ
            }
            IRBinOpExpr(leftExpr, rightExpr, op, position)
        } else IRNone
    }

    override fun visitLiteralExpr(ctx: DecafParser.LiteralExprContext?): IR {
        return super.visitLiteralExpr(ctx)
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

    override fun visitParenExpr(ctx: DecafParser.ParenExprContext?): IR {
        return super.visitParenExpr(ctx)
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

    override fun visitOrExpr(ctx: DecafParser.OrExprContext?): IR {
        return if (ctx != null) {
            val position = positionOfContext(ctx)
            val leftExpr = visit(ctx.expr(0)) as IRExpr
            val rightExpr = visit(ctx.expr(1)) as IRExpr
            IRBinOpExpr(leftExpr, rightExpr, BinOp.OR, position)
        } else IRNone
    }

    override fun visitMethodCallExpr(ctx: DecafParser.MethodCallExprContext?): IR {
        return super.visitMethodCallExpr(ctx)
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
                    IRIntLiteral(Integer.valueOf(ctx.NUMBER().text), position)
                }
                ctx.CHAR() != null -> {
                    val char = ctx.CHAR().text[1]
                    IRIntLiteral(Character.getNumericValue(char) ,position)
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