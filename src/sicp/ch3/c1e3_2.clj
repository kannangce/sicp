(ns sicp.ch3.c1e3-2)


(defn make-monitored
  [to-monitor]
  (let [sum (atom 0)]
    (fn [& args]
      (if (= ['how-many-calls?] args)
        @sum
        (do (swap! sum inc)
            (apply to-monitor args))))))