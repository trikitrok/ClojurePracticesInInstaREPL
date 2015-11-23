;; 4Clojure 88

(reduce conj #{1 2 3 4 5 6} #{1 3 5 7})
(filter #{1 2 3 4 5 6} #{1 3 5 7})
(filter #{1 3 5 7} #{1 2 3 4 5 6})
(filter #{} #{1 2 3 4 5 6})
(filter #{3} #{1 2})
(filter #{1 2} #{3})

(defn symetric-difference [set1 set2]
  (let [union (reduce conj set1 set2)
        intersection (filter set1 set2)]
    (set (filter #(not (some #{%} intersection)) union))))

(= (symetric-difference #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7})
(= (symetric-difference #{:a :b :c} #{}) #{:a :b :c})
(= (symetric-difference #{} #{4 5 6}) #{4 5 6})
(= (symetric-difference #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]})

(defn symetric-difference [set1 set2]
  (let [union (set (reduce conj set1 set2))
        intersection (set (filter set1 set2))]
    (set (filter #(not (contains? intersection %)) union))))

(= (symetric-difference #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7})
(= (symetric-difference #{:a :b :c} #{}) #{:a :b :c})
(= (symetric-difference #{} #{4 5 6}) #{4 5 6})
(= (symetric-difference #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]})


;; 4Clojure 90
(defn cartesian-product [set1 set2]
  (into
   #{}
   (for [x set1 y set2]
     [x y])))

(cartesian-product #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})


(= (cartesian-product
    #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
    #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
      ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
      ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})

(= (cartesian-product
    #{1 2 3} #{4 5})
   #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})

(= 300 (count (cartesian-product
               (into #{} (range 10))
               (into #{} (range 30)))))
