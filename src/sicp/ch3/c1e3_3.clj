(ns sicp.ch3.c1e3-3)


(defn withdraw [balance amount]
  (if (>= balance amount)
    (- balance amount)
    "Insufficient balance"))

(defn deposit
  [balance amount]
  (+ balance amount))

(defn update-account-balance!
  [account balance]
  (if (number? balance)
    (swap! account #(assoc-in % [:balance] balance))
    balance))

(defn make-account
  [balance secret-password]
  (let [account (atom {:balance balance
                       :secret  secret-password})]
    (defn dispatch
      [arg]
      (cond (= arg :withdraw)
            #(update-account-balance! account (withdraw (:balance @account) %))
            (= arg :deposit)
            #(update-account-balance! account (deposit (:balance @account) %))))))




