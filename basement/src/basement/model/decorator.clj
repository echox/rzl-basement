(ns basement.model.decorator
  (:require [basement.util.json :as json]))

(defn uri-self [user]
  (str "/users/" (user :_id))
  )

(defn add-self [user]
  (json/link "self"
            "application/org.raumzeitlabor.benutzerdb-v1+json"
             (uri-self user)))

(defn add-vcard [user]
  (json/link "self"
            "text/vcard"
             (uri-self user)))

(defn add-delete [user]
  (json/link "delete"
             "application/org.raumzeitlabor.benutzerdb-v1+json"
             (uri-self user)
             "DELETE"))

(defn convert-id [object]
  (assoc object :_id (str (object :_id))))

(defn add-links [user]
  (assoc user
  :link
    [(add-delete user)
     (add-self user)
     (add-vcard user)]))

(defn decorate-user [user]
  (if-not (empty? user)
    (->> user
        (add-links)
        (convert-id)
         )))
