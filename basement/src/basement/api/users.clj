(ns basement.api.users
  (:require [basement.db.monger :as db]
            [basement.model.decorator]))

(defn user [nick firstname lastname email contribution-receipt]
  {:nick nick
   :firstname firstname
   :lastname lastname
   :email email
   :contribution-receipt contribution-receipt})

(defn lookup-user [id]
    (db/decorate-user (db/get-user id)))
