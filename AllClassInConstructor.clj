(defn make [klass & args]
  (apply klass args))


(defn Point [x y]
  {:x x
   :y y
   :__class_symbol__ 'Point
   :__methods__ {
                 :shift (fn [this xinc yinc]
                          (make Point (+ (:x this) xinc)
                                (+ (:y this) yinc)))}})

(def a-point (make Point 0 0))

(:__methods__ a-point)

(def f (:shift (:__methods__ a-point)))

(f a-point 1 2)

(defn send-to [object message & args]
  (apply (message (:__methods__ object)) object args))

(send-to a-point :shift 1 2)

(defn Point [x y]
  {:_x x
   :_y y
   :__class_symbol__ 'Point
   :__methods__ {
                 :x (fn [this] (:_x this))

                 :y (fn [this] (:_y this))

                 :shift (fn [this dx dy]
                          (make Point
                                (+ (send-to this :x) dx)
                                (+ (send-to this :y) dy)))

                 :add (fn [this other]
                        (send-to this
                                 :shift
                                 (send-to other :x)
                                 (send-to other :y)))}})

(def a-point (make Point 0 0))

(send-to a-point :shift 1 2)

(send-to a-point :add (make Point  1 2))




