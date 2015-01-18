(use '[clojure.java.io :only (reader)])

(take 2
      (line-seq
       (reader
        "/home/trikitrok/Desktop/Quotes.txt")))
(with-open [rdr (reader "/home/trikitrok/Desktop/Quotes.txt")]
  (count (line-seq rdr)))

(with-open [rdr (reader "/home/trikitrok/Desktop/Quotes.txt")]
  (count (filter #(re-find #"\S" %) (line-seq rdr))))

(re-find #"\S" "")
(re-find #"\S" "a")

(and nil true)
(and true nil)
(and true "hola")
(and "hola" true)

(defn non-blank? [line]
  (not= nil (re-find #"\S" line)))

(non-blank? "")
(non-blank? "hola")


(defn non-git? [file]
  (not (.contains (.toString file) ".git")))

(non-git? "/home/trikitrok/Desktop/Quotes.txt")

(defn clojure-source? [file]
  (.contains (.toString file) ".clj"))

(clojure-source? "/home/trikitrok/Desktop/Quotes.txt")
(clojure-source? "/media/trikitrok/0d7e8f3c-a84a-4aec-9319-054a8b4d873f/MisCosas/Documentos/Clojure/0_MiClojure/ClojurePractices/ExploringClojure.clj")

(defn clojure-loc [base-file]
  (reduce
   +
   (for [file (file-seq base-file) :when (and (clojure-source? file) (non-git? file))]
     (with-open [rdr (reader file)]
       (count (filter non-blank? (line-seq rdr)))))))

(clojure-loc
 (java.io.File.
  "/media/trikitrok/0d7e8f3c-a84a-4aec-9319-054a8b4d873f/MisCosas/Documentos/Clojure/0_MiClojure/ClojurePractices"))


;; Functions on lists
(def coll (list 1 2 3))

(peek coll)
(pop coll)
(rest ())
; (pop ()) -> exception

;; Functions on vectors
(def a-vec [1 2 3 4])

(peek a-vec)

(pop a-vec)

(get a-vec 2)
(get a-vec 10)

(a-vec 1)
; (a-vec 5) ; -> exception

(assoc a-vec 1 "2")

(subvec a-vec 1 4)
(subvec a-vec 1)
(subvec a-vec 1 3)
(take 2 (drop 1 a-vec)) ; subvec is faster for vectors

(def a-map (hash-map :a 1 :b 2 :c 3))

(keys a-map)
(vals a-map)

(get a-map :c)
(get a-map :d)
(get a-map :d "not found")

(a-map :a)
(:a a-map)

(contains? a-map :c)
(contains? a-map :d)

(def score {:stu nil :joey 100})

(:stu score)
(contains? score :stu)

(assoc a-map :moko "loko")
(dissoc a-map :a)

(select-keys a-map [:a :c])

(merge a-map score)

(def a-2-map {:a "moko" :b "koko" :c "loko"})

(merge a-map a-2-map)
(merge a-2-map a-map)

(merge-with #(str %1 " " %2) a-map a-2-map)

(def languages #{"java" "c" "clojure"})
(def beverages #{"java" "chai"})

(clojure.set/union languages beverages)
(clojure.set/intersection languages beverages)
(clojure.set/difference languages beverages)
(clojure.set/difference beverages languages)
(clojure.set/select #(= 1 (.length %)) languages)
(clojure.set/select #(= 4 (.length %)) beverages)











