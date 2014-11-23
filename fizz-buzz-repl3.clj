(def fizzes (cycle '("" "" "Fizz")))

(def buzzes (cycle '("" "" "" "" "Buzz")))

(def naturals (iterate #(+ % 1) 1))

(take 10 naturals)

(def naturals (drop 1 (range)))

(map #(str %) naturals)

(def fizzes-buzzes (map #(str %1 %2) fizzes buzzes))

(nth fizzes-buzzes 14)

(clojure.string/replace "* *" #" " "moko")

(clojure.string/join
 " "
 (take 100
       (map #(clojure.string/replace %1 #"^$" %2)
            fizzes-buzzes
            (map #(str %) naturals))))

(clojure.string/join
 " "
 (take 100
       (map #(clojure.string/replace %1 #"^$" %2)
            (map #(str %1 %2)
                 (cycle '("" "" "Fizz"))
                 (cycle '("" "" "" "" "Buzz")))
            (map #(str %) naturals))))

(clojure.string/join
 " "
 (take 100
(map #(clojure.string/replace (str %1 %2) #"^$" (str %3))
     (cycle '("" "" "Fizz"))
     (cycle '("" "" "" "" "Buzz"))
     naturals)))

(clojure.string/join
 " "
 (take 100
       (map #(nth
              (cycle [% % "Fizz" % "Buzz" "Fizz" % % "Fizz" "Buzz" % "Fizz" % % "FizzBuzz"])
              (dec %))
            naturals)))
