(ns de.sveri.mct.routes.home
  (:require [compojure.core :refer [defroutes GET POST]]
            [de.sveri.mct.layout :as layout]
            [de.sveri.mct.db.topic :as db-t]
            [de.sveri.mct.db.question :as db-q]))

(defn home-page
  ([]
   (layout/render "home/index.html" {:topics (db-t/get-all-topics)}))
  ([topic_id]
   (layout/render "home/index.html" {:topics (db-t/get-all-topics)
                                     :question (db-q/get-random-question topic_id)
                                     :selected-topic-id topic_id})))

(defn contact-page []
  (layout/render "home/contact.html"))

(defn tos-page []
  (layout/render "home/tos.html"))

(defn cookies-page []
  (layout/render "home/cookies.html"))

(defroutes home-routes
           (GET "/contact" [] (contact-page))
           (GET "/tos" [] (tos-page))
           (GET "/cookies" [] (cookies-page))
           (GET "/" [] (home-page))
           (POST "/index/select_topic" [topic_id] (home-page topic_id)))
