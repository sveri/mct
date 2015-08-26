(ns de.sveri.mct.routes.home
  (:require [compojure.core :refer [routes GET POST]]
            [clojure.string :as s]
            [de.sveri.mct.layout :as layout]
            [de.sveri.mct.db.topic :as db-t]
            [de.sveri.mct.db.question :as db-q]
            [de.sveri.mct.db.response :as db-r]
            [de.sveri.mct.service.response :as ser-r]
            [de.sveri.mct.service.user :as ser-u]))

(defn home-page
  ([]
   (layout/render "home/index.html" {:topics (db-t/get-all-topics)}))
  ([topic_id & [answered-questions]]
   (layout/render "home/index.html" {:topics (db-t/get-all-topics)
                                     :question (db-q/get-random-question topic_id answered-questions)
                                     :selected-topic-id topic_id})))

(defn contact-page []
  (layout/render "home/contact.html"))

(defn tos-page []
  (layout/render "home/tos.html"))

(defn cookies-page []
  (layout/render "home/cookies.html"))

(defmulti answer-question (fn [_ _] (s/blank? (ser-u/get-logged-in-username))))

(defmethod answer-question true [{:keys [params]} config]
  (println "not logged in"))

(defmethod answer-question false [{:keys [params]} config]
  (doseq [response (ser-r/params->db config params (ser-u/get-logged-in-username))]
    (db-r/create-response response)))

(defn home-routes [config]
  (routes
    (GET "/contact" [] (contact-page))
    (GET "/tos" [] (tos-page))
    (GET "/cookies" [] (cookies-page))
    (GET "/" [] (home-page))
    (POST "/index/select_topic" [topic_id] (home-page topic_id))
    (POST "/index/answer_question" req (answer-question req config))))
