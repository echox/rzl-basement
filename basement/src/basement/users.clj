(ns basement.users)

(defn user [firstname lastname]
  {:firstname firstname :lastname lastname})


(defn userlist []
  (user "test" "user"))
