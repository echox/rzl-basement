(ns basement.api.users
  (:require [basement.db.monger :as db]
            [basement.model.decorator :as decorator]))

(defn user [nick firstname lastname email contribution-receipt]
  {:nickname nick
   :firstname firstname
   :lastname lastname
   :email email
   :contribution-receipt contribution-receipt})

(defn lookup-user [id]
    (decorator/decorate-user (db/get-user id)))

(defn create-user [userdata]
  (let [nickname (userdata :nickname)
        firstname (userdata :firstname)
        lastname (userdata :lastname)
        email (userdata :email)
        contribution-receipt (userdata :contribution-receipt)]


      (db/store-user (user nickname firstname lastname email contribution-receipt))
    )

  )

(defn delete-user [id]
  (db/delete-user id)
)
