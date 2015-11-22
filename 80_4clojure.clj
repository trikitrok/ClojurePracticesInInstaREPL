;; 4clojure 80
(defn divisors [n]
  (filter #(and (not= % n) (zero? (mod n %)))
          (range 1 n)))

(divisors 2)
(divisors 3)
(divisors 5)
(divisors 4)
(divisors 6)
(divisors 8)
(divisors 10)
(divisors 496)
(divisors 500)

(defn perfect-number? [n]
  (->> (divisors n)
       identity
       (reduce +)
       (= n)))

(= (perfect-number? 6) true)
(= (perfect-number? 7) false)
(= (perfect-number? 496) true)
(= (perfect-number? 500) false)
(= (perfect-number? 8128) true)

(defn perfect-number? [n]
  (let [divisors
        (fn [n]
          (filter #(and (not= % n)
                        (zero? (mod n %)))
                  (range 1 n)))]
    (->> (divisors n)
         (reduce +)
         (= n))))

(= (perfect-number? 6) true)
(= (perfect-number? 7) false)
(= (perfect-number? 496) true)
(= (perfect-number? 500) false)
(= (perfect-number? 8128) true)