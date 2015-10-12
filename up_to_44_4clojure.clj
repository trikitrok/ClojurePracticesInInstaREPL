(fn fib [n]
  (take n (map first (iterate (fn [[a b]] [b (+ a b)]) [1 1]))))

(fib 1)
(fib 2)
(fib 3)
(fib 4)
(fib 5)
(fib 6)


(def a '((1 2) 3 [4 [5 6]]))

(defn my-flatten
  ([coll] (my-flatten [] coll))
  ([acc coll]
   (cond (empty? coll) acc
         (sequential? (first coll)) (recur (concat acc (my-flatten (first coll))) (rest coll))
         :else (recur (concat acc [(first coll)]) (rest coll)))))

(my-flatten a)


(defn my-flatten [coll]
  (loop [acc [] coll coll]
    (cond (empty? coll) acc
          (sequential? (first coll)) (recur (concat acc (my-flatten (first coll))) (rest coll))
          :else (recur (concat acc [(first coll)]) (rest coll)))))

(my-flatten a)


(fn my-flatten
  [coll]
  (loop [acc [] coll coll]
    (cond (empty? coll) acc
          (sequential? (first coll)) (recur (concat acc (my-flatten (first coll))) (rest coll))
          :else (recur (concat acc [(first coll)]) (rest coll)))))

(fn flatten* [x]
  (if (coll? x)
    (mapcat flatten* x)
    [x]))

(fn [s] (apply str (filter #(Character/isUpperCase %) s)))

(seq (into #{} "leeeeeerrroyyy"))

(seq "leeeeeerrroyyy")

(seq (apply sorted-set "leeeeeerrroyyy"))

(distinct "leeeeeerrroyyy")

(distinct [1 1 2 3 3 2 2 3])

(def ls [1 1 2 3 3 2 2 3])

(defn compress [ls]
  (loop [compressed-ls [(first ls)] last-elem (first ls)  ls (rest ls)]
    (if (empty? ls)
      compressed-ls
      (let [current (first ls)]
        (if (= current last-elem)
          (recur compressed-ls last-elem (rest ls))
          (recur (conj compressed-ls current) current (rest ls)))))))

(compress ls)
(compress "leeeeeerrroyyy")

(partition-by identity ls)
(mapcat distinct (partition-by identity ls))

(map first (partition-by identity ls))

(defn duplicate [ls]
  (mapcat #(vector % %) ls))

(duplicate [1 3 5 8])

(defn duplicate [ls]
  (interleave ls ls))

(duplicate [1 3 5 8])

(defn replicate [ls n]
  (mapcat #(repeat n %) ls))

(replicate [1 3 5 8] 3)

(defn my-range [a b]
  (take (- b a) (iterate inc a)))

(my-range 1 4)

(my-range -2 2)


(#(repeat (- %2 %1) %1) -2 2)

(map-indexed + [-2 -2 -2 -2])

((fn [a b]
   (map-indexed + (repeat (- b a) a))) -2 2)

(re-seq #"[A-Z]+" "bA1B3Ce ")
(re-seq #"[A-Z]" "bA1B3Ce ")
(re-seq #"[A-Z]*" "bA1B3Ce ")

(defn my-max [& ls]
  (last (sort ls)))

(my-max 9 3 2 5 1)

(def my-interleave (partial mapcat vector))

(my-interleave [1 3 5 7] [2 4 6 8])

(defn my-interpose [elem ls]
  (drop-last (interleave ls (repeat elem))))

(my-interpose 0 [1 3 5])


(defn drop-every-nth [ls n]
  (remove nil?
          (map-indexed #(if (and (>= (inc %1) n) (zero? (mod (inc %1) n))) nil %2)
                       ls)))

(drop-every-nth [1 2 3 4 5 6 7 8] 3)

(partition-all 2 3 [1 2 3 4 5 6 7 8])

(defn drop-every-nth [ls n]
  (flatten (partition-all (dec n) n ls)))

(drop-every-nth [1 2 3 4 5 6 7 8] 3)

(defn factorial [n]
  (apply * (range 1 (inc n))))

(factorial 4)

(defn deinterleave [ls n]
  (map #(flatten (partition 1 n (drop % ls))) (range 0 n)))

(deinterleave [1 2 3 4 5 6] 2)

(deinterleave (range 9) 3)

(partition 2 [1 2 3 4 5 6 7 8])
(partition 3 (range 9))

(apply map list (partition 2 [1 2 3 4 5 6 7 8]))
(apply map list (partition 3 (range 9)))

(defn deinterleave [ls n]
  (apply map list (partition n ls)))

(deinterleave [1 2 3 4 5 6] 2)

(deinterleave (range 9) 3)

(take 5 (drop 2 (cycle [1 2 3 4 5])))

(defn rotate [n ls]
  (let [num-elems (count ls)]
    (take num-elems (drop (mod n num-elems) (cycle ls)))))

(rotate 2 [1 2 3 4 5])
(rotate -2 [1 2 3 4 5])
(rotate -4 [:a :b :c])

(mod -2 5)

(drop 2 [1 2 3 4 5])
(take 2 [1 2 3 4 5])

(mod -4 3)

(drop 2 [:a :b :c])
(take 2 [:a :b :c])

(defn rotate [n ls]
  (let [pos (mod n (count ls))]
    (concat (drop pos ls) (take pos ls))))

(rotate 2 [1 2 3 4 5])
(rotate -2 [1 2 3 4 5])
(rotate -4 [:a :b :c])


