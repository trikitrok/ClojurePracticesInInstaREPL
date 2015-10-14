(defn longest-increasing-sub-seq [ls]
  (loop [increasing-subseqs [] current-seq [] elem (first ls) ls (rest ls)]
    (cond
     (nil? elem)
     (if-let [longest (first (sort-by (comp - count) (remove #(= (count %) 1) increasing-subseqs)))]
       longest
       [])

     (empty? current-seq)
     (let [current-seq (conj current-seq elem)]
       (recur (conj increasing-subseqs current-seq) current-seq (first ls) (rest ls)))

     :else
     (let [previous-elem (last current-seq)]
       (if (> elem previous-elem)
         (recur (conj increasing-subseqs (conj current-seq elem)) (conj current-seq elem) (first ls) (rest ls))
         (recur increasing-subseqs [elem] (first ls) (rest ls)))))))

(longest-increasing-sub-seq [1 0 1 2 3 0 4 5])
(longest-increasing-sub-seq [5 6 1 3 2 7])
(longest-increasing-sub-seq [2 3 3 4 5])
(longest-increasing-sub-seq [7 6 5 4])

(conj [] 1)

(defn f [{:keys [max-increasing-seq current-seq]} elem]
  (if-let [last-elem (last current-seq)]
    (if (> elem last-elem)
      (let [current-seq (conj current-seq elem)]
        (if (< (count max-increasing-seq) (count current-seq))
          {:max-increasing-seq current-seq :current-seq current-seq}
          {:max-increasing-seq max-increasing-seq :current-seq current-seq}))
      {:max-increasing-seq max-increasing-seq :current-seq [elem]})
    {:max-increasing-seq max-increasing-seq :current-seq [elem]}))

(f {:max-increasing-seq [] :current-seq [1]} 0)


(reduce f {:max-increasing-seq [] :current-seq []} [1 0 1 2 3 0 4 5])

(defn longest-increasing-sub-seq [ls]
  (:max-increasing-seq
   (reduce
    (fn [{:keys [max-increasing-seq current-seq]} elem]
      (if-let [last-elem (last current-seq)]
        (if (> elem last-elem)
          (let [current-seq (conj current-seq elem)]
            (if (< (count max-increasing-seq) (count current-seq))
              {:max-increasing-seq current-seq :current-seq current-seq}
              {:max-increasing-seq max-increasing-seq :current-seq current-seq}))
          {:max-increasing-seq max-increasing-seq :current-seq [elem]})
        {:max-increasing-seq max-increasing-seq :current-seq [elem]}))
    {:max-increasing-seq [] :current-seq []} ls)))

(longest-increasing-sub-seq [1 0 1 2 3 0 4 5])
(longest-increasing-sub-seq [5 6 1 3 2 7])
(longest-increasing-sub-seq [2 3 3 4 5])
(longest-increasing-sub-seq [7 6 5 4])

(defn longest-increasing-sub-seq [ls]
  (:max-seq
   (reduce
    (fn [{:keys [max-seq current-seq] :as acc} elem]
      (let [last-elem (last current-seq)]
        (if (or (nil? last-elem) (<= elem last-elem))
          (assoc acc :current-seq [elem])
          (let [new-seq (conj current-seq elem)
                acc (assoc acc :current-seq new-seq)]
            (if (< (count max-seq) (count new-seq))
              (assoc acc :max-seq new-seq)
              acc)))))
    {:max-seq [] :current-seq []} ls)))

(longest-increasing-sub-seq [1 0 1 2 3 0 4 5])
(longest-increasing-sub-seq [5 6 1 3 2 7])
(longest-increasing-sub-seq [2 3 3 4 5])
(longest-increasing-sub-seq [7 6 5 4])


(defn longest-increasing-sub-seq [ls]
  (let [pairs (partition 2 1 ls)
        bigger? (map #(apply < %) pairs)
        runs (partition-by second (map vector pairs bigger?))
        runs (filter #(-> % first second) runs)
        ord-runs (sort-by #(* -1 (count %)) runs)
        best (first ord-runs)
        ans (concat (-> best first first first vector) (map #(-> % first second) best))]
  (if (nil? (first ans))
    '()
    ans)))

(longest-increasing-sub-seq [1 0 1 2 3 0 4 5])
(longest-increasing-sub-seq [5 6 1 3 2 7])
(longest-increasing-sub-seq [2 3 3 4 5])
(longest-increasing-sub-seq [7 6 5 4])
