package deep.decaf.analysis

import deep.decaf.parser.*

enum class Type {
    INT,
    BOOL,
    VOID,
    ERROR
}

class TypeAssigner : DecafBaseVisitor<Type>() {
    private val idTypeMap = mutableMapOf<String, Type>()
    private val methodArgListMap = mutableMapOf<String, List<Type>>()
    private val methodReturnTypeMap = mutableMapOf<String, Type>()

    override fun visitProgram(ctx: DecafParser.ProgramContext?): Type {
        return if (ctx != null) {
            val fieldDecls = ctx.field_decl()
            val methodDecls = ctx.method_decl()
            var nonErrorCount = 0
            for (fieldDecl in fieldDecls) {
                if (visit(fieldDecl) != Type.ERROR) {
                    nonErrorCount++
                }
            }
            for (methodDecl in methodDecls) {
                if (visit(methodDecl) != Type.ERROR) {
                    nonErrorCount++
                }
            }
            if (nonErrorCount == fieldDecls.size + methodDecls.size) {
                Type.VOID
            } else {
                Type.ERROR
            }
        } else Type.ERROR
    }

    override fun visitField_decl(ctx: DecafParser.Field_declContext?): Type {
        return if (ctx != null) {
            val type = visit(ctx.type())
            val fieldNames = ctx.field_name()
            for (fieldName in fieldNames) {
                idTypeMap[fieldName.ID().text] = type
            }
            type
        } else Type.ERROR
    }

    override fun visitType(ctx: DecafParser.TypeContext?): Type {
        return if (ctx != null) {
            if (ctx.TK_BOOLEAN() != null) Type.BOOL else Type.INT
        } else Type.ERROR
    }

    override fun visitMethod_decl(ctx: DecafParser.Method_declContext?): Type {
        return if (ctx != null) {
            val returnType = if (ctx.TK_VOID() != null) Type.VOID else visit(ctx.type())
            val methodName = ctx.ID().text
            val args = ctx.arglist()?.arg()
            if (args != null) {
                val argTypeList = args.map { visit(it) }
                methodArgListMap[methodName] = argTypeList
            } else {
                methodArgListMap[methodName] = listOf()
            }
            methodReturnTypeMap[methodName] = returnType
            returnType
        } else Type.ERROR
    }

    override fun visitArg(ctx: DecafParser.ArgContext?): Type {
        return if (ctx != null) visit(ctx.type())
        else Type.ERROR
    }

    override fun visitBlock(ctx: DecafParser.BlockContext?): Type {
        return if (ctx != null) {
            val varDecls = ctx.var_decl()
            val statements = ctx.statement()
            val noErrorCount =
                varDecls.sumBy { if (visit(it) != Type.ERROR) 1 else 0 } + statements.sumBy { if (visit(it) != Type.ERROR) 1 else 0 }
            if (noErrorCount == varDecls.size + statements.size) Type.VOID else Type.ERROR
        } else Type.ERROR
    }

    // Expression visitors start
    override fun visitNegExpr(ctx: DecafParser.NegExprContext?): Type {
        return if (ctx != null) {
            if (visit(ctx.expr()) == Type.INT) {
                Type.INT
            } else Type.ERROR
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