{
module Main (main) where
}

%wrapper "basic"

$digit = 0-9
$alpha = [a-zA-Z]

tokens :-

  $white+				;
  var|function|procedure { \s -> Keyword }
  while|for|do          { \s -> Keyword }
  if|then|else		    { \s -> Keyword }
  [a-z]($alpha*\_*\-*)*	 { \s -> Identifier s }
  $digit+				{ \s -> Const (read s) }
  (\+{1,2}|\-{1,2}|\={1,2})		{ \s -> Op s }

{
-- Each action has type :: String -> Token

-- The token type:
data Token = Keyword  |
    Identifier String |
    Op String |
    Const Int deriving (Eq, Show) 
	
main = do
  s <- getContents
  print (alexScanTokens s)
}