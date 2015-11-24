;; 4Clojure 95
(defn binary-tree? [tree]
  (and (sequential? tree)
       (= 3 (count tree))
       (or (nil? (second tree)) (binary-tree? (second tree)))
       (or (nil? (nth tree 2)) (binary-tree? (nth tree 2)))))

(binary-tree? nil)
(binary-tree? [])
(binary-tree? [1])
(binary-tree? 1)
(binary-tree? [1 2 3])

(= (binary-tree? '(:a (:b nil nil) nil)) true)
(= (binary-tree? '(:a (:b nil nil))) false)
(= (binary-tree? [1 nil [2 [3 nil nil] [4 nil nil]]]) true)
(= (binary-tree? [1 [2 nil nil] [3 nil nil] [4 nil nil]]) false)
(= (binary-tree? [1 [2 [3 [4 nil nil] nil] nil] nil]) true)
(= (binary-tree? [1 [2 [3 [4 false nil] nil] nil] nil]) false)
(= (binary-tree? '(:a nil ())) false)

(defn binary-tree? [tree]
  (and (sequential? tree)
       (= 3 (count tree))
       (every? #(or (nil? %) (binary-tree? %)) (rest tree))))

(= (binary-tree? '(:a (:b nil nil) nil)) true)
(= (binary-tree? '(:a (:b nil nil))) false)
(= (binary-tree? [1 nil [2 [3 nil nil] [4 nil nil]]]) true)
(= (binary-tree? [1 [2 nil nil] [3 nil nil] [4 nil nil]]) false)
(= (binary-tree? [1 [2 [3 [4 nil nil] nil] nil] nil]) true)
(= (binary-tree? [1 [2 [3 [4 false nil] nil] nil] nil]) false)
(= (binary-tree? '(:a nil ())) false)