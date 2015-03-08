(ns basement.api.users)

(defn user [nick firstname lastname email contribution-receipt]
  {:nick nick
   :fistname firstname
   :lastname lastname
   :email email
   :contribution-receipt contribution-receipt})

(defn link [rel type href]
  {:rel rel
   :type type
   :href href})

(defn add-self [user]
  (assoc user :link (link "self"
                          "application/org.raumzeitlabor.benutzerdb-v1+json"
                          (str "users/" (user :nick)))))

(defn lookup-user [userid]
    (add-self (user userid "Martin" "Mustermann" "foo@example.org" false)))
