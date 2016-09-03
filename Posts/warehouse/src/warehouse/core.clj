(ns warehouse.core
  (:require
    [clojure.core.async :as async]))

(def warehouse-capacity 10)

(def warehouse-channel (async/chan warehouse-capacity))

(def banana-channel (async/chan (async/sliding-buffer 3)))

(def stock-items [:shirt
                  :pants
                  :socks
                  :shoes])

(defn- generate-random-items []
  (repeatedly warehouse-capacity #(rand-nth stock-items)))

(defn load-items-into-channel [items channel]
  (map #(async/>!! channel %) items))

(defn make-payment-channel []
  (let [payments (async/chan)]
    (async/go
      (while true
        (let [in (async/<! payments)]
          (if (number? in)
            (let [[item _] (async/alts!! [warehouse-channel  banana-channel])]
              (println item))
            (println "We only accept numeric values! No Number, No Clothes!")))))
    payments))

(defn -main [& args]
  (load-items-into-channel
    (generate-random-items)
    warehouse-channel))
