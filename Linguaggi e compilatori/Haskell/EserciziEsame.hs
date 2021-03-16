
remove :: [[a]] -> [[a]]
remove [] = []
remove (x:xs) = map (\x -> tail x) xs

diagonal :: [[a]] -> [a]
diagonal [] = []
diagonal (x:xs) = (head x) : diagonal (remove (x:xs))

scacchiera a = [ [ (mod (x+y) 2) | y <- [1..a]] | x <- [1..a]]


takeTwo :: (Ord a) => [a] -> [a] -> [a]
takeTwo _ [] = []
takeTwo [] _ = []
takeTwo (x:xs) (y:ys) | (x == y) = x : takeTwo xs ys
                      | (x > y) =  takeTwo (x:xs) (ys)
                      | (x < y) =  takeTwo (xs) (y:ys)

matTakeTwo :: (Ord a) => [[a]] -> [a]
matTakeTwo l = foldl (\acc y -> takeTwo acc y) (l !! 0) l 

main = do 
    let matrix =  [[1, 2, 3], [2, 1, 6], [3, 6, 1]]
    putStrLn ( show (remove matrix))
    print (scacchiera 10)
    print (takeTwo [1,2,3,4,5,7,8] [5,6,8,10,11,12,13])
    let mt = [[1,2,3,4,5,6],
              [2,3,4,5,6,7],
              [3,4,5,6,7,8],
              [4,5,6,7,8,9]]
    print (matTakeTwo mt)
    print (diagonal matrix)