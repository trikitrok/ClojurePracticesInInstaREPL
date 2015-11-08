;;4clojure 58
(defn my-comp-2 [f1 f2]
  (fn [& args] (f1 (apply f2 args))))

((my-comp-2 rest reverse) [1 2 3 4])

((my-comp-2 #(mod % 8) +) 3 5 7 9)

((my-comp-2 zero? (my-comp-2 #(mod % 8) +)) 3 5 7 9)

(defn my-comp [& fs]
  (reduce my-comp-2 fs))

((my-comp rest reverse) [1 2 3 4])

((my-comp #(mod % 8) +) 3 5 7 9)

((my-comp zero? #(mod % 8) +) 3 5 7 9)

(defn my-comp [& fs]
  (reduce
   (fn [acc-f f]
     (fn [& args] (acc-f (apply f args))))
   fs))

((my-comp rest reverse) [1 2 3 4])

((my-comp #(mod % 8) +) 3 5 7 9)

((my-comp zero? #(mod % 8) +) 3 5 7 9)


;;4 clojure 59
(defn my-juxt [& fs]
  (fn [& args]
    (map #(apply % args) fs)))

((my-juxt + max min) 2 3 5 1 6 4)

((my-juxt #(.toUpperCase %) count) "hello")

((my-juxt :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10})


;; 4clojure 60
(take 5 (reductions + (range)))

(defn my-reductions-2 [f coll]
  (cons (first coll)
        (cons (f (first coll) (second coll))
              nil)))

(my-reductions-2 + [1 4])

(defn my-reductions-no-lazy
  ([f coll]
   (my-reductions-no-lazy f (first coll) (rest coll)))
  ([f init coll]
   (cons init
         (when (first coll)
           (my-reductions-no-lazy
            f
            (f init (first coll))
            (rest coll))))))

(my-reductions-no-lazy + [1])

(my-reductions-no-lazy + [1 4 6 7])

(my-reductions-no-lazy + 5 [1 4 6 7])

(my-reductions-no-lazy conj [1] [2 3 4])

(my-reductions-no-lazy * 2 [3 4 5])

(my-reductions-no-lazy * [3 4 5])

(defn my-reductions-lazy
  ([f coll]
   (my-reductions-lazy f (first coll) (rest coll)))
  ([f init coll]
   (cons init
         (lazy-seq
          (when-let [next-elem (first coll)]
           (my-reductions-lazy
            f
            (f init next-elem)
            (rest coll)))))))

(my-reductions-lazy + [1])

(my-reductions-lazy + [1 4 6 7])

(my-reductions-lazy + 5 [1 4 6 7])

(my-reductions-lazy conj [1] [2 3 4])

(my-reductions-lazy * 2 [3 4 5])

(my-reductions-lazy * [3 4 5])

(take 5 (my-reductions-lazy + (range)))
