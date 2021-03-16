{
module Alex where
}

%wrapper "basic"

$digit = [0-9]
$alpha = [a-zA-Z]

tokens :-
  
  $white+				;
  $digit+ { \s -> TokenNum (read s)}
  \+ { \s -> TokenPlus }
  \- { \s -> TokenMinus }
  
{
-- Each action has type :: String -> Token

-- The token type:
data Token = TokenNum Int | TokenPlus | TokenMinus
    deriving (Eq, Show) 
}
