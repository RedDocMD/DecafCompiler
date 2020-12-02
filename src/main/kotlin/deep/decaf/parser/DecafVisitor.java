// Generated from /home/deep/work/kotlin/DecafKotlin/src/grammar/Decaf.g4 by ANTLR 4.8
package deep.decaf.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DecafParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DecafVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DecafParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(DecafParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#field_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_decl(DecafParser.Field_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#field_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_name(DecafParser.Field_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(DecafParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#method_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_decl(DecafParser.Method_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglist(DecafParser.ArglistContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(DecafParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(DecafParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#var_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_decl(DecafParser.Var_declContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(DecafParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodCallStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCallStmt(DecafParser.MethodCallStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(DecafParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(DecafParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(DecafParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(DecafParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(DecafParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BlockStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStmt(DecafParser.BlockStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#assign_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_op(DecafParser.Assign_opContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleMethodCall}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleMethodCall(DecafParser.SimpleMethodCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CalloutMethodCall}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalloutMethodCall(DecafParser.CalloutMethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#method_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_name(DecafParser.Method_nameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdLocation}
	 * labeled alternative in {@link DecafParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdLocation(DecafParser.IdLocationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayLocation}
	 * labeled alternative in {@link DecafParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLocation(DecafParser.ArrayLocationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LocationExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocationExpr(DecafParser.LocationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultGrpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultGrpExpr(DecafParser.MultGrpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CmpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpExpr(DecafParser.CmpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpr(DecafParser.LiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(DecafParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolOpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOpExpr(DecafParser.BoolOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqOpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqOpExpr(DecafParser.EqOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(DecafParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(DecafParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddGrpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddGrpExpr(DecafParser.AddGrpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodCallExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCallExpr(DecafParser.MethodCallExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#callout_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallout_list(DecafParser.Callout_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#callout_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallout_arg(DecafParser.Callout_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list(DecafParser.Expr_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(DecafParser.LiteralContext ctx);
}