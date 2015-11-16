;; 4clojure 75
(defn totient [n]
  (letfn
    [(gcd
      [n m]
      (cond (= n m) n
            (> n m) (gcd (- n m) m)
            :else (gcd n (- m n))))
     (coprimes-up-to
      [n]
      (filter #(= (gcd n %) 1) (range 1 n)))]
    (if (= n 1)
      1
      (count (coprimes-up-to n)))))

(totient 10)

(totient 1)

(totient 99)