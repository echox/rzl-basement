(ns basement.util.json)

(defn link [rel type href]
  {:rel rel
   :type type
   :href href})
