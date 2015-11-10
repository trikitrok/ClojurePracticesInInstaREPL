;; 4clojure 65
(defn my-seq-type [coll]
  (cond

   (= 2 (:c (conj coll [:c 1] [:c 2])))
   :map

   (= (inc (count coll)) (count (conj coll [:c 1] [:c 1])))
   :set

   (= [:c 2] (first (conj coll [:c 1] [:c 2])))
   :list

   (= [:c 2] (last (conj coll [:c 1] [:c 2])))
   :vector))

(my-seq-type {})

(my-seq-type '())

(my-seq-type [])

(my-seq-type #{})

(count (conj #{} [:c 1] [:c 1]))

(conj {} [:c 1] [:c 2])

(conj '() [:c 1] [:c 2])

(conj [] [:c 1] [:d 1])

(conj #{} [:c 1] [:c 1])
;;

(defn my-seq-type [coll]
  (condp = (nth (str coll) 0)
    \{ :map
    \c :list
    \[ :vector
    \# :set))

(my-seq-type {})

(my-seq-type '())

(my-seq-type [])

(my-seq-type #{})

(str [])

(str (range 10))

(str #{})

(str {})


;; 4clojure 66
(defn gcd [a b]
  (cond (zero? b)
        a

        (= a b)
        a

        (> a b)
        (recur (- a b) b)

        :else
        (recur a (- b a))))

(gcd 2 4)

(gcd 10 5)

(gcd 5 7)

(gcd 1023 858)


;; 4clojure 67
(defn first-primes [n]
  (map
   first
   (take
    n
    (iterate
     (fn [[p coll]]
       (let [sieved-coll (remove #(zero? (mod % p)) coll)]
         [(first sieved-coll)
          (drop 1 sieved-coll)]))
     [2 (drop 2 (range))]))))

(first-primes 2)
(first-primes 5)
(last (first-primes 100))


(defn first-primes
  ([n] (take n (first-primes 2 (drop 2 (range)))))
  ([p coll]
   (cons
    p
    (lazy-seq
     (let [sieved-coll (remove #(zero? (mod % p)) coll)]
       (first-primes (first sieved-coll)
                     (drop 1 sieved-coll)))))))

(first-primes 2)
(first-primes 5)
(last (first-primes 100))