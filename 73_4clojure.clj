;; 4clojure 73 Analyze a Tic-Tac-Toe Board

[[:x :e :o]
 [:x :e :e]
 [:x :e :o]]

((partial apply (partial map (fn [a b c] [a b c])))
 [[:x :e :o]
  [:x :e :e]
  [:x :e :o]])

(def columns (partial apply (partial map (fn [a b c] [a b c]))))

(def rows identity)

(columns [[:x :e :o]
          [:x :e :e]
          [:x :e :o]])


(defn any-complete-column? [board mark]
  (some (fn [column]
          (= 3 (count (filter #(= mark %) column))))
        (columns board)))

(any-complete-column?
 [[:x :e :o]
  [:x :e :e]
  [:e :e :o]]
 :x)

(any-complete-column?
 [[:x :e :o]
  [:x :e :e]
  [:x :e :o]]
 :x)

(any-complete-column?
 [[:x :e :o]
  [:x :e :e]
  [:x :e :o]]
 :o)

(defn any-complete? [mark coll]
  (some (fn [column]
          (= 3 (count (filter #(= mark %) column))))
        coll))

(any-complete?
 :x
 (columns
  [[:x :e :o]
   [:x :e :e]
   [:x :e :o]]))

(any-complete?
 :x
 (rows
  [[:x :e :o]
   [:x :e :e]
   [:x :e :o]]))

(any-complete?
 :x
 (rows
  [[:x :e :o]
   [:x :x :x]
   [:x :e :o]]))

; diag 1
(get-in
 [[1 :e :o]
  [:x :x :x]
  [:x :e :o]] [0 0])

(get-in
 [[:o :e :o]
  [:x 2 :x]
  [:x :e :o]] [1 1])

(get-in
 [[:x :e :o]
  [:x :o :x]
  [:x :e 3]] [2 2])

; diag 2
(get-in
 [[:x :e 1]
  [:x :e :x]
  [:e :e 3]] [0 2])

(get-in
 [[:x :e :o]
  [:x 2 :x]
  [:x :e :o]] [1 1])

(get-in
 [[:x :e :o]
  [:x :x :x]
  [3 :e :o]] [2 0])

(defn diagonals [board]
  [(map #(get-in board %) [[0 0] [1 1] [2 2]])
   (map #(get-in board %) [[0 2] [1 1] [2 0]])])

(diagonals
 [[0 1 2]
  [3 4 5]
  [6 7 8]])

((fn [board mark]
  (some (partial any-complete? mark)
        [(diagonals board) (columns board) (rows board)]))
  [[:x :e :o]
  [:x :o :x]
  [:x :e 3]]
 :x)

((fn [board mark]
  (some (partial any-complete? mark)
        [(diagonals board) (columns board) (rows board)]))
  [[:x :e :o]
  [:o :o :x]
  [:x :e 3]]
 :x)

((fn [board mark]
  (some (partial any-complete? mark)
        [(diagonals board) (columns board) (rows board)]))
  [[:x :e :o]
  [:o :o :x]
  [:x :x :x]]
 :x)

(defn who-wins? [board]
  (let [diagonals-idxs [[[0 0] [1 1] [2 2]] [[0 2] [1 1] [2 0]]]
        diagonal (partial map #(get-in board %))
        columns (partial apply (partial map (fn [a b c] [a b c])))
        rows identity
        any-complete? (fn [mark coll]
                        (some (fn [cell] (= 3 (count (filter #(= mark %) cell)))) coll))
        wins? (fn [board mark]
                (some (partial any-complete? mark)
                      [(map diagonal diagonals-idxs) (columns board) (rows board)]))]
    (cond (wins? board :x) :x
          (wins? board :o) :o
          :else nil)))

(who-wins? [[:e :e :e]
            [:e :e :e]
            [:e :e :e]])

(who-wins? [[:x :e :o]
            [:x :e :e]
            [:x :e :o]])

(who-wins? [[:e :x :e]
            [:o :o :o]
            [:x :e :x]])
