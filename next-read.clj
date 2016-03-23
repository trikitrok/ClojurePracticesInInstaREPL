;; lein exec ./next-read.clj some_folder_name

(defn next-read [folder]
  (->>
    (clojure.java.io/file (str "./" folder))
    (.listFiles)
    seq
    (remove #(.isDirectory %))
    rand-nth
    (.getName)))

(let [folder (second *command-line-args*)]
  (println (next-read folder)))