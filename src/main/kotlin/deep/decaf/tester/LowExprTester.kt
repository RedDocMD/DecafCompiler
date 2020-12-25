package deep.decaf.tester

import deep.decaf.ir.*
import deep.decaf.low.amd64.*

fun main() {
    val pos = Position.unknown()
    val expressions = listOf(
        IRBinOpExpr(
            IRBinOpExpr(
                IRIntLiteral(1, pos), IRIntLiteral(2, pos), BinOp.MULTIPLY, pos
            ),
            IRBinOpExpr(
                IRBinOpExpr(
                    IRIntLiteral(3, pos), IRIntLiteral(4, pos), BinOp.MULTIPLY, pos
                ),
                IRBinOpExpr(
                    IRIntLiteral(5, pos), IRIntLiteral(6, pos), BinOp.MULTIPLY, pos
                ),
                BinOp.SUBTRACT,
                pos
            ),
            BinOp.ADD,
            pos
        ),

        IRBinOpExpr(
            IRBinOpExpr(
                IRLocationExpression(IRIDLocation("a", pos), pos),
                IRLocationExpression(IRIDLocation("b", pos), pos),
                BinOp.MORE,
                pos
            ),
            IRBinOpExpr(
                IRLocationExpression(IRIDLocation("c", pos), pos),
                IRLocationExpression(IRIDLocation("d", pos), pos),
                BinOp.EQ,
                pos
            ),
            BinOp.AND,
            pos
        )
    )

    val info = AsmProgramInfo()
    expressions.forEach { expr ->
        val statements = irExprToLow(expr, info)
        val asm = statements.joinToString("\n") { it.toString() }
        println(asm)
        println()
    }
}