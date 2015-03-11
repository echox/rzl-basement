(ns basement.interface.index
  (:require [basement.util.json :as json]))

(defn index []
  {:benutzerdb { :link (json/link "usermanagement"
                                  "application/org.raumzeitlabor.benutzerdb-v1+json"
                                  "/users/") } })
