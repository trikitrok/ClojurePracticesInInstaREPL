;; Anything you type in here will be executed
;; immediately with the results shown on the
;; right.
(+ 2 3)
(concat [1 2 3] [5 7 9])
(+ 1 3 6)
(+)
(/ 22 5)
(/ 22 5.0)
(quot 22 5)
(rem 22 7)
(+ 1 (/ 0.00001 1000000000000000000))
(+ 1 (/ 0.00001M 1000000000000000000))
(* 1000 1000 1000 1000 1000 100 100000000000000000000000000000000000N)
(println "This is a\nmultiline string")
"This is a\nmultiline string"
(.toUpperCase "hello")
(str 1 2 3 nil 5)
(str \a \b \c)
(str \a \b \c \space \1 \2 \3)
(interleave "Attack at midnight" "The purple elephant chortled")

(apply str (interleave "Attack at midnight" "The purple elephant chortled"))
(apply str (take-nth 2 "ATthtea cpku raptl em iedlneipghhatn"))
(true? true)
(false? false)
(false? nil)
(nil? nil)
(if nil "nil evaluates to true" "nil evaluates to false")
(if () "We're in Clojure" "We're in common Lisp")
(def inventors {"Lisp" "MacCarthy" "Clojure" "Hickey"})
(inventors "Lisp")
(inventors "foo")
("Hickey" inventors)
(def inventors {:Lisp "MacCarthy" :Clojure "Hickey"})
(inventors :Lisp)
(:Lisp inventors)
:foo
;(defrecord Book [title author])
;(->Book "Anathem" "Neal Stephenson")
;(Book. "koko" "moko")
;#user.Book{:title "mo", :author "ko"}
'(1 2)
(string? "hi")
(keyword? :hi)
(symbol? 'hello)
(defn greeting
  "Returns a greeting of the form 'Hello, username'"
  ([] (greeting "anonymous"))
  ([username]
  (str "Hello, " username)))
(greeting "lolo")
(doc greeting)
(greeting)
(defn date [person-1 person-2 & chaperones]
  (str person-1 " and " person-2 " went out with " (count chaperones) " chaperones"))
(date "Romeo" "Juliet" "Friar Lawrence" "Nurse" "Momo")
(require '[clojure.string :as str])
(str/split "A fine day it is" #"\W+")
(filter (fn [w] (> (count w) 2)) (str/split "A fine day it is" #"\W+"))
(defn make-greeting [greeting-prefix]
  (fn [username] (str greeting-prefix ", " username)))
(def hi-greeting (make-greeting "hi"))
(hi-greeting "koko")
((make-greeting "aloha") "pepe")
(def foo 10)
(println (var foo))
(println #'foo)
(defn square-corners [bottom left size]
  (let [top (+ bottom size)
        right (+ left size)]
    [[bottom right] [top left] [top right] [bottom right]]))
(square-corners 0 0 5)
(defn test-let [a b]
  (let [c (+ a b)
        d (+ c a)
        a (+ a d)
        e (+ a b)]
    [a b c d e]))
(test-let 1 2)
; let is like Racket's let*
(meta #'str)
(defn ^{:tag String} shout [^{:tag String} s] (.toUpperCase s))
(meta #'shout)

(defn ^String hush [^String s] (.toLowerCase s))
(meta #'hush)

(defn greet-author2 [{fname :first-name}]
  (println "Hello," fname))

(greet-author2 {:last-name "Vinge" :first-name "Vernor"})

(let [[x y] [1 2 3]]
  [x y])

(let [[_ _ z] [1 2 3]]
  _)

(let [[x y :as coords] [1 2 3 4 5 6]]
  (str "x: " x " y: " y " total dimensions " (count coords)))

(def foo 5)
(resolve 'foo)

(in-ns 'myapp)
String
File/separator
java.io.File/separator

(import '(java.io InputStream File))
(.exists (File. "/tmp"))

(require 'clojure.string)
(clojure.string/split "Something,separated,by,commas" #",")

(require '[clojure.string :as str])
(str/split "Something,separated,by,commas" #",")

(ns examples.exploring
  (:require [clojure.string :as str])
  (:import (java.io File)))

(in-ns 'user)

(def rnd (new java.util.Random))
(.nextInt rnd)
(. rnd nextInt)
(. rnd nextInt 10)
(.nextInt rnd 10)
(. Math PI)
(.PI Math)

(import '(java.util Random Locale)
        '(java.text MessageFormat))

Random
Locale
MessageFormat

(javadoc java.net.URL) ; ???

(defn is-small? [number]
  (if (< number 1000)
    "yes"
    (do
      (println "Saw a big number" number)
      "no")))

(is-small? 2000)
(is-small? 2)

(loop [result [] x 5]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

;(countdown [] 7)

(take 5 (iterate dec 5))
(into [] (take 5 (iterate dec 5)))

(range 6)
(reverse (range 6))
(drop-last (reverse (range 6)))
(into [] (drop-last (reverse (range 6))))

(rest (range 6))
(vec (reverse (rest (range 6))))

(defn indexed [coll]
  (map-indexed vector coll))

(indexed "abcde")

(defn indexed-filter [pred coll]
  (when pred
    (for [[idx elt] (indexed coll) :when (pred elt)] idx)))

(indexed-filter #{\a \b} "abcdbbb")
(indexed-filter #{\a \b} "xyz")
(indexed-filter #{\a \b} "sssa")

(defn index-of-any [pred coll]
  (first (indexed-filter pred coll)))

(index-of-any #{\a \b} "sssabab")
(index-of-any #{\c} "abcdbbb")


























