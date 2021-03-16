{
module Alex where
import Debug.Trace
}

%wrapper "basic"

$digit = [0-9]
@number = $digit+(\.$digit+)?

tokens :-

  @number {\s -> TokenNum (read s)}
  $white+	  ;
  \+  {\s -> TokenPlus }
  \-  {\s -> TokenMinus }
  \*  {\s -> TokenMul }
  \/  {\s -> TokenDiv }
  \{  {\s -> TokenCurlyOpen }
  \}  {\s -> TokenCurlyClosed }
  \[  {\s -> TokenSquareOpen }
  \]  {\s -> TokenSquareClosed }
  \(  {\s -> TokenRoundOpen }
  \)  {\s -> TokenRoundClosed }
{
-- Each action has type :: String -> Token

-- The token type:
data Token = TokenNum Float
           | TokenPlus
           | TokenMinus
           | TokenMul
           | TokenDiv
           | TokenCurlyOpen
           | TokenCurlyClosed
           | TokenSquareOpen
           | TokenSquareClosed
           | TokenRoundOpen
           | TokenRoundClosed deriving (Eq, Show)
}