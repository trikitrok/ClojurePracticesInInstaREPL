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

;; Resulting version of fizz-buzz
(defn fizz-buzz [coll]
  (let [is-multiple-of? (fn [num n]
                          (= 0 (rem n num)))

        is-multiple-of-3? (partial is-multiple-of? 3)

        is-multiple-of-5? (partial is-multiple-of? 5)

        fizz (fn [n s]
               (if (is-multiple-of-3? n)
                 (str s "Fizz")
                 s))

        buzz (fn [n s]
               (if (is-multiple-of-5? n)
                 (str s "Buzz")
                 s))

        empty-str-to-number (fn [n s]
                              (if (= s "")
                                (str n)
                                s))

        transformations (list fizz buzz empty-str-to-number)

        transform (fn [functions numbers result]
                    (if (empty? functions)
                      result
                      (recur (rest functions)
                             numbers
                             (map (first functions) numbers result))))]
    (clojure.string/join
     \space
     (transform transformations coll (repeat (count coll) "")))))
