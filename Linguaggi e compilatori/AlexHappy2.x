{
module Alex where
}

%wrapper "basic"

tokens :-

  $white+	;
  \=   { \s -> TokenEquals }
  let { \s -> TokenLet }
  case { \s -> TokenCase }
  of   { \s -> TokenOf }
  in   { \s -> TokenIn }
  "->" { \s -> TokenArrow }
  \(   { \s -> TokenRoundOpen }    
  \)   { \s -> TokenRoundClosed } 

  [a-zA-Z0-9]+ {\s -> TokenIdentifier s }
{

-- The token type:
data Token = TokenIdentifier String
           | TokenLet 
           | TokenCase
           | TokenOf
           | TokenIn
           | TokenArrow
           | TokenRoundOpen
           | TokenRoundClosed
           | TokenEquals deriving (Eq, Show)
}