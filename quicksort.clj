(ns sorting)

(split-with #(< % 3) [1 4 3 5 2])

(group-by #(< % 3) [1 4 3 5 2])
(get (group-by #(< % 3) [1 4 3 5 2]) true)
(get (group-by #(< % 3) [1 4 3 5 2]) false)

(defn smaller-and-greater [n coll]
  (let [sg (group-by #(<= % n) coll)]
    [(get sg true)
     (get sg false)]))

(smaller-and-greater 3 [1 4 3 5 2])

(defn quicksort [coll]
  (if (empty? coll)
    '()
    (let [el (first coll)
          [smaller greater] (smaller-and-greater el (rest coll))]
      (concat (quicksort smaller)
              (list el)
              (quicksort greater)))))

(quicksort [])
(quicksort [2 3 1])
(quicksort [1 4 3 5 2])
(quicksort [1 6 3 3 2])


(for [x [1 4 3 5 2] :when (<= x 3)] x)
(for [x [1 4 3 5 2] :when (> x 3)] x)

(defn quicksort [coll]
  (if (empty? coll)
    '()
    (let [el (first coll)
          smaller (filter #(<= % el) (rest coll))
          greater (filter #(> % el) (rest coll))]
      (concat (quicksort smaller)
              (cons el (quicksort greater))))))

(quicksort [])
(quicksort [2 3 1])
(quicksort [1 4 3 5 2])
(quicksort [1 6 3 3 2])
