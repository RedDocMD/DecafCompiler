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
	 * Enter a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(DecafParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(DecafParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodCallStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallStmt(DecafParser.MethodCallStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodCallStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallStmt(DecafParser.MethodCallStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(DecafParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(DecafParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(DecafParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(DecafParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(DecafParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(DecafParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(DecafParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(DecafParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(DecafParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(DecafParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmt(DecafParser.BlockStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStmt}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmt(DecafParser.BlockStmtContext ctx);
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
	 * Enter a parse tree produced by the {@code SimpleMethodCall}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 */
	void enterSimpleMethodCall(DecafParser.SimpleMethodCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleMethodCall}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 */
	void exitSimpleMethodCall(DecafParser.SimpleMethodCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CalloutMethodCall}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 */
	void enterCalloutMethodCall(DecafParser.CalloutMethodCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CalloutMethodCall}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 */
	void exitCalloutMethodCall(DecafParser.CalloutMethodCallContext ctx);
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
	 * Enter a parse tree produced by the {@code IdLocation}
	 * labeled alternative in {@link DecafParser#location}.
	 * @param ctx the parse tree
	 */
	void enterIdLocation(DecafParser.IdLocationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdLocation}
	 * labeled alternative in {@link DecafParser#location}.
	 * @param ctx the parse tree
	 */
	void exitIdLocation(DecafParser.IdLocationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayLocation}
	 * labeled alternative in {@link DecafParser#location}.
	 * @param ctx the parse tree
	 */
	void enterArrayLocation(DecafParser.ArrayLocationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayLocation}
	 * labeled alternative in {@link DecafParser#location}.
	 * @param ctx the parse tree
	 */
	void exitArrayLocation(DecafParser.ArrayLocationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LocationExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLocationExpr(DecafParser.LocationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LocationExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLocationExpr(DecafParser.LocationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultGrpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultGrpExpr(DecafParser.MultGrpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultGrpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultGrpExpr(DecafParser.MultGrpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CmpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCmpExpr(DecafParser.CmpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CmpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCmpExpr(DecafParser.CmpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpr(DecafParser.LiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpr(DecafParser.LiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegExpr(DecafParser.NegExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegExpr(DecafParser.NegExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolOpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolOpExpr(DecafParser.BoolOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolOpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolOpExpr(DecafParser.BoolOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqOpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqOpExpr(DecafParser.EqOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqOpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqOpExpr(DecafParser.EqOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(DecafParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(DecafParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(DecafParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(DecafParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddGrpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddGrpExpr(DecafParser.AddGrpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddGrpExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddGrpExpr(DecafParser.AddGrpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodCallExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallExpr(DecafParser.MethodCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodCallExpr}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallExpr(DecafParser.MethodCallExprContext ctx);
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