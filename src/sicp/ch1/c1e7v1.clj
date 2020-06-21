(ns sicp.ch1.c1e7v1)
;; Solution for 1.7 basic version

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
  [guess number]
  (->>
      (- (square guess) number)
      (abs)
      (> 0.001)))

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
   (if
    (good-enough? guess number)
    guess
    (sqrt number (improve guess number))))
  ([number]
   (sqrt number (/ number 2))))

(sqrt 90000)
; Above gives StackOverflowError
