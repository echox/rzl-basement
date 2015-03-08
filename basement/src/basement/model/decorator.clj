(ns basement.model.decorator
  (:require [basement.api.users]))

(defn link [rel type href]
  {:rel rel
   :type type
   :href href})

(defn add-self [user]
  (assoc user :link (link "self"
                          "application/org.raumzeitlabor.benutzerdb-v1+json"
                          (str "users/" (user :_id)))))

(defn convert-id [object]
  (assoc object :_id (str (object :_id))))

(defn decorate-user [user]
  (if-not (empty? user)
    (->> user
        (add-self)
        (convert-id))))
