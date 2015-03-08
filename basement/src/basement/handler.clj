(ns basement.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]

            [basement.users]
            [basement.api.users]

            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :refer [resource-response response]]
            [ring.util.response :as req]
            [ring.middleware.json :as middleware]))

(use 'basement.api.users)

(defn testout [r]
	"bar 2"
)

(defn decorate [data]
  (req/header (response data)
    "Content-Type" "application/org.raumzeitlabor.benutzerdb-v1+json; charset=utf-8"
  )
)



(defroutes app-routes
  (GET "/" [] "Hello World")
	(GET "/test" [] testout)
  (GET "/users" [] (decorate (lookup-user "foo")))
  (GET "/users/:id" [id] (decorate (lookup-user id)))
	)



(def basement
  (-> app-routes
    (middleware/wrap-json-response)
    (wrap-defaults site-defaults)

  )
)
