;; 4clojure 77
(defn anagrams [words word]
  (let [sorted-word (sort word)]
    (set (filter #(= sorted-word (sort %))
                 words))))

(anagrams ["meat" "mat" "team" "mate" "eat"] "meat")

(defn find-anagrams [words]
  (let [anagrams (fn [words word]
                   (let [sorted-word (sort word)]
                     (set (filter #(= sorted-word (sort %)) words))))]
    (->> words
         (map (partial anagrams words))
         distinct
         (filter #(> (count %) 1))
         set)))

(find-anagrams ["meat" "mat" "team" "mate" "eat"])
(find-anagrams ["veer" "lake" "item" "kale" "mite" "ever"])