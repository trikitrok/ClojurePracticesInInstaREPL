(defn make [class & args]
  (let
    [seeded {:__class_symbol__ (:__own_symbol__ class)}

     constructor (:add_instance_values
                  (:__instance_methods__ class))]

    (apply constructor seeded args)))

(defn send-to [instance message & args]
  (let
    [class (eval (:__class_symbol__ instance))
     method (message (:__instance_methods__ class))]
    (apply method instance args)))

(def Point
  {
   :__own_symbol__ 'Point

   :__instance_methods__
   {
    :__class_symbol__ 'Point

    :class-name :__class_symbol__

    :class
    (fn [this]
      (eval (:__class_symbol__ this)))

    :add_instance_values
    (fn [this x y]
      (assoc this :x x :y y))

    :shift
    (fn [this dx dy]
      (make Point
            (+ (:x this) dx)
            (+ (:y this) dy)))

    :add
    (fn [this other]
      (send-to this
               :shift
               (:x other)
               (:y other)))
    }
   })

(def a-point (make Point 0 0))

(eval (:__class_symbol__ a-point))

(:__instance_methods__
          (eval (:__class_symbol__ a-point)))

(:shift (:__instance_methods__
          (eval (:__class_symbol__ a-point))))

(def shift (:shift (:__instance_methods__
          (eval (:__class_symbol__ a-point)))))

(shift a-point 1 2)

(send-to a-point :shift 1 2)

(send-to a-point :add (make Point  1 2))

(defn apply-message-to [class instance message args]
  (let
    [method (message (:__instance_methods__ class))]

    (apply method instance args)))

(defn send-to [instance message & args]
  (let
    [class (eval (:__class_symbol__ instance))]
    (apply-message-to class instance message args)))

(defn make [class & args]
  (let
    [seeded {:__class_symbol__ (:__own_symbol__ class)}]
    (apply-message-to class seeded :add_instance_values args)))

(def a-point (make Point 0 0))

(send-to a-point :shift 1 2)

(send-to a-point :add (make Point  1 2))

(send-to a-point :class-name)

(send-to a-point :class)

(eval (:__class_symbol__ a-point))

(apply-message-to (eval (:__class_symbol__ a-point)) a-point :class-name [])

(:class-name (:__instance_methods__ (eval (:__class_symbol__ a-point))))

(apply (:class-name (:__instance_methods__ (eval (:__class_symbol__ a-point)))) a-point [])


(def Point
  {
   :__own_symbol__ 'Point

   :__instance_methods__
   {
    :__class_symbol__ 'Point

    :class-name :__class_symbol__

    :class
    (fn [this]
      (eval (:__class_symbol__ this)))

    :add_instance_values
    (fn [this x y]
      (assoc this :x x :y y))

    :shift
    (fn [this dx dy]
      (make Point
            (+ (:x this) dx)
            (+ (:y this) dy)))

    :add
    (fn [this other]
      (send-to this
               :shift
               (:x other)
               (:y other)))

    :origin
    (fn [this]
      (make Point 0 0))
    }
   })

(send-to a-point :origin)

(:not-there {:a 1 :b 2})

(and true nil)
(and true false)
(or nil 3)

(defn apply-message-to [class instance message args]
  (let
    [method (or (message (:__instance_methods__ class)) message)]

   (apply method instance args)))

(defn send-to [instance message & args]
  (let
    [class (eval (:__class_symbol__ instance))]
    (apply-message-to class instance message args)))


(send-to a-point :x)
(send-to a-point :y)

(send-to a-point :origin)

(send-to a-point :shift 1 2)

(send-to a-point :add (make Point  1 2))

(send-to a-point :class-name)

(send-to a-point :class)

(send-to a-point :unknown-message)