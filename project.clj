(defproject digitalcomposer "0.1.0-SNAPSHOT"
  :description "Music composition AI"
  :url ""
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [hiccup "1.0.5"]
                 [compojure "1.4.0"]
                 [org.clojure/data.json "0.2.6"]
                 [org.encog/encog-core "3.2.0"]
                 [enclog "0.6.3"]]
  :main digitalcomposer.core)
