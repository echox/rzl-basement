(ns basement.interface.users
  (:require [basement.util.json :as json]
            [basement.api.users :as users]
            [hiccup.core]
            [hiccup.form]
            [ring.util.response :as ring]))

(use 'hiccup.core)
(use 'hiccup.form)

(defn users []
  {:usermanagement { :link [(json/link "add" "text/html" "/users/add")
                           (json/link "search" "text/html" "/users/search") ]}})

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
  ]))

(defn users-add-post [{params :params}]
  (let [id (str ((users/create-user params) :_id))]
      (ring/redirect (str "/users/" id))))

(defn delete-user [id]
  (users/delete-user id)
)
