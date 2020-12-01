// Generated from /home/deep/work/kotlin/DecafKotlin/src/grammar/DecafParser.g4 by ANTLR 4.8
package deep.decaf.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DecafParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DecafParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(DecafParserParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#field_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_decl(DecafParserParser.Field_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#field_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_name(DecafParserParser.Field_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(DecafParserParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#method_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_decl(DecafParserParser.Method_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglist(DecafParserParser.ArglistContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(DecafParserParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(DecafParserParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#var_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_decl(DecafParserParser.Var_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(DecafParserParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#method_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_call(DecafParserParser.Method_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#method_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_name(DecafParserParser.Method_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocation(DecafParserParser.LocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(DecafParserParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#callout_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallout_list(DecafParserParser.Callout_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#callout_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallout_arg(DecafParserParser.Callout_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list(DecafParserParser.Expr_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParserParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(DecafParserParser.LiteralContext ctx);
}