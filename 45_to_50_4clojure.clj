(def flip #(comp (partial apply %) reverse list))

((flip nth) 2 [1 2 3 4 5])
((flip >) 7 8)
((flip quot) 2 8)
((flip take) [1 2 3 4 5] 3)


(defn my-split-at [n ls]
  [(take n ls) (drop n ls)])

(my-split-at 3 [1 2 3 4 5 6])
(my-split-at 1 [:a :b :c :d])


(def my-split-at
  (juxt take drop))

(my-split-at 3 [1 2 3 4 5 6])
(my-split-at 1 [:a :b :c :d])

(partition-all 3 [1 2 3 4 5 6])

(partition-all 1 [:a :b :c :d])

(defn my-split-at [n ls]
  (list (first (partition-all n ls))
        (mapcat identity (rest(partition-all n ls)))))

(my-split-at 1 [:a :b :c :d])
(my-split-at 3 [1 2 3 4 5 6])


(type [1 2])
(type :a)
(type 5)
(type "koko")

(defn split-by-type [ls]
  (vals (reduce (fn [acc elem]
                  (if-let [type-seq (acc (type elem))]
                    (assoc acc (type elem) (conj type-seq elem))
                    (assoc acc (type elem) [elem])))
                {} ls)))

(split-by-type ["hola" [1 2] :a [3 4] 5 "moko" 6 :b])

(group-by type ["hola" [1 2] :a [3 4] 5 "moko" 6 :b])

(defn split-by-type [ls]
  (vals (group-by type ls)))

(split-by-type ["hola" [1 2] :a [3 4] 5 "moko" 6 :b])
