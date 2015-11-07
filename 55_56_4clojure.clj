;; 4clojure 55
(reduce
 (fn [acc elem]
   (if-let [num (get acc elem)]
     (assoc acc elem (inc num))
     (assoc acc elem 1)))
 {}
 [1 2 1 3 3 1 4])

(reduce
 (fn [acc elem]
   (assoc acc elem
     (if-let [num (get acc elem)]
       (inc num)
       1)))
 {}
 [1 2 1 3 3 1 4])

(partition-by identity [1 2 1 3 3 1 4])

(map first (partition-by identity [1 2 1 3 3 1 4]))

(map count (partition-by identity [1 2 1 3 3 1 4]))

(zipmap
 (map first (partition-by identity [1 2 1 3 3 1 4]))
 (map count (partition-by identity [1 2 1 3 3 1 4])))

(group-by identity [1 2 1 3 3 1 4])

(into {} (map (fn [pair] ((juxt first (comp count second)) pair))
              (group-by identity [1 1 2 3 2 1 1])))

(into {} (map (fn [[key values]] (vector key (count values)))
              (group-by identity [1 1 2 3 2 1 1])))

(into {}
      (for [[k vs] (group-by identity [1 1 2 3 2 1 1])]
        [k (count vs)]))


; 4clojure 56
(defn my-distinct [ls]
  (loop [acc [] ls ls]
    (if-let [elem (first ls)]
      (recur (conj acc elem) (filter #(not= elem %) ls))
      acc)))

(my-distinct [1 2 1 3 1 2 4])

(my-distinct '([2 4] [1 2] [1 3] [1 3]))

(defn my-distinct2 [ls]
  (reduce
   #(if (some #{%2} %1)
      %1
      (conj %1 %2))
   []
   ls))

(my-distinct2 [1 2 1 3 1 2 4])

(my-distinct2 '([2 4] [1 2] [1 3] [1 3]))
