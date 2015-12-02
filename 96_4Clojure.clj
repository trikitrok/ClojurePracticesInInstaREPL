;; 4Clojure 96
(defn symmetric-binary-tree?
  [[_ l r]]
  ((fn mirror?
     [[e1 l1 r1 :as l] [e2 l2 r2 :as r]]
     (or (= nil l r)
         (and (= e1 e2)
              (mirror? l1 r2)
              (mirror? l2 r1))))
   l r))

(= (symmetric-binary-tree? '(:a (:b nil nil) (:b nil nil)))
   true)

(= (symmetric-binary-tree? '(:a (:b nil nil) nil))
   false)

(= (symmetric-binary-tree? '(:a (:b nil nil) (:c nil nil)))
   false)

(= (symmetric-binary-tree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                            [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
   true)