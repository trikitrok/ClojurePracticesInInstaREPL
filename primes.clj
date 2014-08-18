(def n 600851475143)

(defn primes-below
  ([n] (primes-below [] (range 2 (+ 1 n))))
  ([acc ls]
   (if (empty? ls)
     acc
       (let
       [next-prime (first ls)]
       (recur (conj acc next-prime)
              (remove #(zero? (mod % next-prime))
                      ls))))))

(primes-below 30)
(primes-below 10000)

(defn largest-prime-below [n]
  (apply
   max
   (primes-below n)))

(largest-prime-below 100)
(largest-prime-below 1000)
(largest-prime-below 10000)
;(largest-prime-below 100000) -> java.lang.StackOverflowError

(defn sieve [[xs ps]]
  (let [[p & more] xs]
    [(remove #(zero? (rem % p)) xs) (cons p ps)]))

(iterate sieve [(range 2 (inc 30)) '()])

(defn primes-below [n]
  (apply
    concat
    (first
     (drop-while
      #(< (ffirst %) (Math/sqrt n))
      (iterate sieve [(range 2 (inc n)) nil])))))

(defn largest-prime-below [n]
  (apply
   max
   (primes-below n)))

(largest-prime-below 100)
(largest-prime-below 1000)
(largest-prime-below 10000)
(largest-prime-below 100000)
(largest-prime-below 1000000)
;(largest-prime-below 10000000);too slow
;(largest-prime-below n); -> java.lang.StackOverflowError

(defn sieve [primes n]
  (remove #(zero? (rem % n)) primes))

(defn largest-prime-below [n]
  (apply
   max
   (reduce
    sieve
    (range 2 (inc n))
    (range 2 (Math/sqrt 30)))))

(largest-prime-below 100)
(largest-prime-below 1000)
(largest-prime-below 10000)
;(largest-prime-below 10000000);too slow
(largest-prime-below n) ; java.lang.StackOverflowError

(defn lazy-primes3 []
  (letfn [(enqueue [sieve n step]
            (let [m (+ n step)]
              (if (sieve m)
                (recur sieve m step)
                (assoc sieve m step))))
          (next-sieve [sieve candidate]
            (if-let [step (sieve candidate)]
              (-> sieve
                (dissoc candidate)
                (enqueue candidate step))
              (enqueue sieve candidate (+ candidate candidate))))
          (next-primes [sieve candidate]
            (if (sieve candidate)
              (recur (next-sieve sieve candidate) (+ candidate 2))
              (cons candidate
                (lazy-seq (next-primes (next-sieve sieve candidate)
                            (+ candidate 2))))))]
    (cons 2 (lazy-seq (next-primes {} 3)))))

(defn largest-prime-below [n]
  (apply max (take-while (partial > n) (lazy-primes3))))

(largest-prime-below 100000)
(largest-prime-below 1000000)
;(largest-prime-below 10000000);too slow
;(apply max (take-while (partial > n) (lazy-primes3))) ; too slow!!!