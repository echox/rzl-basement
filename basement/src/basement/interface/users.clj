(ns basement.interface.users
  (:require [basement.util.json :as json]
            [hiccup.core]
            [hiccup.form]))

(use 'hiccup.core)
(use 'hiccup.form)
(use 'ring.util.anti-forgery)

(defn users []
  {:usermanagement { :link (json/link "add" "text/html" "/users/add") } })

(defn users-add [r]
(html
   [:form {:action "/users/add" :method "POST" :name "user-add"}

        (label {:class "control-label"} "nickname" "Nickname")
        (text-field "nickname")

       (label {:class "control-label"} "email" "Email")
       (email-field {:placeholder "foo@example.org" } "email")

        (label {:class "control-label"} "firstname" "Firstname")
        (text-field "firstname")

        (label {:class "control-label"} "lastname" "Lastname")
        (text-field "lastname")

      (label "contribution-receipt" "contribution-receipt?")
      (check-box "contribution-receipt" false)

      (submit-button "add")
    (anti-forgery-field)

  ]))
