(ns basement.db.monger
 (:require [monger.core :as mg]
            [monger.collection :as mc]))

(defn db-connection []
  (let [conn (mg/connect)
        database   (mg/get-db conn "benutzerdb")]
        database))

(def db (delay (db-connection)))

(defn get-db [] @db)

(defn get-user [nick]
  (mc/find-maps (get-db) "users" {:firstname "Martin"}))

