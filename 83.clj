;; 4clojure 83
(defn half-truth [& bools]
  (and (not (every? true? bools))
       (not= nil (some true? bools))))

;; #(and (not (every? true? %&))
;;      (not= nil (some true? %&)))

(half-truth false false)
(half-truth true false)
(half-truth true)
(half-truth false true false)
(half-truth true true true)
(half-truth true true true false)