(ns digitalcomposer.views.layout
  (:use [hiccup.page :only (html5 include-css include-js)]))

(defn page [title & content]
  (html5 {:lang "en"}
    [:head
      [:title title]
      (include-css "css/player.css")]
    [:body [:div content]
      (include-js "js/player.js")]))
