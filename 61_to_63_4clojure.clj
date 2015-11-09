;; 4clojure 61
(defn my-zipmap [ks vs]
  (apply hash-map (interleave ks vs)))

(interleave [:a :b :c] [1 2 3])

(apply hash-map '(:a 1 :b 2 :c 3))

(my-zipmap [:a :b :c] [1 2 3])
;;

(defn my-zipmap [ks vs]
  (reduce into (map hash-map ks vs)))

(map hash-map [:a :b :c] [1 2 3])

(my-zipmap [:a :b :c] [1 2 3])
;;

(into (into {} {:a 1}) {:b 2})

(defn my-zipmap [ks vs]
  (apply assoc {} (interleave ks vs)))

(assoc {} :a 1 :b 2 :c 3)

(my-zipmap [:a :b :c] [1 2 3])
;;

;; 4clojure 62

(defn my-iterate [f init]
  (cons init
        (lazy-seq
         (my-iterate f (f init)))))

(take 5 (my-iterate #(* 2 %) 1))
(take 100 (my-iterate inc 0))
(take 9 (my-iterate #(inc (mod % 3)) 1))
;;

(defn my-iterate [f init]
  (reductions (fn [i _] (f i)) (repeat init)))

(take 5 (my-iterate #(* 2 %) 1))
(take 100 (my-iterate inc 0))
(take 9 (my-iterate #(inc (mod % 3)) 1))
;;

;; 4clojure 63
(group-by #(> % 5) [1 3 6 8])

(defn my-group-by [f coll]
  (reduce
   (fn [acc elem]
     (let [res (f elem)]
       (if-let [ls (get acc res)]
         (assoc acc res (conj ls elem))
         (assoc acc res [elem]))))
   {}
   coll))

(my-group-by #(> % 5) [1 3 6 8])
;;

(defn my-group-by [f coll]
  (reduce
   (fn [acc elem]
     (let [res (f elem)]
       (assoc
         acc res (conj (get acc res []) elem))))
   {}
   coll))

(my-group-by #(> % 5) [1 3 6 8])
;;

(defn my-group-by [f coll]
  (apply merge-with concat (map (fn [elem] (hash-map (f elem) [elem])) coll)))

(my-group-by #(> % 5) [1 3 6 8])

(merge-with concat {:kiko [5]} {:koko [1]} {:kiko [4]})
