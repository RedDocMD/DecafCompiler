package deep.decaf.analysis

import deep.decaf.parser.*

enum class Type {
    INT,
    BOOL,
    VOID,
    ERROR
}

class TypeAssigner : DecafBaseVisitor<Type>() {
    // Expression visitors start
    override fun visitNegExpr(ctx: DecafParser.NegExprContext?): Type {
        return if (ctx != null) {
            if (visit(ctx.expr()) == Type.INT) {
                Type.INT
            } else  Type.ERROR
        } else Type.ERROR
    }

    override fun visitNotExpr(ctx: DecafParser.NotExprContext?): Type {
        return if (ctx != null) {
            if (visit(ctx.expr()) == Type.BOOL) Type.BOOL else Type.ERROR
        } else Type.ERROR
    }
    override fun visitMultGrpExpr(ctx: DecafParser.MultGrpExprContext?): Type {
        return if (ctx != null) {
            val leftType = visit(ctx.expr(0))
            val rightType = visit(ctx.expr(1))
            if (leftType == Type.INT && rightType == Type.INT) {
                Type.INT
            } else {
                Type.ERROR
            }
        } else {
            Type.ERROR
        }
    }

    override fun visitAddGrpExpr(ctx: DecafParser.AddGrpExprContext?): Type {
        return if (ctx != null) {
            val leftType = visit(ctx.expr(0))
            val rightType = visit(ctx.expr(1))
            if (leftType == Type.INT && rightType == Type.INT) {
                Type.INT
            } else {
                Type.ERROR
            }
        } else {
            Type.ERROR
        }
    }

    override fun visitEqOpExpr(ctx: DecafParser.EqOpExprContext?): Type {
        return if (ctx != null) {
            val leftType = visit(ctx.expr(0))
            val rightType = visit(ctx.expr(1))
            if (leftType == Type.INT && rightType == Type.INT) {
                Type.BOOL
            } else if (leftType == Type.BOOL && rightType == Type.BOOL) {
                Type.BOOL
            } else {
                Type.ERROR
            }
        } else {
            Type.ERROR
        }
    }


    override fun visitCmpExpr(ctx: DecafParser.CmpExprContext?): Type {
        return if (ctx != null) {
            val leftType = visit(ctx.expr(0))
            val rightType = visit(ctx.expr(1))
            if (leftType == Type.INT && rightType == Type.INT) {
                Type.BOOL
            } else {
                Type.ERROR
            }
        } else {
            Type.ERROR
        }
    }

    override fun visitAndExpr(ctx: DecafParser.AndExprContext?): Type {
        return if (ctx != null) {
            val leftType = visit(ctx.expr(0))
            val rightType = visit(ctx.expr(1))
            if (leftType == Type.BOOL && rightType == Type.BOOL) {
                Type.BOOL
            } else {
                Type.ERROR
            }
        } else {
            Type.ERROR
        }
    }

    override fun visitOrExpr(ctx: DecafParser.OrExprContext?): Type {
        return if (ctx != null) {
            val leftType = visit(ctx.expr(0))
            val rightType = visit(ctx.expr(1))
            if (leftType == Type.BOOL && rightType == Type.BOOL) {
                Type.BOOL
            } else {
                Type.ERROR
            }
        } else {
            Type.ERROR
        }
    }
    // Expression visitors end
}