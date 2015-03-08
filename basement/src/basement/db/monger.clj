(ns basement.db.monger
 (:require [monger.core :as mg]
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

  (def obj (if-not (contains? user :_id)
    (assoc user :_id (generate-id))
             user))

  (mc/update (get-db) "users" obj {:upsert true})
  obj
)
