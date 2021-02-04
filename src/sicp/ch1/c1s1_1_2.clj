(ns sicp.ch1.c1s1-1-2)

;; Code in the section 1.1.2 on the coin change


(def ^:dynamic denominations [1 5 10 25 50])

(def cnt (atom 1))

(defn get-nth-coin
  "Gets the coin at the given position of the available
   denominations considering the index starts at 1"
  [n]
  (nth denominations (dec n)))


;; This is defined as a var to experiment with memoize
(def cc
  (memoize (fn
             [amount coins]
             (swap! cnt inc)                                ;; Variable to keep track of number of times the function has been called.
             (cond
               (= amount 0) 1                               ;; If the amount is 0 it is expected to be arisen out of the recursion
               (or (< amount 1) (= coins 0)) 0              ;; For negative amount or no coins implies the denominations doesn't match
               :else (+
                       (cc (- amount (get-nth-coin coins)) coins) ;; Account for the
                       (cc amount (dec coins)))))))         ;; Calculate the count after ignoring the first coin.



(defn change-amount
  "Finds the number of ways the given amount an be changed considering all the denominations
   with the optional value to supply the coin-set from which the denominations has to be drwan"
  ([amount]
   (println "Calculation possible exchanges for " amount " with denominations " denominations)
   (cc amount (count denominations)))

  ([amount coin-set]
   (binding [denominations (sort (set coin-set))]
     (change-amount amount))))


(import 'java.io.PrintWriter)
(import 'java.io.OutputStream)

(comment
  (doseq
    [x (range 1 101)]
    (do
      (let [possible-changes (change-amount x)]
        (println x "-" possible-changes)
        (println "number of times cc called" @cnt)
        (def cnt (atom 1))))))