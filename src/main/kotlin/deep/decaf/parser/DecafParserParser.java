// Generated from /home/deep/work/kotlin/DecafKotlin/src/grammar/DecafParser.g4 by ANTLR 4.8
package deep.decaf.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DecafParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, COMMENT=6, WS=7, TK_BOOLEAN=8, 
		TK_BREAK=9, TK_CALLOUT=10, TK_CLASS=11, TK_CONTINUE=12, TK_ELSE=13, TK_FOR=14, 
		TK_IF=15, TK_INT=16, TK_RETURN=17, TK_VOID=18, LCURLY=19, RCURLY=20, LPAREN=21, 
		RPAREN=22, LSQUARE=23, RSQUARE=24, BOOL=25, CHAR=26, STRING=27, ID=28, 
		NUMBER=29, COMMA=30, SEMICOLON=31, DOT=32, BIN_OP=33, ASSIGN_OP=34;
	public static final int
		RULE_program = 0, RULE_field_decl = 1, RULE_field_name = 2, RULE_type = 3, 
		RULE_method_decl = 4, RULE_arglist = 5, RULE_arg = 6, RULE_block = 7, 
		RULE_var_decl = 8, RULE_statement = 9, RULE_method_call = 10, RULE_method_name = 11, 
		RULE_location = 12, RULE_expr = 13, RULE_callout_list = 14, RULE_callout_arg = 15, 
		RULE_expr_list = 16, RULE_literal = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "field_decl", "field_name", "type", "method_decl", "arglist", 
			"arg", "block", "var_decl", "statement", "method_call", "method_name", 
			"location", "expr", "callout_list", "callout_arg", "expr_list", "literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'+='", "'-='", "'-'", "'!'", null, null, "'boolean'", "'break'", 
			"'callout'", "'class'", "'continue'", "'else'", "'for'", "'if'", "'int'", 
			"'return'", "'void'", "'{'", "'}'", "'('", "')'", "'['", "']'", null, 
			null, null, null, null, "','", "';'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "COMMENT", "WS", "TK_BOOLEAN", "TK_BREAK", 
			"TK_CALLOUT", "TK_CLASS", "TK_CONTINUE", "TK_ELSE", "TK_FOR", "TK_IF", 
			"TK_INT", "TK_RETURN", "TK_VOID", "LCURLY", "RCURLY", "LPAREN", "RPAREN", 
			"LSQUARE", "RSQUARE", "BOOL", "CHAR", "STRING", "ID", "NUMBER", "COMMA", 
			"SEMICOLON", "DOT", "BIN_OP", "ASSIGN_OP"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "DecafParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DecafParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode TK_CLASS() { return getToken(DecafParserParser.TK_CLASS, 0); }
		public TerminalNode ID() { return getToken(DecafParserParser.ID, 0); }
		public TerminalNode LCURLY() { return getToken(DecafParserParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(DecafParserParser.RCURLY, 0); }
		public List<Field_declContext> field_decl() {
			return getRuleContexts(Field_declContext.class);
		}
		public Field_declContext field_decl(int i) {
			return getRuleContext(Field_declContext.class,i);
		}
		public List<Method_declContext> method_decl() {
			return getRuleContexts(Method_declContext.class);
		}
		public Method_declContext method_decl(int i) {
			return getRuleContext(Method_declContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(TK_CLASS);
			setState(37);
			match(ID);
			setState(38);
			match(LCURLY);
			setState(42);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(39);
					field_decl();
					}
					} 
				}
				setState(44);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TK_BOOLEAN) | (1L << TK_INT) | (1L << TK_VOID))) != 0)) {
				{
				{
				setState(45);
				method_decl();
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_declContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<Field_nameContext> field_name() {
			return getRuleContexts(Field_nameContext.class);
		}
		public Field_nameContext field_name(int i) {
			return getRuleContext(Field_nameContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(DecafParserParser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DecafParserParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecafParserParser.COMMA, i);
		}
		public Field_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterField_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitField_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitField_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Field_declContext field_decl() throws RecognitionException {
		Field_declContext _localctx = new Field_declContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_field_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			type();
			setState(54);
			field_name();
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(55);
				match(COMMA);
				setState(56);
				field_name();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DecafParserParser.ID, 0); }
		public TerminalNode LSQUARE() { return getToken(DecafParserParser.LSQUARE, 0); }
		public TerminalNode NUMBER() { return getToken(DecafParserParser.NUMBER, 0); }
		public TerminalNode RSQUARE() { return getToken(DecafParserParser.RSQUARE, 0); }
		public Field_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterField_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitField_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitField_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Field_nameContext field_name() throws RecognitionException {
		Field_nameContext _localctx = new Field_nameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_field_name);
		try {
			setState(69);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				match(ID);
				setState(66);
				match(LSQUARE);
				setState(67);
				match(NUMBER);
				setState(68);
				match(RSQUARE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode TK_BOOLEAN() { return getToken(DecafParserParser.TK_BOOLEAN, 0); }
		public TerminalNode TK_INT() { return getToken(DecafParserParser.TK_INT, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			_la = _input.LA(1);
			if ( !(_la==TK_BOOLEAN || _la==TK_INT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Method_declContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DecafParserParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(DecafParserParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DecafParserParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode TK_VOID() { return getToken(DecafParserParser.TK_VOID, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public Method_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterMethod_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitMethod_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitMethod_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Method_declContext method_decl() throws RecognitionException {
		Method_declContext _localctx = new Method_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_method_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TK_BOOLEAN:
			case TK_INT:
				{
				setState(73);
				type();
				}
				break;
			case TK_VOID:
				{
				setState(74);
				match(TK_VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(77);
			match(ID);
			setState(78);
			match(LPAREN);
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TK_BOOLEAN || _la==TK_INT) {
				{
				setState(79);
				arglist();
				}
			}

			setState(82);
			match(RPAREN);
			setState(83);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArglistContext extends ParserRuleContext {
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DecafParserParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecafParserParser.COMMA, i);
		}
		public ArglistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arglist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterArglist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitArglist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitArglist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArglistContext arglist() throws RecognitionException {
		ArglistContext _localctx = new ArglistContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_arglist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			arg();
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(86);
				match(COMMA);
				setState(87);
				arg();
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(DecafParserParser.ID, 0); }
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			type();
			setState(94);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LCURLY() { return getToken(DecafParserParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(DecafParserParser.RCURLY, 0); }
		public List<Var_declContext> var_decl() {
			return getRuleContexts(Var_declContext.class);
		}
		public Var_declContext var_decl(int i) {
			return getRuleContext(Var_declContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(LCURLY);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TK_BOOLEAN || _la==TK_INT) {
				{
				{
				setState(97);
				var_decl();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TK_BREAK) | (1L << TK_CALLOUT) | (1L << TK_CONTINUE) | (1L << TK_FOR) | (1L << TK_IF) | (1L << TK_RETURN) | (1L << LCURLY) | (1L << ID))) != 0)) {
				{
				{
				setState(103);
				statement();
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(109);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_declContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(DecafParserParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DecafParserParser.ID, i);
		}
		public TerminalNode SEMICOLON() { return getToken(DecafParserParser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DecafParserParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecafParserParser.COMMA, i);
		}
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterVar_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitVar_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitVar_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_declContext var_decl() throws RecognitionException {
		Var_declContext _localctx = new Var_declContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_var_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			type();
			setState(112);
			match(ID);
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(113);
				match(COMMA);
				setState(114);
				match(ID);
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(120);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(DecafParserParser.SEMICOLON, 0); }
		public Method_callContext method_call() {
			return getRuleContext(Method_callContext.class,0);
		}
		public TerminalNode TK_IF() { return getToken(DecafParserParser.TK_IF, 0); }
		public TerminalNode LPAREN() { return getToken(DecafParserParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DecafParserParser.RPAREN, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode TK_ELSE() { return getToken(DecafParserParser.TK_ELSE, 0); }
		public TerminalNode TK_FOR() { return getToken(DecafParserParser.TK_FOR, 0); }
		public TerminalNode ID() { return getToken(DecafParserParser.ID, 0); }
		public TerminalNode COMMA() { return getToken(DecafParserParser.COMMA, 0); }
		public TerminalNode TK_RETURN() { return getToken(DecafParserParser.TK_RETURN, 0); }
		public TerminalNode TK_BREAK() { return getToken(DecafParserParser.TK_BREAK, 0); }
		public TerminalNode TK_CONTINUE() { return getToken(DecafParserParser.TK_CONTINUE, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statement);
		int _la;
		try {
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				location();
				setState(123);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(124);
				expr(0);
				setState(125);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				method_call();
				setState(128);
				match(SEMICOLON);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(130);
				match(TK_IF);
				setState(131);
				match(LPAREN);
				setState(132);
				expr(0);
				setState(133);
				match(RPAREN);
				setState(134);
				block();
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TK_ELSE) {
					{
					setState(135);
					match(TK_ELSE);
					setState(136);
					block();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(139);
				match(TK_FOR);
				setState(140);
				match(ID);
				setState(141);
				match(T__0);
				setState(142);
				expr(0);
				setState(143);
				match(COMMA);
				setState(144);
				expr(0);
				setState(145);
				block();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(147);
				match(TK_RETURN);
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << TK_CALLOUT) | (1L << LPAREN) | (1L << BOOL) | (1L << CHAR) | (1L << ID) | (1L << NUMBER))) != 0)) {
					{
					setState(148);
					expr(0);
					}
				}

				setState(151);
				match(SEMICOLON);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(152);
				match(TK_BREAK);
				setState(153);
				match(SEMICOLON);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(154);
				match(TK_CONTINUE);
				setState(155);
				match(SEMICOLON);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(156);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Method_callContext extends ParserRuleContext {
		public Method_nameContext method_name() {
			return getRuleContext(Method_nameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(DecafParserParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DecafParserParser.RPAREN, 0); }
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public TerminalNode TK_CALLOUT() { return getToken(DecafParserParser.TK_CALLOUT, 0); }
		public TerminalNode STRING() { return getToken(DecafParserParser.STRING, 0); }
		public TerminalNode COMMA() { return getToken(DecafParserParser.COMMA, 0); }
		public Callout_listContext callout_list() {
			return getRuleContext(Callout_listContext.class,0);
		}
		public Method_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterMethod_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitMethod_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitMethod_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Method_callContext method_call() throws RecognitionException {
		Method_callContext _localctx = new Method_callContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_method_call);
		int _la;
		try {
			setState(174);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				method_name();
				setState(160);
				match(LPAREN);
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << TK_CALLOUT) | (1L << LPAREN) | (1L << BOOL) | (1L << CHAR) | (1L << ID) | (1L << NUMBER))) != 0)) {
					{
					setState(161);
					expr_list();
					}
				}

				setState(164);
				match(RPAREN);
				}
				break;
			case TK_CALLOUT:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				match(TK_CALLOUT);
				setState(167);
				match(LPAREN);
				setState(168);
				match(STRING);
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(169);
					match(COMMA);
					setState(170);
					callout_list();
					}
				}

				setState(173);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Method_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DecafParserParser.ID, 0); }
		public Method_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterMethod_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitMethod_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitMethod_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Method_nameContext method_name() throws RecognitionException {
		Method_nameContext _localctx = new Method_nameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_method_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DecafParserParser.ID, 0); }
		public TerminalNode LSQUARE() { return getToken(DecafParserParser.LSQUARE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RSQUARE() { return getToken(DecafParserParser.RSQUARE, 0); }
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitLocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitLocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_location);
		try {
			setState(184);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				match(ID);
				setState(180);
				match(LSQUARE);
				setState(181);
				expr(0);
				setState(182);
				match(RSQUARE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public Method_callContext method_call() {
			return getRuleContext(Method_callContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(DecafParserParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DecafParserParser.RPAREN, 0); }
		public TerminalNode BIN_OP() { return getToken(DecafParserParser.BIN_OP, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(187);
				location();
				}
				break;
			case 2:
				{
				setState(188);
				method_call();
				}
				break;
			case 3:
				{
				setState(189);
				literal();
				}
				break;
			case 4:
				{
				setState(190);
				match(T__3);
				setState(191);
				expr(3);
				}
				break;
			case 5:
				{
				setState(192);
				match(T__4);
				setState(193);
				expr(2);
				}
				break;
			case 6:
				{
				setState(194);
				match(LPAREN);
				setState(195);
				expr(0);
				setState(196);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(205);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(200);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(201);
					match(BIN_OP);
					setState(202);
					expr(5);
					}
					} 
				}
				setState(207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Callout_listContext extends ParserRuleContext {
		public List<Callout_argContext> callout_arg() {
			return getRuleContexts(Callout_argContext.class);
		}
		public Callout_argContext callout_arg(int i) {
			return getRuleContext(Callout_argContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DecafParserParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecafParserParser.COMMA, i);
		}
		public Callout_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callout_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterCallout_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitCallout_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitCallout_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Callout_listContext callout_list() throws RecognitionException {
		Callout_listContext _localctx = new Callout_listContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_callout_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			callout_arg();
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(209);
				match(COMMA);
				setState(210);
				callout_arg();
				}
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Callout_argContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode STRING() { return getToken(DecafParserParser.STRING, 0); }
		public Callout_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callout_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterCallout_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitCallout_arg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitCallout_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Callout_argContext callout_arg() throws RecognitionException {
		Callout_argContext _localctx = new Callout_argContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_callout_arg);
		try {
			setState(218);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
			case T__4:
			case TK_CALLOUT:
			case LPAREN:
			case BOOL:
			case CHAR:
			case ID:
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(216);
				expr(0);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(217);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_listContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DecafParserParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecafParserParser.COMMA, i);
		}
		public Expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterExpr_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitExpr_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitExpr_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_listContext expr_list() throws RecognitionException {
		Expr_listContext _localctx = new Expr_listContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expr_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			expr(0);
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(221);
				match(COMMA);
				setState(222);
				expr(0);
				}
				}
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(DecafParserParser.NUMBER, 0); }
		public TerminalNode CHAR() { return getToken(DecafParserParser.CHAR, 0); }
		public TerminalNode BOOL() { return getToken(DecafParserParser.BOOL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafParserListener ) ((DecafParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafParserVisitor ) return ((DecafParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << CHAR) | (1L << NUMBER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$\u00e9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2\3\2\7\2\61\n\2\f\2"+
		"\16\2\64\13\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3<\n\3\f\3\16\3?\13\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\5\4H\n\4\3\5\3\5\3\6\3\6\5\6N\n\6\3\6\3\6\3\6\5\6"+
		"S\n\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7[\n\7\f\7\16\7^\13\7\3\b\3\b\3\b\3\t"+
		"\3\t\7\te\n\t\f\t\16\th\13\t\3\t\7\tk\n\t\f\t\16\tn\13\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\7\nv\n\n\f\n\16\ny\13\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u008c\n\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0098\n\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\5\13\u00a0\n\13\3\f\3\f\3\f\5\f\u00a5\n\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\5\f\u00ae\n\f\3\f\5\f\u00b1\n\f\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u00bb\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\5\17\u00c9\n\17\3\17\3\17\3\17\7\17\u00ce\n"+
		"\17\f\17\16\17\u00d1\13\17\3\20\3\20\3\20\7\20\u00d6\n\20\f\20\16\20\u00d9"+
		"\13\20\3\21\3\21\5\21\u00dd\n\21\3\22\3\22\3\22\7\22\u00e2\n\22\f\22\16"+
		"\22\u00e5\13\22\3\23\3\23\3\23\2\3\34\24\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$\2\5\4\2\n\n\22\22\3\2\3\5\4\2\33\34\37\37\2\u00f6\2&\3\2"+
		"\2\2\4\67\3\2\2\2\6G\3\2\2\2\bI\3\2\2\2\nM\3\2\2\2\fW\3\2\2\2\16_\3\2"+
		"\2\2\20b\3\2\2\2\22q\3\2\2\2\24\u009f\3\2\2\2\26\u00b0\3\2\2\2\30\u00b2"+
		"\3\2\2\2\32\u00ba\3\2\2\2\34\u00c8\3\2\2\2\36\u00d2\3\2\2\2 \u00dc\3\2"+
		"\2\2\"\u00de\3\2\2\2$\u00e6\3\2\2\2&\'\7\r\2\2\'(\7\36\2\2(,\7\25\2\2"+
		")+\5\4\3\2*)\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\62\3\2\2\2.,\3\2\2"+
		"\2/\61\5\n\6\2\60/\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63"+
		"\65\3\2\2\2\64\62\3\2\2\2\65\66\7\26\2\2\66\3\3\2\2\2\678\5\b\5\28=\5"+
		"\6\4\29:\7 \2\2:<\5\6\4\2;9\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>@\3"+
		"\2\2\2?=\3\2\2\2@A\7!\2\2A\5\3\2\2\2BH\7\36\2\2CD\7\36\2\2DE\7\31\2\2"+
		"EF\7\37\2\2FH\7\32\2\2GB\3\2\2\2GC\3\2\2\2H\7\3\2\2\2IJ\t\2\2\2J\t\3\2"+
		"\2\2KN\5\b\5\2LN\7\24\2\2MK\3\2\2\2ML\3\2\2\2NO\3\2\2\2OP\7\36\2\2PR\7"+
		"\27\2\2QS\5\f\7\2RQ\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\7\30\2\2UV\5\20\t\2"+
		"V\13\3\2\2\2W\\\5\16\b\2XY\7 \2\2Y[\5\16\b\2ZX\3\2\2\2[^\3\2\2\2\\Z\3"+
		"\2\2\2\\]\3\2\2\2]\r\3\2\2\2^\\\3\2\2\2_`\5\b\5\2`a\7\36\2\2a\17\3\2\2"+
		"\2bf\7\25\2\2ce\5\22\n\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gl\3\2"+
		"\2\2hf\3\2\2\2ik\5\24\13\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3"+
		"\2\2\2nl\3\2\2\2op\7\26\2\2p\21\3\2\2\2qr\5\b\5\2rw\7\36\2\2st\7 \2\2"+
		"tv\7\36\2\2us\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2"+
		"\2z{\7!\2\2{\23\3\2\2\2|}\5\32\16\2}~\t\3\2\2~\177\5\34\17\2\177\u0080"+
		"\7!\2\2\u0080\u00a0\3\2\2\2\u0081\u0082\5\26\f\2\u0082\u0083\7!\2\2\u0083"+
		"\u00a0\3\2\2\2\u0084\u0085\7\21\2\2\u0085\u0086\7\27\2\2\u0086\u0087\5"+
		"\34\17\2\u0087\u0088\7\30\2\2\u0088\u008b\5\20\t\2\u0089\u008a\7\17\2"+
		"\2\u008a\u008c\5\20\t\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u00a0\3\2\2\2\u008d\u008e\7\20\2\2\u008e\u008f\7\36\2\2\u008f\u0090\7"+
		"\3\2\2\u0090\u0091\5\34\17\2\u0091\u0092\7 \2\2\u0092\u0093\5\34\17\2"+
		"\u0093\u0094\5\20\t\2\u0094\u00a0\3\2\2\2\u0095\u0097\7\23\2\2\u0096\u0098"+
		"\5\34\17\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\3\2\2\2"+
		"\u0099\u00a0\7!\2\2\u009a\u009b\7\13\2\2\u009b\u00a0\7!\2\2\u009c\u009d"+
		"\7\16\2\2\u009d\u00a0\7!\2\2\u009e\u00a0\5\20\t\2\u009f|\3\2\2\2\u009f"+
		"\u0081\3\2\2\2\u009f\u0084\3\2\2\2\u009f\u008d\3\2\2\2\u009f\u0095\3\2"+
		"\2\2\u009f\u009a\3\2\2\2\u009f\u009c\3\2\2\2\u009f\u009e\3\2\2\2\u00a0"+
		"\25\3\2\2\2\u00a1\u00a2\5\30\r\2\u00a2\u00a4\7\27\2\2\u00a3\u00a5\5\""+
		"\22\2\u00a4\u00a3\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6"+
		"\u00a7\7\30\2\2\u00a7\u00b1\3\2\2\2\u00a8\u00a9\7\f\2\2\u00a9\u00aa\7"+
		"\27\2\2\u00aa\u00ad\7\35\2\2\u00ab\u00ac\7 \2\2\u00ac\u00ae\5\36\20\2"+
		"\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1"+
		"\7\30\2\2\u00b0\u00a1\3\2\2\2\u00b0\u00a8\3\2\2\2\u00b1\27\3\2\2\2\u00b2"+
		"\u00b3\7\36\2\2\u00b3\31\3\2\2\2\u00b4\u00bb\7\36\2\2\u00b5\u00b6\7\36"+
		"\2\2\u00b6\u00b7\7\31\2\2\u00b7\u00b8\5\34\17\2\u00b8\u00b9\7\32\2\2\u00b9"+
		"\u00bb\3\2\2\2\u00ba\u00b4\3\2\2\2\u00ba\u00b5\3\2\2\2\u00bb\33\3\2\2"+
		"\2\u00bc\u00bd\b\17\1\2\u00bd\u00c9\5\32\16\2\u00be\u00c9\5\26\f\2\u00bf"+
		"\u00c9\5$\23\2\u00c0\u00c1\7\6\2\2\u00c1\u00c9\5\34\17\5\u00c2\u00c3\7"+
		"\7\2\2\u00c3\u00c9\5\34\17\4\u00c4\u00c5\7\27\2\2\u00c5\u00c6\5\34\17"+
		"\2\u00c6\u00c7\7\30\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00bc\3\2\2\2\u00c8"+
		"\u00be\3\2\2\2\u00c8\u00bf\3\2\2\2\u00c8\u00c0\3\2\2\2\u00c8\u00c2\3\2"+
		"\2\2\u00c8\u00c4\3\2\2\2\u00c9\u00cf\3\2\2\2\u00ca\u00cb\f\6\2\2\u00cb"+
		"\u00cc\7#\2\2\u00cc\u00ce\5\34\17\7\u00cd\u00ca\3\2\2\2\u00ce\u00d1\3"+
		"\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\35\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d2\u00d7\5 \21\2\u00d3\u00d4\7 \2\2\u00d4\u00d6\5 \21"+
		"\2\u00d5\u00d3\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8"+
		"\3\2\2\2\u00d8\37\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00dd\5\34\17\2\u00db"+
		"\u00dd\7\35\2\2\u00dc\u00da\3\2\2\2\u00dc\u00db\3\2\2\2\u00dd!\3\2\2\2"+
		"\u00de\u00e3\5\34\17\2\u00df\u00e0\7 \2\2\u00e0\u00e2\5\34\17\2\u00e1"+
		"\u00df\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2"+
		"\2\2\u00e4#\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\t\4\2\2\u00e7%\3\2"+
		"\2\2\30,\62=GMR\\flw\u008b\u0097\u009f\u00a4\u00ad\u00b0\u00ba\u00c8\u00cf"+
		"\u00d7\u00dc\u00e3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}