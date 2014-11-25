(ns valid-credid-card)

(str 1)
(str \1)

(defn- parse-int [s]
  (Integer. (re-find  #"\d+" (str s))))

(parse-int "3")
(parse-int \3)

(defn- to-digits [n]
  (map parse-int (str n)))

(to-digits 12)

(def ^:private to-digits-rev
  (comp reverse
        (partial to-digits)))

(to-digits-rev 12)

(map-indexed
 (fn [idx itm]
   (if (zero? (mod (+ idx 1) 2))
     (* 2 itm)
     itm))
 (to-digits-rev 123456))

(defn- double-second [coll]
  (map-indexed
   #(if (zero? (mod (+ %1 1) 2))
      (* 2 %2)
      %2)
   coll))

(double-second (to-digits-rev 123456))

(def ^:private sum-digits
  (partial reduce #(+ %1 (reduce + (to-digits %2)))))

(sum-digits [6 66 666])

(defn is-valid? [num-credit-card]
  (zero?
   (mod
    (->>
     num-credit-card
     to-digits-rev
     double-second
     sum-digits)
    10)))
