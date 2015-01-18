(def my-vec [1 2 3 4])

(first my-vec)
(last my-vec)

; rest resturns a seq
(rest my-vec)
(seq? (rest my-vec))

; cons returns a seq
(cons 5 my-vec) ; returns a sequence

(def a-seq (cons 5 my-vec))

(vector? my-vec)
(list? my-vec)
(seq? my-vec)

(vector? a-seq)
(list? a-seq)
(seq? a-seq)

(def another-seq (seq [1 2 3]))

(seq? another-seq)
(list? another-seq)
(vector? another-seq)

(def another-seq (seq '(1 2 3)))

(seq? another-seq)
(list? another-seq)
(vector? another-seq)

(seq '())
(seq [])

(next my-vec)
(seq (rest my-vec))
(=
 (next my-vec)
 (seq (rest my-vec)))

(rest '())
(next '())
(seq (rest '()))

(class (rest my-vec))
(class my-vec)

(first {:1st "poko" :2nd "loko"})
(rest {:1st "poko" :2nd "loko"})

(first #{ :the :quick :brown :fox})
(rest #{ :the :quick :brown :fox})

(sorted-set #{:the :quick :brown :fox})

(sorted-map :b :a :c :d)

; conj vs cons
(conj my-vec 9 8)
(conj a-seq 9 3)
(conj '(1 2 3) 9)
(conj #{1 2} 9)
(conj {:a 1 :b 2} {:c 3})

(cons 9 my-vec)
(cons 9 a-seq)
(cons 9 '(1 2 3))
(cons 9 #{1 2})
(cons [:c 3] {:a 1 :b 2}) ; funny!

; into
(into my-vec [9])
(into a-seq [9])
(into '(1 2 3) [9])
(into #{1 2} [9])
(into {:a 1 :b 2} {:c 3})

; Lazy infinite sequences
(def naturals (iterate inc 1))

(take 10 naturals)

;(repeat 1)
(take 2 (repeat 1))

;(cycle [0 1 2 3])
(take 8 (cycle [0 1 2 3]))

(interleave naturals ["A" "B" "C" "D" "E"])

(interpose ", " '("koko" "moko" "loko"))
(apply str (interpose ", " '("koko" "moko" "loko")))
(clojure.string/join "," '("koko" "moko" "loko"))

(list 1 2 3 4)
(vector 1 2 3 4)
(vec '(1 2 3 4))
(hash-set 3 2 3 2)
(set [2 2 3 3])
(sorted-set 1 2 3 2)
(hash-map :1st 1 :2nd 2 :3rd 3)
(sorted-map :1st 1 :2nd 2 :3rd 3)

(take 10 (filter odd? naturals))

(take 10 (filter even? naturals))

(take 2 (filter (set [1 "A" 4 3 true]) naturals))

(take-while odd? [1 3 5 2 1 2])

(take-while (complement even?) [5 2 1 2])

(take-while
 (fn [n]
   (< n 5))
 naturals)

(take-while (complement #{\a \e \i \o \u}) "the quick brown fox")

(drop-while (complement #{\a \e \i \o \u}) "the quick brown fox")

(split-at 5 (range 10))
(split-with #(<= % 10) (range 0 20 3))

(every? odd? [])
(every? odd? [1 3 5])
(every? odd? [1 2 5])

(some even? [ 1 3 5])
(some even? [1 2 5])

(some identity [nil false 1 nil 2])

(not-every? even? [2 1 4 5])
(not (every? even? [2 1 4 5]))

(not-any? even? naturals)
(not (some even? naturals))


(map #(format "<p>%s</p>" %) ["the" "quick" "brown" "fox"])

(map #(format "<%s>%s</%s>" %1 %2 %1)
     ["h1" "h2" "h3" "h1"]
     ["the" "quick" "brown" "fox"])

(reduce + (range 1 11))
(reduce * (range 1 11))

(sort [41 1 7 11])
(sort > [41 1 7 11])

(sort-by #(.toString %) [41 1 7 11])

(sort-by :grade > [{:grade 83} {:grade 100} {:grade 90}])

(sort-by :grade < [{:grade 83} {:grade 100} {:grade 90}])

(for [word ["the" "quick" "brown" "fox"]]
  (format "<p>%s</p>" word))

; :while -> evaluation continues while its expression holds true
(for [n naturals :while (even? n)] n)

(for [n naturals :while (odd? n)] n)

(for [n [1 4 11] :while ((fn [n] (< n 10)) n) ] n)

(for [n [1 4 11] :while ((fn [n] (> n 10)) n) ] n)

(for [n [1 4 11] :when ((fn [n] (> n 10)) n) ] n)

(for [n [1 4 11] :when ((fn [n] (< n 10)) n) ] n)


(for [file "ABCDEFGH"
      rank (range 1 9)]
  (format "%c%d" file rank))

(for [rank (range 1 9)
      file "ABCDEFGH"]
  (format "%c%d" file rank))

(def x (for [i (range 1 3)] (do (print i) i)))
(doall x)
(dorun x)

(first (.getBytes "hello"))
(rest (.getBytes "hello"))

(cons (int \h) (rest (.getBytes "ello")))

(first (System/getProperties))

(rest (System/getProperties))

(first "Hello")
(rest "Hello")

(cons \H "ello")

(reverse "Hello")
(apply str (reverse "Hello"))

(re-seq #"\w+" "the quick brown fox")

(sort (re-seq #"\w+" "the quick brown fox"))

(drop 2 (re-seq #"\w+" "the quick brown fox"))

(map #(.toUpperCase %)
     (re-seq #"\w+" "the quick brown fox"))

(import '(java.io File))
(.listFiles (File. "."))
(seq (.listFiles (File. ".")))

(map #(.getName %) (seq (.listFiles (File. "."))))

(map #(.getName %) (.listFiles (File. ".")))

(count (file-seq (File. "/media/trikitrok/0d7e8f3c-a84a-4aec-9319-054a8b4d873f/MisCosas/Documentos/Clojure/0_MiClojure/ClojurePractices")))

(defn minutes-to-millis [mins]
  (* mins 1000 60))

(defn recently-modified? [file]
  (> (.lastModified file)
     (- (System/currentTimeMillis) (minutes-to-millis 60))))

 (file-seq (File. "/media/trikitrok/0d7e8f3c-a84a-4aec-9319-054a8b4d873f/MisCosas/Documentos/Clojure/0_MiClojure/ClojurePractices"))

(filter recently-modified? (file-seq (File. "/media/trikitrok/0d7e8f3c-a84a-4aec-9319-054a8b4d873f/MisCosas/Documentos/Clojure/0_MiClojure/ClojurePractices")))





























