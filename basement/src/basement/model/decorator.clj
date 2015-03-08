(ns basement.model.decorator
  (:require [basement.util.json :as json]))

(defn add-self [user]
  (assoc user :link (json/link "self"
                          "application/org.raumzeitlabor.benutzerdb-v1+json"
                          (str "users/" (user :_id)))))

(defn convert-id [object]
  (assoc object :_id (str (object :_id))))

(defn decorate-user [user]
  (if-not (empty? user)
    (->> user
        (add-self)
        (convert-id))))
