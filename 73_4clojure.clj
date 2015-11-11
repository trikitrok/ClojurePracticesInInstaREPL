;; 4clojure 73 Analyze a Tic-Tac-Toe Board
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

(defn any-complete? [coll mark]
  (some (fn [column]
          (= 3 (count (filter #(= mark %) column))))
        coll))

(any-complete?
 (columns
  [[:x :e :o]
   [:x :e :e]
   [:x :e :o]])
 :x)

(any-complete?
 (rows
  [[:x :e :o]
   [:x :e :e]
   [:x :e :o]])
 :x)

(any-complete?
 (rows
  [[:x :e :o]
   [:x :x :x]
   [:x :e :o]])
 :x)

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

(def diag1 [[0 0] [1 1] [2 2]])
(def diag2 [[0 2] [1 1] [2 0]])


(defn diagonals [board]
  [(map #(get-in board %) [[0 0] [1 1] [2 2]])
   (map #(get-in board %) [[0 2] [1 1] [2 0]])])

(diagonals
 [[0 1 2]
  [3 4 5]
  [6 7 8]])

(defn who-wins? [board]
  (let [columns (partial apply (partial map (fn [a b c] [a b c])))
        rows identity
        diagonals (fn diagonals [board]
                    [(map #(get-in board %) [[0 0] [1 1] [2 2]])
                     (map #(get-in board %) [[0 2] [1 1] [2 0]])])
        any-complete? (fn [coll mark]
                        (some (fn [cell] (= 3 (count (filter #(= mark %) cell)))) coll))
        wins? (fn [board mark]
                (or (any-complete? (diagonals board) mark)
                    (any-complete? (columns board) mark)
                    (any-complete? (rows board) mark)))]
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
