(ns basement.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]

            [basement.users]

            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :refer [resource-response response]]
            [ring.util.response :as req]
            [ring.middleware.json :as middleware]))


(defn testout [r]
	"bar 2"
)

(defroutes app-routes
  (GET "/" [] "Hello World")
	(GET "/test" [] testout)
  (GET "/users" [] (decorate basement.users/userlist))
	)


(defn decorate [func]
  (req/header (response (func))
    "Content-Type" "application/org.raumzeitlabor.benutzerdb-v1+json; charset=utf-8"
  )
)


(def basement
  (-> app-routes
    (middleware/wrap-json-response)
    (wrap-defaults site-defaults)

  )
)
