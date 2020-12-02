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
	 * Visit a parse tree produced by {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(DecafParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#assign_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_op(DecafParser.Assign_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_call(DecafParser.Method_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#method_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_name(DecafParser.Method_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocation(DecafParser.LocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(DecafParser.ExprContext ctx);
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