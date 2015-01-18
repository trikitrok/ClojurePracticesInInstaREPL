(defn bizarre-factorial [n]
  (let [positive-ints-until (fn [n] (range 1N (+ n 1)))]
  (apply * (positive-ints-until n))))

(bizarre-factorial 5)


(bizarre-factorial 100)

(defn my-apply [function sequence]
  (eval (cons function sequence)))

(defn bizarre-factorial2 [n]
  (let [positive-ints-until (fn [n] (range 1N (+ n 1)))]
  (my-apply * (positive-ints-until n))))

(bizarre-factorial2 100)


(= (bizarre-factorial 100)
   (bizarre-factorial2 100))

(defn bizarre-factorial3 [n]
  (let [positive-ints-until (fn [n] (range 1N (+ n 1)))
        multiply (partial apply *)]
  (multiply (positive-ints-until n))))

(= (bizarre-factorial 100)
   (bizarre-factorial2 100)
   (bizarre-factorial3 100))


(defn prefix-of? [candidate sequence]
  (= candidate
     (take (count candidate) sequence)))

(prefix-of? [1 2] [1 2 3 4])
(prefix-of? '(2 3) [1 2 3 4])
(prefix-of? '(2 3) [2 3 4])

(defn tails [sequence]
  (let
    [drop-n (fn [n] (drop n sequence))
    elements-number (count sequence)]
    (map drop-n (range 0 (+ elements-number 1)))))

(tails '(1 2 3 4))

(def a '(1 2 3 4))

(range 0 (count a))
(range 0 (+ (count a) 1))
(drop 3 a)
(drop 4 a)

(defn tails2 [sequence]
  (map drop (range 0 (+ (count sequence) 1)) (repeat (+ (count sequence) 1) sequence)))

(tails2 '(1 2 3 4))

(= (tails a) (tails2 a))

(repeat 4 [1 2 3 4])

(defn tails3 [sequence]
  (let
    [number-of-tails (+ (count sequence) 1)
     sequence-copies (repeat number-of-tails sequence)
     elements-to-drop (range 0 number-of-tails)]
    (map drop elements-to-drop sequence-copies)))

(tails3 '(1 2 3 4))

(= (tails a) (tails2 a) (tails3 a))







