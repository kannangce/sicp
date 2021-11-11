(ns sicp.ch1.c1e12)

;; Exercise 1.15 sin(x)=3 sin(x/3) - 4(sin(x/3))^3.

(def counts (atom {}))

(defn cube [x]
  (* x x x))

(defn p
  [x]
  (do
    (swap! counts update-in [:p] #(if (nil? %) 0 (inc %)))
    (- (* 3 x)
       (* 4 (cube x)))))

(defn sine
  [angle]
  (if (not (> (Math/abs angle) 0.1))
    angle
    (p (sine (/ angle 3.0)))))

(defn deg->radian
  [angle]
  (* (/ Math/PI 180) angle))

(sine (deg->radian 90))

(sine 12.15)
(deref counts)

;; P is getting called 4 times.

