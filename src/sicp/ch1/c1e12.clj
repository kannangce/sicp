(ns sicp.ch1.c1e12)

;; Exercise 1.12 generate and print a pascal triangle recursively.

(defn next-elem
  "Gets the next row in the pascal triangle for the given current row"
  [row]
  (mapv + 
        (conj row 0) ;; Add 0 at end
        (into [0] row))) ;; Add 0 to the front

(defn build-triangle
  "Builds a pascal triangle staring from the given row and upto the given limit"
  [limit]
  (loop [triangle [[1]] limit limit]
    (if (>= (count triangle) limit)
      triangle
      (recur (conj triangle (next-elem (last triangle))) limit))))

(defn print-row
  "Prints the given row  in a triangle with the maximum length as given total-row-length"
  [row total-row-length]
  (let [curr-row-len (count row)
        start-at (- total-row-length curr-row-len)
        separator "  "]
    (dotimes [_ start-at] (print separator))
    (doseq [n row] (print (str n separator)))
    (println "")))

(defn print-triangle
  "Prints the given triangle in  a formatted way"
  [triangle]
  (let [total-size (dec (* 2 (count triangle)))]
    (doseq [i triangle] (print-row  i total-size))))

(defn pascal-triangle
  "Prints the pascal triangle for the given number of lines"
  [n]
  (-> (build-triangle n)
      (print-triangle)))

(pascal-triangle 50)