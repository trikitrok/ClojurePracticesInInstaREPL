(assoc {:a 1} :a 2222)

(merge {:a 1 :b 2 :c 3} {:a 111 :b 222 :d 4} {:b "two"})

(defn Point [x y]
  {:x x :y y})

(:x (Point 1 2))
(:y (Point 1 2))

(defn x [this]
  (:x this))

(defn y [this]
  (get this :y))

(x (Point 1 2))
(y (Point 1 2))


(def x :x)
(def y :y)

(x (Point 1 2))
(y (Point 1 2))

(defn Point [x y]
  {:x x
   :y y
   :__class_symbol__ 'Point})

(Point 2 0)

(def class-of :__class_symbol__)

(class-of (Point 3 4))

(defn shift [this dx dy]
  (Point (+ (x this) dx)
         (+ (y this) dy)))

(shift (Point 0 0) 1 2)

(defn add-1 [this other]
  (Point (+ (x this) (x other))
         (+ (y this) (y other))))

(add-1 (Point 0 0) (Point 1 2))

(defn add-2 [this other]
  (shift this (x other) (y other)))

(add-2 (Point 0 0) (Point 1 2))

(defn make [klass & args]
  (apply klass args))

(make Point 1 2)

(defn Triangle [p1 p2 p3]
  {:point-1 p1
   :point-2 p2
   :point-3 p3
   :__class_symbol__ 'Triangle})

(make Triangle
      (make Point 1 2)
      (make Point 1 3)
      (make Point 3 1))

;; (defn equal-triangles? [& triangles]
;;   (apply = triangles))

(def equal-triangles? =)
;; In Clojure, there's no distinction between "pointer equality" and
;; "content equality". (If you think about it, the substitution rule
;; for function evaluation demands that.)

(def triangle (make Triangle
      (make Point 1 2)
      (make Point 1 3)
      (make Point 3 1)))

(def equal-triangle (make Triangle
      (make Point 1 2)
      (make Point 1 3)
      (make Point 3 1)))

(def different-triangle (make Triangle
      (make Point 0 2)
      (make Point 5 3)
      (make Point 3 0)))

(equal-triangles? triangle triangle)
(equal-triangles? triangle equal-triangle)
(equal-triangles? triangle different-triangle)

(equal-triangles? triangle different-triangle equal-triangle)
(equal-triangles? triangle triangle equal-triangle)

(defn valid-triangle? [& points]
  (and
   (= 3 (count points))
   (= points (distinct points))))

(valid-triangle?
 (make Point 1 2)
 (make Point 1 3)
 (make Point 3 1))

(valid-triangle?
 (make Point 1 2)
 (make Point 1 3)
 (make Point 1 2))










