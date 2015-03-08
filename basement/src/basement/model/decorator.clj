(ns basement.model.decorator
  (:require [basement.api.users]))

(defn link [rel type href]
  {:rel rel
   :type type
   :href href})

(defn add-self [user]
  (assoc user :link (link "self"
                          "application/org.raumzeitlabor.benutzerdb-v1+json"
                          (str "users/" (user :nick)))))

(defn decorate-user [user]
  (add-self user))
