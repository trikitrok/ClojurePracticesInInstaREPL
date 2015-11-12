;; 4clojure 74
(defn filter-perfect-squares [s]
  (let [perfect-square? #(zero? (- (Math/sqrt %) (Math/floor (Math/sqrt %))))]
    (-> s
        (clojure.string/split #",")
        ((partial map #(Integer/parseInt %)))
        ((partial filter perfect-square?))
        ((partial clojure.string/join ",")))))

(filter-perfect-squares "4,5,6,7,8,9")

(zero? (- (Math/sqrt 4) (Math/floor (Math/sqrt 4))))

(def perfect-square? #(zero? (- (Math/sqrt %) (Math/floor (Math/sqrt %)))))

(perfect-square? 2)
(perfect-square? 4)
(perfect-square? 5)
(perfect-square? 6)

(defn filter-perfect-squares [s]
  (let [perfect-square? #(zero? (- (Math/sqrt %) (Math/floor (Math/sqrt %))))]
    (->> (clojure.string/split s #",")
         (map #(Integer/parseInt %))
         (filter perfect-square?)
         (clojure.string/join ","))))

(filter-perfect-squares "4,5,6,7,8,9")


(defn filter-perfect-squares [s] ;; as-> can't be used in 4Clojure. It's available from Clojure 1.5 on.
  (let [perfect-square? #(zero? (- (Math/sqrt %) (Math/floor (Math/sqrt %))))]
    (as-> s $
          (clojure.string/split $ #",")
          (map #(Integer/parseInt %) $)
          (filter perfect-square? $)
          (clojure.string/join "," $))))

(filter-perfect-squares "4,5,6,7,8,9")
