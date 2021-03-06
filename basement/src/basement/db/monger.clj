(ns basement.db.monger
 (:require [monger.core :as mg]
           [monger.operators :refer :all]
            [monger.collection :as mc]))

(defn db-connection []
  (let [conn (mg/connect)
        database   (mg/get-db conn "benutzerdb")]
        database))

(def db (delay (db-connection)))

(defn get-db [] @db)

(defn generate-id [] (org.bson.types.ObjectId.))
(defn str-to-id [str] (org.bson.types.ObjectId. str))

(defn get-user-by-key [key value]
  (mc/find-one-as-map (get-db) "users" {key value}))

(defn get-user [id]
  (mc/find-one-as-map (get-db) "users" {:_id (str-to-id id)}))


(defn store-user [user]
  (def user-with-id (if-not (contains? user :_id)
              (assoc user :_id (generate-id))
               user))
  (mc/insert (get-db) "users" user-with-id)
  user-with-id)

(defn delete-user [id]
  (str (mc/remove-by-id (get-db) "users" (str-to-id id))))

(defn remove-empty [map]
  (into {} (remove (comp (fn [e] (= "" e)) second) map)))

(defn search-user [attributes]
(let [params (remove-empty attributes)
      querymap (reduce (fn [in [k v]] (assoc in k {$regex v})) {} params)]
  (mc/find-maps (get-db) "users" querymap)))
