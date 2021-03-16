module Main where
import Alex
import Happy

main = do
    let corretta = "{14.0 + (28.0) *[12+24 + (22 + ((22)))]} * [22.0 + (23 - 2)]"
    --let scorretta = "{14.0 + (28.0) * [12 + {24} + (22 + ((22)))]} * [22.0 + (23 - 2)]"
    --s <- getContents
    print (parse (alexScanTokens corretta))