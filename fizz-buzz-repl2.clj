(defn is-multiple-of? [num n]
  (= 0 (rem n num)))

(def is-multiple-of-3?
  (partial is-multiple-of? 3))

(def is-multiple-of-5?
  (partial is-multiple-of? 5))


(def a-vec [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15])


(defn fizz [n s]
  [n (if (is-multiple-of-3? n)
       (str s "Fizz")
       s)])

(fizz 3 "")

(defn buzz [[n s]]
  [n (if (is-multiple-of-5? n)
       (str s "Buzz")
       s)])

(buzz [5 ""])

(defn empty-str-to-number [[n s]]
  (if (= s "") (str n) s))


(def t (reduce comp (reverse [fizz buzz empty-str-to-number])))

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

(def t2 (reduce comp (reverse [fizz2 buzz empty-str-to-number])))

(for [n a-vec s [""]] [n s])

(map t2 (for [n a-vec s [""]] [n s]))

; Another alternative to fizz-buzz
(defn fizz-buzz [coll]
  (let [is-multiple-of? (fn [num n]
                          (= 0 (rem n num)))

        is-multiple-of-3? (partial is-multiple-of? 3)

        is-multiple-of-5? (partial is-multiple-of? 5)

        fizz (fn [[n s]]
               [n (if (is-multiple-of-3? n)
                    (str s "Fizz")
                    s)])

        buzz (fn [[n s]]
               [n (if (is-multiple-of-5? n)
                    (str s "Buzz")
                    s)])

        empty-str-to-number (fn [[n s]]
                              (if (= s "") (str n) s))

        transformations (list fizz buzz empty-str-to-number)]
    (clojure.string/join
     \space
     (map
      (reduce comp (reverse transformations))
      (for [n coll s [""]] [n s])))))

(fizz-buzz a-vec)
