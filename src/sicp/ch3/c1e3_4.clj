(ns sicp.ch3.c1e3-4)


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

(defn call-the-cops
  []
  "calling cops")

(defn make-account
  [balance secret-password]
  (let [account (atom {:balance              balance
                       :secret               secret-password
                       :wrong-password-count 0})]
    (defn dispatch
      [password arg]
      (if (= password secret-password)
        (cond (= arg :withdraw)
              #(update-account-balance! account (withdraw (:balance @account) %))
              (= arg :deposit)
              #(update-account-balance! account (deposit (:balance @account) %)))
        (fn [& args]
          (let [wrong-password-count (:wrong-password-count account)]
            (set! account #(assoc-in % [:wrong-password-count] (inc wrong-password-count)))
            (if
              (> wrong-password-count 7)
              (call-the-cops)))
          "Incorrect password")))))


(def A (make-account 1000 "1"))

((A "1" :withdraw) 100)

(defn -main [args]
  print "test")