(ns basement.util.json)

(defn link [rel type href & method]
   (let [map
   {:rel rel
   :type type
   :href href}]

     (if-not (= method nil)
       (assoc map :method (first method))
       map
       )
    ))
