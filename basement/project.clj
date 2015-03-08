(defproject basement "0.1.0-SNAPSHOT"
  :description "new RZL benutzerdb core webservice"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-core "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [org.clojure/data.json "0.2.6"]
                 [ring/ring-json "0.3.1"]
                 [ejdb "1.0.2"]
                 ]
;;  :repositories {"local" "file:maven_repository"}
;;  :native-path "/path/to/ejdb.so"
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler basement.handler/basement}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [ring/ring-jetty-adapter "1.3.2"]]}})
