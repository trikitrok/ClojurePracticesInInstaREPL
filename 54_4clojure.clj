; Implementation using tail recursion consuming elements one by one
(defn my-partition [partition-size ls]
  (loop [result [] current-partition [] ls ls]
    (if (seq ls)
      (let [elem (first ls)
            new-partition (conj current-partition elem)]
        (if (= (count new-partition) partition-size)
          (recur (conj result new-partition) [] (rest ls))
          (recur result new-partition (rest ls))))
      result)))

(my-partition  3 [])
(my-partition  3 [1])
(my-partition  3 [1 2])
(my-partition  3 [1 2 3])
(my-partition  3 [1 2 3 4])
(my-partition  3 [1 2 3 4 5 6 7])
(my-partition  3 (range 8))

; Equivalent but simpler implementation using reduce
(defn my-partition [partition-size ls]
  (:result
   (reduce
    (fn [{:keys [result current-partition] :as acc} elem]
      (let [new-partition (conj current-partition elem)]
        (if (= (count new-partition) partition-size)
          {:result (conj result new-partition) :current-partition []}
          (assoc acc :current-partition new-partition))))
    {:result [] :current-partition []} ls)))

(my-partition  3 [])
(my-partition  3 [1])
(my-partition  3 [1 2])
(my-partition  3 [1 2 3])
(my-partition  3 [1 2 3 4])
(my-partition  3 [1 2 3 4 5 6 7])
(my-partition  3 (range 8))

(take 4 (range 0 3))
(drop 4 (range 0 3))

(conj [1 2 3] nil)
(cons nil [1 2 3])
(cons '() [1 2 3])


; Much simpler recursive implementation consuming partition-size elements each step
(defn my-partition [partition-size ls]
  (if (>= (count ls) partition-size)
    (concat [(take partition-size ls)]
            (my-partition partition-size (drop partition-size ls)))
    []))

(my-partition 3 [])
(my-partition 3 [1])
(my-partition 3 [1 2])
(my-partition 3 [1 2 3])
(my-partition 3 [1 2 3 4])
(my-partition 3 [1 2 3 4 5 6 7])
(my-partition 3 (range 8))

; The same but tail recursive
(defn my-partition [partition-size ls]
  (loop [acc [] ls ls]
    (if (>= (count ls) partition-size)
      (recur (conj acc (take partition-size ls)) (drop partition-size ls))
      acc)))

(my-partition 3 [])
(my-partition 3 [1])
(my-partition 3 [1 2])
(my-partition 3 [1 2 3])
(my-partition 3 [1 2 3 4])
(my-partition 3 [1 2 3 4 5 6 7])
(my-partition 3 (range 8))
