// Generated from /home/deep/work/kotlin/DecafKotlin/src/grammar/DecafParser.g4 by ANTLR 4.8
package deep.decaf.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DecafParserParser}.
 */
public interface DecafParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(DecafParserParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(DecafParserParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#field_decl}.
	 * @param ctx the parse tree
	 */
	void enterField_decl(DecafParserParser.Field_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#field_decl}.
	 * @param ctx the parse tree
	 */
	void exitField_decl(DecafParserParser.Field_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#field_name}.
	 * @param ctx the parse tree
	 */
	void enterField_name(DecafParserParser.Field_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#field_name}.
	 * @param ctx the parse tree
	 */
	void exitField_name(DecafParserParser.Field_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(DecafParserParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(DecafParserParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#method_decl}.
	 * @param ctx the parse tree
	 */
	void enterMethod_decl(DecafParserParser.Method_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#method_decl}.
	 * @param ctx the parse tree
	 */
	void exitMethod_decl(DecafParserParser.Method_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#arglist}.
	 * @param ctx the parse tree
	 */
	void enterArglist(DecafParserParser.ArglistContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#arglist}.
	 * @param ctx the parse tree
	 */
	void exitArglist(DecafParserParser.ArglistContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(DecafParserParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(DecafParserParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(DecafParserParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(DecafParserParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void enterVar_decl(DecafParserParser.Var_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void exitVar_decl(DecafParserParser.Var_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(DecafParserParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(DecafParserParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#method_call}.
	 * @param ctx the parse tree
	 */
	void enterMethod_call(DecafParserParser.Method_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#method_call}.
	 * @param ctx the parse tree
	 */
	void exitMethod_call(DecafParserParser.Method_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#method_name}.
	 * @param ctx the parse tree
	 */
	void enterMethod_name(DecafParserParser.Method_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#method_name}.
	 * @param ctx the parse tree
	 */
	void exitMethod_name(DecafParserParser.Method_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(DecafParserParser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(DecafParserParser.LocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(DecafParserParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(DecafParserParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#callout_list}.
	 * @param ctx the parse tree
	 */
	void enterCallout_list(DecafParserParser.Callout_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#callout_list}.
	 * @param ctx the parse tree
	 */
	void exitCallout_list(DecafParserParser.Callout_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#callout_arg}.
	 * @param ctx the parse tree
	 */
	void enterCallout_arg(DecafParserParser.Callout_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#callout_arg}.
	 * @param ctx the parse tree
	 */
	void exitCallout_arg(DecafParserParser.Callout_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(DecafParserParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(DecafParserParser.Expr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(DecafParserParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(DecafParserParser.LiteralContext ctx);
}