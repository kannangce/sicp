(ns sicp.ch1.c1e20)

;; Exercise 1.20 difference b/w normal and applicative order

(defn mod1
  [a b]
  (println "calling mod for " a b)
  (mod a b))

(defn gcd
  [a b]
  (if (= b 0)
    a
    (gcd b (mod1 a b))))


(defmacro gcd1
  [a b]
  `(if (= ~b 0)
    ~a
    (gcd1 ~b ~(mod1 a b))))

(gcd1 206 40)