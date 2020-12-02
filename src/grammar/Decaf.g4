grammar Decaf;
import DecafTokens;

program :  TK_CLASS ID LCURLY field_decl* method_decl* RCURLY;
field_decl : type field_name (COMMA field_name)* SEMICOLON;
field_name : ID | ID LSQUARE NUMBER RSQUARE;
type : TK_BOOLEAN | TK_INT;
method_decl : (type | TK_VOID) ID LPAREN arglist? RPAREN block;
arglist : arg (COMMA arg)*;
arg : type ID;
block : LCURLY var_decl* statement* RCURLY;
var_decl : type ID (COMMA ID)* SEMICOLON;
statement : location assign_op expr SEMICOLON
            | method_call SEMICOLON
            | TK_IF LPAREN expr RPAREN block (TK_ELSE block)?
            | TK_FOR ID '=' expr COMMA expr block
            | TK_RETURN expr? SEMICOLON
            | TK_BREAK  SEMICOLON
            | TK_CONTINUE SEMICOLON
            | block;
assign_op : '=' | '+=' | '-=';
method_call : method_name LPAREN expr_list? RPAREN
              | TK_CALLOUT LPAREN STRING (COMMA callout_list)? RPAREN;
method_name : ID;
location : ID
          | ID LSQUARE expr RSQUARE;
expr : location
      | method_call
      | literal
      | expr ('*' | '/' | '%') expr
      | expr ('+' | '-') expr
      | expr ('<' | '>' | '<=' | '>=') expr
      | expr ('&&' | '||') expr
      | expr ('==' | '!=') expr
      | '-' expr
      | '!' expr
      | LPAREN expr RPAREN;
callout_list : callout_arg (COMMA callout_arg)*;
callout_arg : expr | STRING;
expr_list : expr (COMMA expr)*;
literal : NUMBER | CHAR | BOOL;