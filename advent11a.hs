import System.IO

solution :: [Int] -> Int -> Int
solution list 0 = length list
solution list n = solution (concatMap go list) (n-1)
    where go x
            | x == 0 = [1]
            | even (length (show x)) = [toInt $ take (div (length $ show x) 2) (show x),
                                        toInt $ drop (div (length $ show x) 2) (show x)]
            | otherwise = [x * 2024]

main = do
        handle <- openFile "input.txt" ReadMode
        contents <- hGetContents handle
        let list = map toInt (words contents)
        print (solution list 25)
        hClose handle

toInt :: String -> Int
toInt = read
