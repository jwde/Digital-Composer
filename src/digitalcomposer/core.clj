(ns digitalcomposer.core
  (:require [ring.adapter.jetty :as jetty]
            [digitalcomposer.views.layout :as layout]
            [digitalcomposer.views.player :as player]
            [compojure.core :refer [defroutes GET ANY]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [clojure.data.json :as json]
            [digitalcomposer.generator.song :as song]))

(defroutes routes
  (GET "/" [] (layout/page "Digital Composer" (player/player)))
  (GET "/song" [] (json/write-str (song/getSong)))
  (route/resources "/"))

(def application (handler/site routes))

(defn -main []
  (jetty/run-jetty application {:port 3000 :join? false}))
