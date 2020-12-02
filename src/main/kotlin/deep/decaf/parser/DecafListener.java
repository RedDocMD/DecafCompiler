// Generated from /home/deep/work/kotlin/DecafKotlin/src/grammar/Decaf.g4 by ANTLR 4.8
package deep.decaf.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DecafParser}.
 */
public interface DecafListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DecafParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(DecafParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(DecafParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#field_decl}.
	 * @param ctx the parse tree
	 */
	void enterField_decl(DecafParser.Field_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#field_decl}.
	 * @param ctx the parse tree
	 */
	void exitField_decl(DecafParser.Field_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#field_name}.
	 * @param ctx the parse tree
	 */
	void enterField_name(DecafParser.Field_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#field_name}.
	 * @param ctx the parse tree
	 */
	void exitField_name(DecafParser.Field_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(DecafParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(DecafParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#method_decl}.
	 * @param ctx the parse tree
	 */
	void enterMethod_decl(DecafParser.Method_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#method_decl}.
	 * @param ctx the parse tree
	 */
	void exitMethod_decl(DecafParser.Method_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#arglist}.
	 * @param ctx the parse tree
	 */
	void enterArglist(DecafParser.ArglistContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#arglist}.
	 * @param ctx the parse tree
	 */
	void exitArglist(DecafParser.ArglistContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(DecafParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(DecafParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(DecafParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(DecafParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void enterVar_decl(DecafParser.Var_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void exitVar_decl(DecafParser.Var_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(DecafParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(DecafParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#assign_op}.
	 * @param ctx the parse tree
	 */
	void enterAssign_op(DecafParser.Assign_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#assign_op}.
	 * @param ctx the parse tree
	 */
	void exitAssign_op(DecafParser.Assign_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 */
	void enterMethod_call(DecafParser.Method_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 */
	void exitMethod_call(DecafParser.Method_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#method_name}.
	 * @param ctx the parse tree
	 */
	void enterMethod_name(DecafParser.Method_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#method_name}.
	 * @param ctx the parse tree
	 */
	void exitMethod_name(DecafParser.Method_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(DecafParser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(DecafParser.LocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(DecafParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(DecafParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#callout_list}.
	 * @param ctx the parse tree
	 */
	void enterCallout_list(DecafParser.Callout_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#callout_list}.
	 * @param ctx the parse tree
	 */
	void exitCallout_list(DecafParser.Callout_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#callout_arg}.
	 * @param ctx the parse tree
	 */
	void enterCallout_arg(DecafParser.Callout_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#callout_arg}.
	 * @param ctx the parse tree
	 */
	void exitCallout_arg(DecafParser.Callout_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(DecafParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(DecafParser.Expr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(DecafParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(DecafParser.LiteralContext ctx);
}