{
module Happy where
import Alex
}
%name parse
%tokentype { Token }
%error { parseError }
%token
    num { TokenNum $$ }

    '+' { TokenPlus }
    '-' { TokenMinus }
    '*' { TokenMul }
    '/' { TokenDiv }

    '{' { TokenCurlyOpen } 
    '}' { TokenCurlyClosed }
    '[' { TokenSquareOpen } 
    ']' { TokenSquareClosed }
    '(' { TokenRoundOpen } 
    ')' { TokenRoundClosed }
%left '+' '-' '*' '/'
%%

S : '{' G '}' { $2 }
  | '[' Q ']' { $2 }
  | '(' T ')' { $2 }
  | S OP S    { $2 $1 $3 }
  | E         { $1 }

G : '[' Q ']' { $2 }
  | '(' T ')' { $2 }
  | G OP G    { $2 $1 $3 }
  | E         { $1 }

Q : '(' T ')' { $2 }
  | Q OP Q    { $2 $1 $3 }
  | E         { $1 }

T : '(' T ')' { $2 }
  | T OP T    { $2 $1 $3 }
  | E         { $1 }

E : num { $1 }

OP : '+' { (+) }
   | '-' { (-) }
   | '*' { (*) }
   | '/' { (/) }

{
parseError :: [Token] -> a
parseError _ = error "Parse error"


}