-- 2 Per ogni categoria
-- + Numeri
-- + Liste
-- + Alberi BST
-- + Matrici
-- + Alberi generici
-- + Quad trees
-- + Matrici mediante quad trees

-- TODO: Aggiungere i foldl o foldr
-- Numeri
-- 1.
fattoriale :: Integer -> Integer
--fattoriale 0 = 1
--fattoriale x = x * fattoriale(x-1)
fattoriale x = foldr (*) 1 [1..x]
-- 2.
combinazioni :: Integer -> Integer -> Integer
combinazioni n k = (fattoriale n) `div` ((fattoriale k) * (fattoriale (n - k)))
-- 3.
comb :: [a] -> [[a]]
comb [] = [[]]
comb (x:xs) = map (x:) (comb xs) 
              ++ (comb xs)

-- Liste
-- 1.
rimuoviPosPari :: Show a => [a] -> [a]
rimuoviPosPari [x] = [x]
rimuoviPosPari (x:y:xys) = [x] ++ rimuoviPosPari xys
-- 2.
sumPosDispari :: (Show a, Num a) => [a] -> a
sumPosDispari l = sum (rimuoviPosPari l)
-- 3.
quicksort :: (Show a, Num a, Ord a) => [a] -> [a]
quicksort [] = []
quicksort (x:xs) = quicksort [ y | y <- xs, y < x]
                   ++ [x] ++ 
                   quicksort [ y | y <- xs, y >= x]
-- 4.
min2Dispari :: (Show a, Num a, Ord a, Integral a) => [a] -> (a, a)
min2Dispari l = (ordList !! 1, ordList !! 0)
                where ordList = quicksort [y | y <- l , rem y 2 /= 0]
-- 5.
coppieConseguenti :: (Show a, Num a, Ord a) => [a] -> [(a, a)]
coppieConseguenti [] = []
coppieConseguenti (x:xs) = (x, sum xs) : (coppieConseguenti xs) 
-- 6.
coppieAntecedenti :: (Show a, Num a, Ord a) => [a] -> a -> [(a, a)]
coppieAntecedenti [] _ = []
coppieAntecedenti (x:xs) sm = (x, sm) : (coppieAntecedenti xs (sm + x)) 
-- 7.
shiftToZero :: (Show a, Num a, Ord a) => [a] -> [a]
shiftToZero l = map (\x -> (x - minimum l)) l


-- Matrici
-- 9.
transpose l | (length (head l) == 0) = [] 
            | otherwise = [ head row | row <- l]:(transpose [tail row | row <- l])
-- 10.
isSymmetric l = and (map (\(x, y) -> x == y) (zip (transpose l) l))
-- 11.
dot a b = sum (map (\(x, y) -> x*y) (zip a b)) 
multiply a b | (length a == 0) = []
             | otherwise = [ dot (head a) (rw) | rw <- (transpose b) ]:(multiply (tail a) (b))

-- Alberi
data BSTree a = Null | Node {
    val :: a,
    left :: BSTree a,
    right :: BSTree a
} deriving (Eq, Ord, Read, Show)
-- 1.
sumTree :: (Num a) => BSTree a -> a
sumTree Null = 0
sumTree (Node v left right) = v + (sumTree left) + (sumTree right)
-- 2.
sumOdd :: (Integral a) => BSTree a -> a
sumOdd Null = 0
sumOdd (Node v left right) | odd v = v + (sumTree left) + (sumTree right)
                           | otherwise = 0 + (sumTree left) + (sumTree right)
-- 3.
samesums [] = False
samesums [x] = True
samesums (x:y:xys) | (sumTree x == sumTree y) = samesums(y:xys)
                   | otherwise = False
-- 4.
bstElem :: (Num a, Eq a) => a -> BSTree a -> Bool
bstElem val Null = False
bstElem val (Node a tl tr) | val == a = True
                           | otherwise = (bstElem val tl) || (bstElem val tr)
-- 6.
bst2List :: BSTree a -> [a]
bst2List Null = []
bst2List (Node v left right) = 
    bst2List left 
    ++ [v] ++
    bst2List right 

-- 10.
height :: (Num a, Ord a) => BSTree a -> a
height Null = 0
height (Node _ left right) = 1 + (max (height left)  (height right))
almostBalanced :: (Num a, Ord a) => BSTree a -> Bool
almostBalanced Null = True
almostBalanced (Node _ left right) = 
    (abs ((height left) - (height right))) <= 1
    && (almostBalanced left) 
    && (almostBalanced right)

-- 13. 
enqueue :: BSTree a -> [BSTree a]
enqueue Null = [] 
enqueue (Node _ Null Null) = []
enqueue (Node _ left Null) = [left]
enqueue (Node _ Null right) = [right]
enqueue (Node _ left right) = [left,right]
levelsList :: [BSTree a] -> [a]
levelsList [] = []
levelsList l = (map (\(Node v left right) -> v) l) ++ (levelsList (concat (map enqueue l))) 
levels (Node v left right) = levelsList [(Node v left right)] 



-- Alberi generici
data Tree a = Void | Tree a [Tree a] 
    deriving (Eq, Show)

-- 15.
preorder :: Tree a -> [a]
preorder Void = []
preorder (Tree a []) = [a]
preorder (Tree a children) = [a] ++ (concat (map (\node -> preorder node) children))

-- 16.
frontier :: Tree a -> [a]
frontier Void = []
frontier (Tree a []) = [a]
frontier (Tree a children) = concat (map (\node -> frontier node) children)

-- Quadtrees
data QTree a = Col a | QTree (QTree a) (QTree a) (QTree a) (QTree a)
    deriving (Eq, Show)

pickElementQT :: QTree a -> a
pickElementQT (Col a) = a
pickElementQT (QTree a _ _ _) = pickElementQT a

zipWithQT :: (Eq a) => (a -> a -> a) -> QTree a -> QTree a -> QTree a
zipWithQT op (Col a) (Col b) = Col (op a b)
zipWithQT op (Col a) (QTree a2 b2 c2 d2) 
    | isClusterSame = Col (op a (pickElementQT a2)) -- Prendo un elemento tanto sono tutti uguali
    | otherwise = (QTree (zipWithQT (op) (Col a) a2) 
                         (zipWithQT (op) (Col a) b2)
                         (zipWithQT (op) (Col a) c2) 
                         (zipWithQT (op) (Col a) d2))
    where 
        isClusterSame = (zipWithQT (op) (Col a) a2) == (zipWithQT (op) (Col a) b2) &&
                        (zipWithQT (op) (Col a) b2) == (zipWithQT (op) (Col a) c2) &&
                        (zipWithQT (op) (Col a) c2) == (zipWithQT (op) (Col a) d2)
zipWithQT op (QTree a1 b1 c1 d1) (Col b) 
    | isClusterSame = Col (op (pickElementQT a1) b)
    | otherwise = (QTree (zipWithQT (op) a1 (Col b)) 
                         (zipWithQT (op) b1 (Col b))
                         (zipWithQT (op) c1 (Col b)) 
                         (zipWithQT (op) d1 (Col b)))
    where 
        isClusterSame = (zipWithQT (op) a1 (Col b)) == (zipWithQT (op) b1 (Col b)) &&
                        (zipWithQT (op) b1 (Col b)) == (zipWithQT (op) c1 (Col b)) &&
                        (zipWithQT (op) c1 (Col b)) == (zipWithQT (op) d1 (Col b))
zipWithQT op (QTree a1 b1 c1 d1) (QTree a2 b2 c2 d2) = 
    (QTree (zipWithQT (op) a1 a2) 
           (zipWithQT (op) b1 b2)
           (zipWithQT (op) c1 c2) 
           (zipWithQT (op) d1 d2))

framed :: (a -> Bool) -> QTree a -> Bool
framed p node = top (p) node && 
                           right (p) node && 
                           bottom (p) node && 
                           left (p) node
    where 
        top p (Col a) = p a 
        top p (QTree a b c d) = (top (p) a) && (top (p) b) 
        right p (Col a) = p a
        right p (QTree a b c d) = (right (p) b) && (right (p) d)
        bottom p (Col a) = p a
        bottom p (QTree a b c d) = (bottom (p) c) && (bottom (p) d)
        left p (Col a) = p a          
        left p (QTree a b c d) = (left (p) a) && (left (p) c)

frame :: (Eq a) => a -> QTree a -> Maybe a
frame c tree | framed (\x -> x == c) tree = Just c
             | otherwise = Nothing

-- Matrici tramite quadtrees
data Mat a = Mat {
    nexp :: Int, -- 2^nexp lunghezza del lato della matrice
    mat :: QTree a
} deriving ( Eq , Show )


-- 13.
transposeNode :: QTree a -> QTree a
transposeNode (Col i) = (Col i)
transposeNode (QTree a b c d) = (QTree (transposeNode a) (transposeNode c)
                                       (transposeNode b) (transposeNode d))
transposeQT :: Mat a -> Mat a
transposeQT (Mat length mat) = (Mat length (transposeNode mat))

-- 14.
isSymmetricQT :: (Eq a) => Mat a -> Bool
isSymmetricQT (Mat length mat) = (transposeNode mat) == mat

main = do 
    putStrLn ( show (fattoriale 10));
    putStrLn ( show (combinazioni 5 2));
    putStrLn ( show (comb [1, 2, 3]));
    putStrLn ( show (rimuoviPosPari [1, 2, 3, 4, 5, 6, 7]));
    putStrLn ( show (sumPosDispari [1, 2, 3, 4, 5, 6, 7]));
    putStrLn ( show (quicksort [7, 6, 5, 4, 3, 2, 1]));
    putStrLn ( show (min2Dispari [7, 6, 5, 4, 3, 2, 1]));
    putStrLn ( show (coppieConseguenti [7, 6, 5, 4, 3, 2, 1]));
    putStrLn ( show (coppieAntecedenti [7, 6, 5, 4, 3, 2, 1] 0));
    putStrLn ( show (shiftToZero [7, 6, 5, 4, 3, 2, 1]));
    let tree = (Node 4 (Node 2 (Node 1 Null Null) (Node 3 Null Null)) (Node 6 (Node 5 Null Null) (Node 7 Null Null))) 
    let tree2 = (Node 2 (Node 1 Null Null) (Node 3 Null Null))
    putStrLn ( show (sumTree tree));
    putStrLn ( show (sumTree tree2));
    putStrLn ( show (sumOdd tree));
    putStrLn ( show (sumOdd tree2));
    putStrLn ( show (samesums [tree, tree, tree]));
    putStrLn ( show (samesums [tree, tree2, tree2, tree, tree]));
    putStrLn ( show (bstElem 6 tree));
    putStrLn ( show (bstElem 6 tree2));
    putStrLn ( show (bst2List tree));
    putStrLn ( show (bst2List tree2));
    putStrLn ( show (almostBalanced (Node 4 Null (Node 7 (Node 5 Null Null) Null))))
    putStrLn ( show (almostBalanced tree2))
    putStrLn ( show (levels (Node 4 Null (Node 7 (Node 5 Null Null) Null))))
    putStrLn ( show (levels tree))
    putStrLn ( show (transpose [[1, 2], [4, 5], [7, 8]]));
    putStrLn ( show (isSymmetric [[1, 2, 3], [2, 1, 6], [3, 6, 1]]));
    putStrLn ( show (isSymmetric [[1, 2, 3], [4, 5, 6], [7, 8, 9]]));
    putStrLn ( show (multiply [[1, 2, 3], [4, 5, 6]] [[1, 2], [3, 4], [5, 6]]));
    let genericTree = (Tree 1 [
            (Tree 1 [
                (Tree 11 []),
                (Tree 12 [
                    (Tree 121 [])
                ]),
                (Tree 13 [])
            ]), 
            (Tree 2 [
                (Tree 21 [])
            ]), 
            (Tree 3 [
                (Tree 31 [
                    (Tree 311 []),
                    (Tree 312 []),
                    (Tree 313 [])
                ]),
                (Tree 32 []),
                (Tree 33 []),
                (Tree 34 [])
            ])])
    putStrLn ( show (preorder genericTree))
    putStrLn ( show (frontier genericTree))
    let qtree1 = (QTree 
                    (Col 1) 
                    (QTree 
                        (Col 1) 
                        (Col 2) 
                        (Col 3) 
                        (Col 4)) 
                    (QTree 
                        (Col 3)
                        (Col 2)
                        (Col 7)
                        (Col 2))
                    (QTree
                        (Col 22)
                        (Col 8)
                        (Col 0)
                        (Col 12)))
    let qtree2 = (QTree 
                    (QTree 
                        (Col 3)
                        (Col 2)
                        (Col 7)
                        (Col 2))
                    (Col 1) 
                    (QTree 
                        (Col 1) 
                        (Col 2) 
                        (Col 3) 
                        (Col 4)) 
                    (Col 0))   
    let cqtree = (Col 2)
    let framedTree = (QTree 
                    (Col 2) 
                    (QTree 
                        (Col 2) 
                        (Col 2) 
                        (Col 20) 
                        (Col 2)) 
                    (QTree 
                        (Col 2)
                        (Col 24)
                        (Col 2)
                        (Col 2))
                    (QTree
                        (Col 22)
                        (Col 2)
                        (Col 2)
                        (Col 2)))
    putStrLn ( show (zipWithQT (*) qtree1 qtree2))
    putStrLn ( show (framed (\x -> x < 10) framedTree))
    putStrLn ( show (framed (\x -> x > 10) framedTree))
    putStrLn ( show (framed (\x -> x > 10) cqtree))
    putStrLn ( show (frame 2 framedTree))
    putStrLn ( show (frame 10 framedTree))
    putStrLn ( show (frame 2 cqtree))
    putStrLn ( show (frame 5 cqtree))
    let treeMatrix = (Mat 4 (QTree 
                    (Col 4) 
                    (QTree 
                        (Col 1) 
                        (Col 2) 
                        (Col 3) 
                        (Col 4)) 
                    (QTree
                        (Col 10)
                        (Col 11)
                        (Col 12)
                        (Col 13))
                    (Col 3)))
    let symmetricTreeMatrix = (Mat 4 (QTree 
                    (Col 4) 
                    (QTree 
                        (Col 1) 
                        (Col 2) 
                        (Col 3) 
                        (Col 4)) 
                    (QTree
                        (Col 1)
                        (Col 3)
                        (Col 2)
                        (Col 4))
                    (Col 3)))
    putStrLn ( show (transposeQT treeMatrix))
    putStrLn ( show (isSymmetricQT treeMatrix))
    putStrLn ( show (isSymmetricQT symmetricTreeMatrix))