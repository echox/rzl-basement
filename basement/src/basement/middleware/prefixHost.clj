(ns basement.middleware.prefixHost)

(defn get-hoststring [request]
    (let [headers (:headers request)]
      ; since the scheme is just another key, skip the ':'
      (str (subs (str (:scheme request)) 1) "://" (get headers "host"))))

(defn prefix-href [map host]
  (clojure.walk/prewalk
   (fn [m]
     (if (and (map? m) (:href m))
       (if (.startsWith (:href m) "/")
         (update-in m [:href ] #(str host %))
        m)
      m))
  map))

(defn prefix [request response]
  (let [body (:body response)]
    (if (map? body)
      (assoc-in response [:body] (prefix-href (:body response) (get-hoststring request)))
      response
    )))

(defn prefixHost
  "Middleware that prefixes json href tags starting by / with the host and schema from the request"
  [handler]
  (fn [request]
    (prefix request (handler request))))
