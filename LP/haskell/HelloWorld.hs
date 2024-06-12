add :: Integer -> Integer -> Integer
add x y = x + y

pog :: IO ()
pog = putStrLn "Teste"

main :: IO ()
main = do
  putStrLn "Hello, World!"
  putStrLn ("The sum of 2 and 3 is " ++ show (add 2 3))
  pog

  putStrLn ("Please look at my favorite numbers: " ++ show [10 .. 20])