{
module Happy where
import Alex
}
%name parse
%tokentype { Token }
%error { parseError }
%token
    id { TokenIdentifier $$ }
    '=' { TokenEquals }
    'let' { TokenLet }
    'case' { TokenCase }
    'of' { TokenOf }
    'in' { TokenIn }
    '->' { TokenArrow }
    '(' { TokenRoundOpen }
    ')' { TokenRoundClosed }

%%

Let : 'let' Identifier '=' Let 'in' Let { LetIn $2 $4 $6 }
    | 'let' Identifier '=' Let           { Let $2 $4 }
    | Expr                               { Expression $1 }

Expr : 'case' Expr 'of' Match       { Case $2 $4 }
     | '(' Expr ')'  { Round $2 }
     | Identifier { Var $1 } 
     | Application { App $1 }

Match : Match Expr '->' Expr       { (Match $2 $4) : $1 }
      | Expr '->' Expr               { [(Match $1 $3)] }

Identifier : id { Identifier $1 }
Application : Identifier Application { Apply $1 $2 }
            | Identifier             { Identity $1 }

{
parseError :: [Token] -> a
parseError _ = error "Parse error"

data Let = LetIn Identifier Let Let 
         | Let Identifier Let 
         | Expression Expr deriving (Show)
data Match = Match Expr Expr deriving (Show)

data Identifier = Identifier String deriving (Show)
data Application = Apply Identifier Application 
                 | Identity Identifier deriving (Show)
data Expr = Case Expr [Match]
          | Round Expr
          | App Application
          | Var Identifier deriving (Show)

}