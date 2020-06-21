(ns sicp.ch1.c1s1-1-2)

;; Code in the section 1.1.2 on the coin change


(def ^:dynamic denominations [1 10 25 50])

(defn get-nth-coin
  "Gets the coint at the given position of the available
   denominations considering the index starts at 1"
  [n]
  (nth denominations (dec n)))


;; This is defined as a var to experiment with memoize
(def cc
   (fn
     [amount coins]
     (cond
       (= amount 0) 1 ;; If the amount is 0 it is expected to be arised out of the recursion the recurive
       (or (< amount 1) (= coins 0)) 0 ;; For negative amount or no coins implies the denominations doesn't match
       :else (+
              (cc (- amount (get-nth-coin coins)) coins) ;; Calculate the count for remaining amount after reducing the current coin and for the same set of coins.
              (cc amount (dec coins)))))) ;; Calculate the count after ignoring the first coin.

(defn change-amount
  "Finds the number of ways the given amount an be changed considering all the denominations
   with the optional value to supply the coin-set from which the denominations has to be drwan"
  ([amount]
   (println "Calculation possible exchanges for " amount " with denominations " denominations)
   (cc amount (count denominations)))

  ([amount coin-set]
   (binding [denominations (sort (set coin-set))]
     (change-amount amount))))

(change-amount 11 [1 10 5])
