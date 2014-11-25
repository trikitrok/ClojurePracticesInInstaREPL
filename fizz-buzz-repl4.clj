(defn fizz-buzz-map [num]
  {0 {0 "FizzBuzz"
      1 "Fizz"
      2 "Fizz"
      3 "Fizz"
      4 "Fizz"}
   1 {0 "Buzz"
      1 (str num)
      2 (str num)
      3 (str num)
      4 (str num)}
   2 {0 "Buzz"
      1 (str num)
      2 (str num)
      3 (str num)
      4 (str num)}})

(defn fizz-buzz-num [num]
  (get (get (fizz-buzz-map num) (mod num 3)) (mod num 5)))

(fizz-buzz-num 1)
(fizz-buzz-num 2)
(fizz-buzz-num 3)
(fizz-buzz-num 4)
(fizz-buzz-num 5)
(fizz-buzz-num 9)
(fizz-buzz-num 15)


