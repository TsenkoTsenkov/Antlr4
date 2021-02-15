 grammar LabeledExpr;
 import CommonLexerRules;

 prog: stat+;

 stat: expr NEWLINE # printExpr
 | ID '=' expr NEWLINE # assign
 | 'clear'             #clear
 | NEWLINE             # blank
 ;
 expr: expr op=('*'|'/') expr  #MulDiv
 | expr op=('+'|'-') expr      #AddSub
 | INT                      # int
 | ID                       # id
 | '(' expr ')'             #parens
 ;

 MUL : '*' ; // assigns token name to '*' used above in grammar
 DIV : '/' ;
 ADD : '+' ;
 SUB : '-' ;