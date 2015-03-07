(ns basement.api.users)

(defn user [nick firstname lastname email contribution-receipt]
  {:nick nick
   :fistname firstname
   :lastname lastname
   :email email
   :contribution-receipt contribution-receipt})

(defn lookup-user [userid]
    (user "foobar" "Martin" "Mustermann" "foo@example.org" false)
  )
