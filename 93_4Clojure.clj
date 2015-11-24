;; 4Clojure 93
(defn partially-flatten [s]
  (let [nested? #(every? sequential? %)]
    (if (empty? s)
      '()
      (if (nested? (first s))
        (concat (partially-flatten (first s))
                (partially-flatten (rest s)))
        (cons (first s) (partially-flatten (rest s)))))))

; This first test was not in the given tests,
; so this first version is doing too much
; Even though it pass all the tests,
; the problem they ask to solve is easier than
; the one this version is resolving
(= (partially-flatten ["Do" ["Nothing"]])
   ["Do" ["Nothing"]])

(= (partially-flatten [["Do"] ["Nothing"]])
   [["Do"] ["Nothing"]])

(= (partially-flatten [[[[:a :b]]] [[:c :d]] [:e :f]])
   [[:a :b] [:c :d] [:e :f]])

(= (partially-flatten '((1 2)((3 4)((((5 6)))))))
   '((1 2)(3 4)(5 6)))

; This second version also passes all the given tests
; but only works for sequences of sequential things
(defn partially-flatten [s]
  (if (every? sequential? s)
    (mapcat partially-flatten s)
    [s]))

(= (partially-flatten [["Do"] ["Nothing"]])
   [["Do"] ["Nothing"]])

(= (partially-flatten [[[[:a :b]]] [[:c :d]] [:e :f]])
   [[:a :b] [:c :d] [:e :f]])

(= (partially-flatten '((1 2)((3 4)((((5 6)))))))
   '((1 2)(3 4)(5 6)))

; Does not work for sequences with any not sequential things
; outside the last nesting level
(= (partially-flatten ["Do" ["Nothing"]])
   ["Do" ["Nothing"]])