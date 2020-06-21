(ns sicp.ch1.c1e7v2)
; This performs better than the example c1e7v1.cl
; Probably that in the earlier example, the good-enough?
; condition was never met for huge numbers there by giving a stackoverflow error.

(defn square
  "Calcuates square of a given number"
  [x]
  (* x x))

(defn abs
  "Returns absolute value of a given number"
  [x]
  (if (> x 0)
     x
     (- x)))


(defn good-enough?
  "Checks if the guessed square root is close"
  [guess next-guess]
  (->>
      (- guess next-guess)
      (abs)
      (> 0.00001)))

(defn improve
  "Improves the guessed square root of the given number"
  [guess number]
  (-> (/ number guess)
     (+ guess)
     (/ 2)
     (float)))

(defn sqrt
  "Finds the square root for the given number using
  Newton's approximation method"
  ([number guess]
   (let [improved (improve guess number)]
     (if
       (good-enough? guess improved)
       guess
       (sqrt number (improve guess number)))))
  ([number]
   (sqrt number (/ number 2))))

(sqrt 9000000000)
