import Data.Map qualified as Map
import System.IO

f :: Map.Map Int Int -> Int -> Int
f m 0 = Map.foldl (+) 0 m
f m n = f (process m) (n - 1)

process :: Map.Map Int Int -> Map.Map Int Int
process = Map.foldrWithKey go Map.empty
  where
    go val cnt accMap =
      let newStones = transform val cnt
       in Prelude.foldr go' accMap newStones
    go' (newVal, newCnt) = Map.insertWith (+) newVal newCnt

transform :: Int -> Int -> [(Int, Int)]
transform val cnt
  | val == 0 = [(1, cnt)]
  | even len =
      [ (toInt $ take (div len 2) sv, cnt),
        (toInt $ drop (div len 2) sv, cnt)
      ]
  | otherwise = [(val * 2024, cnt)]
  where
    sv = show val
    len = length sv

solution :: [Int] -> Int -> Int
solution list = f (Prelude.foldr go Map.empty list)
  where
    go v = Map.insertWith (+) v 1

main = do
  handle <- openFile "input.txt" ReadMode
  contents <- hGetContents handle
  let list = map toInt (words contents)
  print (solution list 75)
  hClose handle

toInt :: String -> Int
toInt = read
