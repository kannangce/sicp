(ns sicp.ch3.c1e3-1)

(defn make-accumulator-v1
  [initial]
  (fn [accumulate]
    (let [sum (+ initial accumulate)]
      (set! initial sum)
      sum)))

(defn make-accumulator-v2
  [initial]
  (with-local-vars [sum initial]
    (fn [accumulate]
      (set! sum #(+ % accumulate))
      initial)))

; A mentioned in the set! doc, it won't work for function and local vars.
; https://clojuredocs.org/clojure.core/set!

(defn make-accumulator
  [initial]
  (let [sum (atom initial)]
    (fn [accumulate]
      (swap! sum #(+ % accumulate))
      @sum)))