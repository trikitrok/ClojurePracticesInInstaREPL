;; 4clojure 69
(merge-with * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})

(defn my-merge-with [f & maps]
  (reduce
   (fn [acc a-map]
     (reduce
      (fn [acc [k v]]
        (if-let [acc-val (get acc k)]
          (assoc acc k (f acc-val v))
          (assoc acc k v)))
      acc
      a-map))
   {}
   maps))

(my-merge-with * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
(my-merge-with - {1 10, 2 20} {1 3, 2 10, 3 15})
(my-merge-with concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
;;


;; 4clojure 70
(clojure.string/split
 (->>
  "Have a nice day."
  (filter #(or (Character/isLetter %)
               (Character/isSpace %)))
  (apply str)) #" ")

(defn my-sort [s]
  (sort
   (fn [a b]
     (compare (clojure.string/lower-case a) (clojure.string/lower-case b)))
   (clojure.string/split
    (->>
     s
     (filter #(or (Character/isLetter %)
                  (Character/isSpace %)))
     (apply str))
    #" ")))

(my-sort "Have a nice day.")
(my-sort "Clojure is a fun language!")
(my-sort "Fools fall for foolish follies.")

(defn my-sort [s]
  (sort-by
   clojure.string/lower-case
   (clojure.string/split
    (->>
     s
     (filter #(or (Character/isLetter %)
                  (Character/isSpace %)))
     (apply str))
    #" ")))

(my-sort "Have a nice day.")
(my-sort "Clojure is a fun language!")
(my-sort "Fools fall for foolish follies.")

(clojure.string/split "Have a nice day." #"[^A-Za-z]")

(defn my-sort [s]
  (sort-by
   clojure.string/lower-case
   (clojure.string/split s #"[^A-Za-z]")))

(my-sort "Have a nice day.")
(my-sort "Clojure is a fun language!")
(my-sort "Fools fall for foolish follies.")
