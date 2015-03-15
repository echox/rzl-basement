(ns basement.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]

            [basement.api.users]
            [basement.interface.users]
            [basement.interface.index]

            [basement.middleware.prefixHost :as prefix-host]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.util.response :refer [resource-response response]]
            [ring.util.response :as req]
            [ring.middleware.json :as middleware]))

(use 'basement.api.users)

(defn decorate [data]
  (req/header (response data)
    "Content-Type" "application/org.raumzeitlabor.benutzerdb-v1+json; charset=utf-8"))


(defn decorate-vcard [data]
  (req/header (response data)
    "Content-Type" "text/vcard; charset=utf-8"))

(defn transform-vcard [user]
  (str "BEGIN:VCARD\n"
       "VERSION:4.0\n"
       "N:"(:lastname user)";"(:firstname user)";;;\n"
       "EMAIL:"(:email user)"\n"
       ;; add REV
       "END:VCARD"))

(defn negotiator [handlers]
  (fn [request]
    (let [header (clojure.string/split (get (:headers request) "accept") #",")
          selected (first (drop-while (fn [item]
                   (not(contains? handlers item)))
                 header))]

    (if (contains? handlers selected)
      (get handlers selected)
      (if (contains? handlers :default)
        (:default handlers)
        (fn [request])))
    )))

(let [multicontent-user {:default (fn[{{id :id}:params}] (decorate (lookup-user id)))
                         "text/vcard"  (fn[{{id :id}:params}] (decorate-vcard (transform-vcard (lookup-user id))))} ]

(defroutes app-routes
  (GET "/" [] (decorate (basement.interface.index/index)))
  (GET "/users/" [] (decorate (basement.interface.users/users)))
  (GET "/users/add" [] basement.interface.users/users-add)
  (POST "/users/add" [] basement.interface.users/users-add-post)
  (GET "/users/search" [] basement.interface.users/users-search)
  (POST "/users/search"  {params :params} (decorate (basement.interface.users/users-search-post params)))
  (GET "/users/:id" [id] (negotiator multicontent-user))
  (DELETE "/users/:id" [id] (basement.interface.users/delete-user id))
  (GET "/hello" [] "Good day fellow!"))
)

(def basement
  (-> app-routes
    (prefix-host/prefixHost)
    (middleware/wrap-json-response)
    (wrap-defaults api-defaults)
  ))

