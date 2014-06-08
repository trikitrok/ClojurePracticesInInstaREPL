(range 1 16)

(defn is-multiple-of? [num n]
  (= 0 (rem n num)))

(def is-multiple-of-3?
  (partial is-multiple-of? 3))

(is-multiple-of-3? 3)
(is-multiple-of-3? 5)

(def is-multiple-of-5?
  (partial is-multiple-of? 5))

(is-multiple-of-5?  3)
(is-multiple-of-5?  5)

(defn fizz [n s]
  (if (is-multiple-of-3? n)
    (str s "Fizz")
    s))

(fizz 3 "")

(fizz 4 "")

(defn buzz [n s]
  (if (is-multiple-of-5? n)
    (str s "Buzz")
    s))

(buzz 3 "")

(buzz 5 "")

(map fizz (range 1 16) (repeat 16 ""))

(map buzz (range 1 16) (repeat 16 ""))

(map buzz (range 1 16) (map fizz (range 1 16) (repeat 16 "")))

(map (fn [n s] (if (= s "") (str n) s))
     (range 1 16)
     (map buzz (range 1 16) (map fizz (range 1 16) (repeat 16 ""))))


(defn empty-str-to-number [n s]
  (if (= s "") (str n) s))

(map empty-str-to-number ; <- recursive application of map
     (range 1 16)
     (map buzz
          (range 1 16)
          (map fizz
               (range 1 16)
               (repeat 16 ""))))

(def transformations (list fizz buzz empty-str-to-number))


(defn transform [functions numbers strings]
  (if (empty? functions)
    strings
    (recur (rest functions) numbers (map (first functions) numbers strings))))

(transform transformations (range 1 16) (repeat 16 ""))


(def a-vec [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15])

(transform transformations a-vec (repeat (count a-vec) ""))


(defn fiz-buzz-numbers [coll transformations]
  (let [transform (fn [functions numbers result]
                    (if (empty? functions)
                      result
                      (recur (rest functions)
                             numbers
                             (map (first functions) numbers result))))]
    (transform transformations coll (repeat (count coll) ""))))

(fiz-buzz-numbers a-vec transformations)


(defn fizz1 [n s]
  [n (if (is-multiple-of-3? n)
       (str s "Fizz")
       s)])

(fizz1 3 "")

(defn buzz1 [[n s]]
  [n (if (is-multiple-of-5? n)
       (str s "Buzz")
       s)])

(buzz1 [5 ""])

(defn empty-str-to-number1 [[n s]]
  (if (= s "") (str n) s))


(def t (reduce comp (reverse [fizz1 buzz1 empty-str-to-number1])))

(t 1 "")
(t 3 "")
(t 5 "")
(t 15 "")

(map t a-vec (repeat (count a-vec) ""))

(defn fizz2 [[n s]]
  [n (if (is-multiple-of-3? n)
       (str s "Fizz")
       s)])

(fizz2 [3 ""])

(def t2 (reduce comp (reverse [fizz2 buzz1 empty-str-to-number1])))

(for [n a-vec s [""]] [n s])

(map t2 (for [n a-vec s [""]] [n s]))


