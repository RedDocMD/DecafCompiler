// Generated from /home/deep/work/kotlin/DecafKotlin/src/grammar/Decaf.g4 by ANTLR 4.8
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
public class DecafParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		COMMENT=18, WS=19, TK_BOOLEAN=20, TK_BREAK=21, TK_CALLOUT=22, TK_CLASS=23, 
		TK_CONTINUE=24, TK_ELSE=25, TK_FOR=26, TK_IF=27, TK_INT=28, TK_RETURN=29, 
		TK_VOID=30, LCURLY=31, RCURLY=32, LPAREN=33, RPAREN=34, LSQUARE=35, RSQUARE=36, 
		BOOL=37, CHAR=38, STRING=39, ID=40, NUMBER=41, COMMA=42, SEMICOLON=43, 
		DOT=44, BIN_OP=45, ASSIGN_OP=46;
	public static final int
		RULE_program = 0, RULE_field_decl = 1, RULE_field_name = 2, RULE_type = 3, 
		RULE_method_decl = 4, RULE_arglist = 5, RULE_arg = 6, RULE_block = 7, 
		RULE_var_decl = 8, RULE_statement = 9, RULE_assign_op = 10, RULE_method_call = 11, 
		RULE_method_name = 12, RULE_location = 13, RULE_expr = 14, RULE_callout_list = 15, 
		RULE_callout_arg = 16, RULE_expr_list = 17, RULE_literal = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "field_decl", "field_name", "type", "method_decl", "arglist", 
			"arg", "block", "var_decl", "statement", "assign_op", "method_call", 
			"method_name", "location", "expr", "callout_list", "callout_arg", "expr_list", 
			"literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'+='", "'-='", "'*'", "'/'", "'%'", "'+'", "'-'", "'<'", 
			"'>'", "'<='", "'>='", "'&&'", "'||'", "'=='", "'!='", "'!'", null, null, 
			"'boolean'", "'break'", "'callout'", "'class'", "'continue'", "'else'", 
			"'for'", "'if'", "'int'", "'return'", "'void'", "'{'", "'}'", "'('", 
			"')'", "'['", "']'", null, null, null, null, null, "','", "';'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
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
	public String getGrammarFileName() { return "Decaf.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DecafParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode TK_CLASS() { return getToken(DecafParser.TK_CLASS, 0); }
		public TerminalNode ID() { return getToken(DecafParser.ID, 0); }
		public TerminalNode LCURLY() { return getToken(DecafParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(DecafParser.RCURLY, 0); }
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
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitProgram(this);
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
			setState(38);
			match(TK_CLASS);
			setState(39);
			match(ID);
			setState(40);
			match(LCURLY);
			setState(44);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(41);
					field_decl();
					}
					} 
				}
				setState(46);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TK_BOOLEAN) | (1L << TK_INT) | (1L << TK_VOID))) != 0)) {
				{
				{
				setState(47);
				method_decl();
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
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
		public TerminalNode SEMICOLON() { return getToken(DecafParser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DecafParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecafParser.COMMA, i);
		}
		public Field_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterField_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitField_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitField_decl(this);
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
			setState(55);
			type();
			setState(56);
			field_name();
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(57);
				match(COMMA);
				setState(58);
				field_name();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
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
		public TerminalNode ID() { return getToken(DecafParser.ID, 0); }
		public TerminalNode LSQUARE() { return getToken(DecafParser.LSQUARE, 0); }
		public TerminalNode NUMBER() { return getToken(DecafParser.NUMBER, 0); }
		public TerminalNode RSQUARE() { return getToken(DecafParser.RSQUARE, 0); }
		public Field_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterField_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitField_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitField_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Field_nameContext field_name() throws RecognitionException {
		Field_nameContext _localctx = new Field_nameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_field_name);
		try {
			setState(71);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				match(ID);
				setState(68);
				match(LSQUARE);
				setState(69);
				match(NUMBER);
				setState(70);
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
		public TerminalNode TK_BOOLEAN() { return getToken(DecafParser.TK_BOOLEAN, 0); }
		public TerminalNode TK_INT() { return getToken(DecafParser.TK_INT, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitType(this);
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
			setState(73);
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
		public TerminalNode ID() { return getToken(DecafParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(DecafParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DecafParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode TK_VOID() { return getToken(DecafParser.TK_VOID, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public Method_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterMethod_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitMethod_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitMethod_decl(this);
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
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TK_BOOLEAN:
			case TK_INT:
				{
				setState(75);
				type();
				}
				break;
			case TK_VOID:
				{
				setState(76);
				match(TK_VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(79);
			match(ID);
			setState(80);
			match(LPAREN);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TK_BOOLEAN || _la==TK_INT) {
				{
				setState(81);
				arglist();
				}
			}

			setState(84);
			match(RPAREN);
			setState(85);
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
		public List<TerminalNode> COMMA() { return getTokens(DecafParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecafParser.COMMA, i);
		}
		public ArglistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arglist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterArglist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitArglist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitArglist(this);
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
			setState(87);
			arg();
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(88);
				match(COMMA);
				setState(89);
				arg();
				}
				}
				setState(94);
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
		public TerminalNode ID() { return getToken(DecafParser.ID, 0); }
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			type();
			setState(96);
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
		public TerminalNode LCURLY() { return getToken(DecafParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(DecafParser.RCURLY, 0); }
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
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitBlock(this);
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
			setState(98);
			match(LCURLY);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TK_BOOLEAN || _la==TK_INT) {
				{
				{
				setState(99);
				var_decl();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TK_BREAK) | (1L << TK_CALLOUT) | (1L << TK_CONTINUE) | (1L << TK_FOR) | (1L << TK_IF) | (1L << TK_RETURN) | (1L << LCURLY) | (1L << ID))) != 0)) {
				{
				{
				setState(105);
				statement();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
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
		public List<TerminalNode> ID() { return getTokens(DecafParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DecafParser.ID, i);
		}
		public TerminalNode SEMICOLON() { return getToken(DecafParser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DecafParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecafParser.COMMA, i);
		}
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterVar_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitVar_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitVar_decl(this);
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
			setState(113);
			type();
			setState(114);
			match(ID);
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(115);
				match(COMMA);
				setState(116);
				match(ID);
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
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
		public Assign_opContext assign_op() {
			return getRuleContext(Assign_opContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(DecafParser.SEMICOLON, 0); }
		public Method_callContext method_call() {
			return getRuleContext(Method_callContext.class,0);
		}
		public TerminalNode TK_IF() { return getToken(DecafParser.TK_IF, 0); }
		public TerminalNode LPAREN() { return getToken(DecafParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DecafParser.RPAREN, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode TK_ELSE() { return getToken(DecafParser.TK_ELSE, 0); }
		public TerminalNode TK_FOR() { return getToken(DecafParser.TK_FOR, 0); }
		public TerminalNode ID() { return getToken(DecafParser.ID, 0); }
		public TerminalNode COMMA() { return getToken(DecafParser.COMMA, 0); }
		public TerminalNode TK_RETURN() { return getToken(DecafParser.TK_RETURN, 0); }
		public TerminalNode TK_BREAK() { return getToken(DecafParser.TK_BREAK, 0); }
		public TerminalNode TK_CONTINUE() { return getToken(DecafParser.TK_CONTINUE, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statement);
		int _la;
		try {
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				location();
				setState(125);
				assign_op();
				setState(126);
				expr(0);
				setState(127);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				method_call();
				setState(130);
				match(SEMICOLON);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(132);
				match(TK_IF);
				setState(133);
				match(LPAREN);
				setState(134);
				expr(0);
				setState(135);
				match(RPAREN);
				setState(136);
				block();
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TK_ELSE) {
					{
					setState(137);
					match(TK_ELSE);
					setState(138);
					block();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(141);
				match(TK_FOR);
				setState(142);
				match(ID);
				setState(143);
				match(T__0);
				setState(144);
				expr(0);
				setState(145);
				match(COMMA);
				setState(146);
				expr(0);
				setState(147);
				block();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(149);
				match(TK_RETURN);
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__16) | (1L << TK_CALLOUT) | (1L << LPAREN) | (1L << BOOL) | (1L << CHAR) | (1L << ID) | (1L << NUMBER))) != 0)) {
					{
					setState(150);
					expr(0);
					}
				}

				setState(153);
				match(SEMICOLON);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(154);
				match(TK_BREAK);
				setState(155);
				match(SEMICOLON);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(156);
				match(TK_CONTINUE);
				setState(157);
				match(SEMICOLON);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(158);
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

	public static class Assign_opContext extends ParserRuleContext {
		public Assign_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterAssign_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitAssign_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitAssign_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_opContext assign_op() throws RecognitionException {
		Assign_opContext _localctx = new Assign_opContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assign_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2))) != 0)) ) {
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

	public static class Method_callContext extends ParserRuleContext {
		public Method_nameContext method_name() {
			return getRuleContext(Method_nameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(DecafParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DecafParser.RPAREN, 0); }
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public TerminalNode TK_CALLOUT() { return getToken(DecafParser.TK_CALLOUT, 0); }
		public TerminalNode STRING() { return getToken(DecafParser.STRING, 0); }
		public TerminalNode COMMA() { return getToken(DecafParser.COMMA, 0); }
		public Callout_listContext callout_list() {
			return getRuleContext(Callout_listContext.class,0);
		}
		public Method_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterMethod_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitMethod_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitMethod_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Method_callContext method_call() throws RecognitionException {
		Method_callContext _localctx = new Method_callContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_method_call);
		int _la;
		try {
			setState(178);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				method_name();
				setState(164);
				match(LPAREN);
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__16) | (1L << TK_CALLOUT) | (1L << LPAREN) | (1L << BOOL) | (1L << CHAR) | (1L << ID) | (1L << NUMBER))) != 0)) {
					{
					setState(165);
					expr_list();
					}
				}

				setState(168);
				match(RPAREN);
				}
				break;
			case TK_CALLOUT:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				match(TK_CALLOUT);
				setState(171);
				match(LPAREN);
				setState(172);
				match(STRING);
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(173);
					match(COMMA);
					setState(174);
					callout_list();
					}
				}

				setState(177);
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
		public TerminalNode ID() { return getToken(DecafParser.ID, 0); }
		public Method_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterMethod_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitMethod_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitMethod_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Method_nameContext method_name() throws RecognitionException {
		Method_nameContext _localctx = new Method_nameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_method_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
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
		public TerminalNode ID() { return getToken(DecafParser.ID, 0); }
		public TerminalNode LSQUARE() { return getToken(DecafParser.LSQUARE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RSQUARE() { return getToken(DecafParser.RSQUARE, 0); }
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitLocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitLocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_location);
		try {
			setState(188);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				match(ID);
				setState(184);
				match(LSQUARE);
				setState(185);
				expr(0);
				setState(186);
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
		public TerminalNode LPAREN() { return getToken(DecafParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DecafParser.RPAREN, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitExpr(this);
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
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(191);
				location();
				}
				break;
			case 2:
				{
				setState(192);
				method_call();
				}
				break;
			case 3:
				{
				setState(193);
				literal();
				}
				break;
			case 4:
				{
				setState(194);
				match(T__7);
				setState(195);
				expr(3);
				}
				break;
			case 5:
				{
				setState(196);
				match(T__16);
				setState(197);
				expr(2);
				}
				break;
			case 6:
				{
				setState(198);
				match(LPAREN);
				setState(199);
				expr(0);
				setState(200);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(221);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(219);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(204);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(205);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(206);
						expr(9);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(207);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(208);
						_la = _input.LA(1);
						if ( !(_la==T__6 || _la==T__7) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(209);
						expr(8);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(210);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(211);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(212);
						expr(7);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(213);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(214);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__13) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(215);
						expr(6);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(216);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(217);
						_la = _input.LA(1);
						if ( !(_la==T__14 || _la==T__15) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(218);
						expr(5);
						}
						break;
					}
					} 
				}
				setState(223);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
		public List<TerminalNode> COMMA() { return getTokens(DecafParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecafParser.COMMA, i);
		}
		public Callout_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callout_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterCallout_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitCallout_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitCallout_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Callout_listContext callout_list() throws RecognitionException {
		Callout_listContext _localctx = new Callout_listContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_callout_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			callout_arg();
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(225);
				match(COMMA);
				setState(226);
				callout_arg();
				}
				}
				setState(231);
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
		public TerminalNode STRING() { return getToken(DecafParser.STRING, 0); }
		public Callout_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callout_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterCallout_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitCallout_arg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitCallout_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Callout_argContext callout_arg() throws RecognitionException {
		Callout_argContext _localctx = new Callout_argContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_callout_arg);
		try {
			setState(234);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
			case T__16:
			case TK_CALLOUT:
			case LPAREN:
			case BOOL:
			case CHAR:
			case ID:
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(232);
				expr(0);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
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
		public List<TerminalNode> COMMA() { return getTokens(DecafParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DecafParser.COMMA, i);
		}
		public Expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterExpr_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitExpr_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitExpr_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_listContext expr_list() throws RecognitionException {
		Expr_listContext _localctx = new Expr_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expr_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			expr(0);
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(237);
				match(COMMA);
				setState(238);
				expr(0);
				}
				}
				setState(243);
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
		public TerminalNode NUMBER() { return getToken(DecafParser.NUMBER, 0); }
		public TerminalNode CHAR() { return getToken(DecafParser.CHAR, 0); }
		public TerminalNode BOOL() { return getToken(DecafParser.BOOL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecafListener ) ((DecafListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DecafVisitor ) return ((DecafVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
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
		case 14:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\60\u00f9\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\7\2-\n\2\f\2\16\2\60\13\2\3\2\7\2"+
		"\63\n\2\f\2\16\2\66\13\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3>\n\3\f\3\16\3A\13"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\5\4J\n\4\3\5\3\5\3\6\3\6\5\6P\n\6\3\6\3"+
		"\6\3\6\5\6U\n\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7]\n\7\f\7\16\7`\13\7\3\b\3"+
		"\b\3\b\3\t\3\t\7\tg\n\t\f\t\16\tj\13\t\3\t\7\tm\n\t\f\t\16\tp\13\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\7\nx\n\n\f\n\16\n{\13\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u008e"+
		"\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u009a\n\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a2\n\13\3\f\3\f\3\r\3\r\3\r\5\r"+
		"\u00a9\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00b2\n\r\3\r\5\r\u00b5\n\r"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00bf\n\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00cd\n\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20"+
		"\u00de\n\20\f\20\16\20\u00e1\13\20\3\21\3\21\3\21\7\21\u00e6\n\21\f\21"+
		"\16\21\u00e9\13\21\3\22\3\22\5\22\u00ed\n\22\3\23\3\23\3\23\7\23\u00f2"+
		"\n\23\f\23\16\23\u00f5\13\23\3\24\3\24\3\24\2\3\36\25\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&\2\n\4\2\26\26\36\36\3\2\3\5\3\2\6\b\3\2\t\n"+
		"\3\2\13\16\3\2\17\20\3\2\21\22\4\2\'(++\2\u0109\2(\3\2\2\2\49\3\2\2\2"+
		"\6I\3\2\2\2\bK\3\2\2\2\nO\3\2\2\2\fY\3\2\2\2\16a\3\2\2\2\20d\3\2\2\2\22"+
		"s\3\2\2\2\24\u00a1\3\2\2\2\26\u00a3\3\2\2\2\30\u00b4\3\2\2\2\32\u00b6"+
		"\3\2\2\2\34\u00be\3\2\2\2\36\u00cc\3\2\2\2 \u00e2\3\2\2\2\"\u00ec\3\2"+
		"\2\2$\u00ee\3\2\2\2&\u00f6\3\2\2\2()\7\31\2\2)*\7*\2\2*.\7!\2\2+-\5\4"+
		"\3\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\64\3\2\2\2\60.\3\2\2\2"+
		"\61\63\5\n\6\2\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2"+
		"\65\67\3\2\2\2\66\64\3\2\2\2\678\7\"\2\28\3\3\2\2\29:\5\b\5\2:?\5\6\4"+
		"\2;<\7,\2\2<>\5\6\4\2=;\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@B\3\2\2"+
		"\2A?\3\2\2\2BC\7-\2\2C\5\3\2\2\2DJ\7*\2\2EF\7*\2\2FG\7%\2\2GH\7+\2\2H"+
		"J\7&\2\2ID\3\2\2\2IE\3\2\2\2J\7\3\2\2\2KL\t\2\2\2L\t\3\2\2\2MP\5\b\5\2"+
		"NP\7 \2\2OM\3\2\2\2ON\3\2\2\2PQ\3\2\2\2QR\7*\2\2RT\7#\2\2SU\5\f\7\2TS"+
		"\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\7$\2\2WX\5\20\t\2X\13\3\2\2\2Y^\5\16\b"+
		"\2Z[\7,\2\2[]\5\16\b\2\\Z\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_\r\3"+
		"\2\2\2`^\3\2\2\2ab\5\b\5\2bc\7*\2\2c\17\3\2\2\2dh\7!\2\2eg\5\22\n\2fe"+
		"\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2in\3\2\2\2jh\3\2\2\2km\5\24\13\2"+
		"lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2pn\3\2\2\2qr\7\"\2\2"+
		"r\21\3\2\2\2st\5\b\5\2ty\7*\2\2uv\7,\2\2vx\7*\2\2wu\3\2\2\2x{\3\2\2\2"+
		"yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|}\7-\2\2}\23\3\2\2\2~\177\5\34"+
		"\17\2\177\u0080\5\26\f\2\u0080\u0081\5\36\20\2\u0081\u0082\7-\2\2\u0082"+
		"\u00a2\3\2\2\2\u0083\u0084\5\30\r\2\u0084\u0085\7-\2\2\u0085\u00a2\3\2"+
		"\2\2\u0086\u0087\7\35\2\2\u0087\u0088\7#\2\2\u0088\u0089\5\36\20\2\u0089"+
		"\u008a\7$\2\2\u008a\u008d\5\20\t\2\u008b\u008c\7\33\2\2\u008c\u008e\5"+
		"\20\t\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u00a2\3\2\2\2\u008f"+
		"\u0090\7\34\2\2\u0090\u0091\7*\2\2\u0091\u0092\7\3\2\2\u0092\u0093\5\36"+
		"\20\2\u0093\u0094\7,\2\2\u0094\u0095\5\36\20\2\u0095\u0096\5\20\t\2\u0096"+
		"\u00a2\3\2\2\2\u0097\u0099\7\37\2\2\u0098\u009a\5\36\20\2\u0099\u0098"+
		"\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u00a2\7-\2\2\u009c"+
		"\u009d\7\27\2\2\u009d\u00a2\7-\2\2\u009e\u009f\7\32\2\2\u009f\u00a2\7"+
		"-\2\2\u00a0\u00a2\5\20\t\2\u00a1~\3\2\2\2\u00a1\u0083\3\2\2\2\u00a1\u0086"+
		"\3\2\2\2\u00a1\u008f\3\2\2\2\u00a1\u0097\3\2\2\2\u00a1\u009c\3\2\2\2\u00a1"+
		"\u009e\3\2\2\2\u00a1\u00a0\3\2\2\2\u00a2\25\3\2\2\2\u00a3\u00a4\t\3\2"+
		"\2\u00a4\27\3\2\2\2\u00a5\u00a6\5\32\16\2\u00a6\u00a8\7#\2\2\u00a7\u00a9"+
		"\5$\23\2\u00a8\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00ab\7$\2\2\u00ab\u00b5\3\2\2\2\u00ac\u00ad\7\30\2\2\u00ad\u00ae\7#"+
		"\2\2\u00ae\u00b1\7)\2\2\u00af\u00b0\7,\2\2\u00b0\u00b2\5 \21\2\u00b1\u00af"+
		"\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b5\7$\2\2\u00b4"+
		"\u00a5\3\2\2\2\u00b4\u00ac\3\2\2\2\u00b5\31\3\2\2\2\u00b6\u00b7\7*\2\2"+
		"\u00b7\33\3\2\2\2\u00b8\u00bf\7*\2\2\u00b9\u00ba\7*\2\2\u00ba\u00bb\7"+
		"%\2\2\u00bb\u00bc\5\36\20\2\u00bc\u00bd\7&\2\2\u00bd\u00bf\3\2\2\2\u00be"+
		"\u00b8\3\2\2\2\u00be\u00b9\3\2\2\2\u00bf\35\3\2\2\2\u00c0\u00c1\b\20\1"+
		"\2\u00c1\u00cd\5\34\17\2\u00c2\u00cd\5\30\r\2\u00c3\u00cd\5&\24\2\u00c4"+
		"\u00c5\7\n\2\2\u00c5\u00cd\5\36\20\5\u00c6\u00c7\7\23\2\2\u00c7\u00cd"+
		"\5\36\20\4\u00c8\u00c9\7#\2\2\u00c9\u00ca\5\36\20\2\u00ca\u00cb\7$\2\2"+
		"\u00cb\u00cd\3\2\2\2\u00cc\u00c0\3\2\2\2\u00cc\u00c2\3\2\2\2\u00cc\u00c3"+
		"\3\2\2\2\u00cc\u00c4\3\2\2\2\u00cc\u00c6\3\2\2\2\u00cc\u00c8\3\2\2\2\u00cd"+
		"\u00df\3\2\2\2\u00ce\u00cf\f\n\2\2\u00cf\u00d0\t\4\2\2\u00d0\u00de\5\36"+
		"\20\13\u00d1\u00d2\f\t\2\2\u00d2\u00d3\t\5\2\2\u00d3\u00de\5\36\20\n\u00d4"+
		"\u00d5\f\b\2\2\u00d5\u00d6\t\6\2\2\u00d6\u00de\5\36\20\t\u00d7\u00d8\f"+
		"\7\2\2\u00d8\u00d9\t\7\2\2\u00d9\u00de\5\36\20\b\u00da\u00db\f\6\2\2\u00db"+
		"\u00dc\t\b\2\2\u00dc\u00de\5\36\20\7\u00dd\u00ce\3\2\2\2\u00dd\u00d1\3"+
		"\2\2\2\u00dd\u00d4\3\2\2\2\u00dd\u00d7\3\2\2\2\u00dd\u00da\3\2\2\2\u00de"+
		"\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\37\3\2\2"+
		"\2\u00e1\u00df\3\2\2\2\u00e2\u00e7\5\"\22\2\u00e3\u00e4\7,\2\2\u00e4\u00e6"+
		"\5\"\22\2\u00e5\u00e3\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2"+
		"\u00e7\u00e8\3\2\2\2\u00e8!\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00ed\5"+
		"\36\20\2\u00eb\u00ed\7)\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00eb\3\2\2\2\u00ed"+
		"#\3\2\2\2\u00ee\u00f3\5\36\20\2\u00ef\u00f0\7,\2\2\u00f0\u00f2\5\36\20"+
		"\2\u00f1\u00ef\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4"+
		"\3\2\2\2\u00f4%\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f7\t\t\2\2\u00f7"+
		"\'\3\2\2\2\31.\64?IOT^hny\u008d\u0099\u00a1\u00a8\u00b1\u00b4\u00be\u00cc"+
		"\u00dd\u00df\u00e7\u00ec\u00f3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}