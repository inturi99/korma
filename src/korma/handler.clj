(ns korma.handler
  (:require [korma.views :as views]
            [compojure.core :as cc]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as ring-json]
            [ring.util.response	:as rr]))

(cc/defroutes app-routes
  (cc/GET "/" [] (views/home-page))
  (cc/GET "/add-user"
          []
          (views/add-user-page))
  (cc/POST "/add-user"
           {params :params}
           (views/add-user-results-page params))
  (cc/GET "/get-all-users"
          []
          (views/all-users-page))
  (cc/POST "/delete-user"
           {params :params}
           (views/delete-user-results-page params))
  (route/resources "/")
  (route/not-found "Not Found"))



;; (def app
;;   (-> app-routes
;;       (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
;;       (ring-json/wrap-json-response)))

(def app
  (handler/site app-routes))
