module Main where
import Alex
import Happy

main = do
    s <- getContents
    let tree = (alexScanTokens s)
    print (parse tree)