; A Future of Delays and Promises (Clojure)
; https://medium.com/tech-and-the-city/a-future-of-delays-and-promises-3ec843d44c9#.1cfm544ix

;; When we write serial code, as we do in Clojure, 
;; we’re essentially binding the following: 
;; a task’s definition, its execution, and requiring its result. 

; Futures -> define a task and place it on another thread without requiring the result right away
(future (Thread/sleep 5000)
        (println "I'll print after 5 seconds"))
(println "I'll print immediately")

; Delays -> a technique to define a task without having to execute it or require the result
(def delayed-greeting
  (delay (let [name "Malina"]
           (println "Hi, my name is " name)
           name)))

(force delayed-greeting)

;; Promises -> allow you to express that you expect a result, without defining when and how
(def my-promise (promise))
(deliver my-promise (+ 1 2))
(deref my-promise) ; also @my-promise