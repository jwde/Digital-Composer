(ns digitalcomposer.core
  (:require [ring.adapter.jetty :as jetty]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello world!"})

(defn -main []
  (jetty/run-jetty handler {:port 3000}))
