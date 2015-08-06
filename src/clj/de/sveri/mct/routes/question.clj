(ns de.sveri.mct.routes.question
  (:require [compojure.core :refer [routes GET POST DELETE]]
            [noir.response :as resp]
            [ring.util.response :refer [response content-type]]
            [taoensso.timbre :as timb]
            [de.sveri.mct.db.question :as db]
            [de.sveri.mct.db.topic :as db-t]
            [de.sveri.mct.db.answer :as db-a]
            [de.sveri.mct.layout :as layout]
            [de.sveri.mct.service.user :as u-service]
            [de.sveri.mct.service.question :as q-service]))

(defn index-page []
  (layout/render "question/index.html" {:questions (db/get-all-questions)
                                        :cols      ["question" "Topic" "rating" "rate_count"]}))

(defn create-page []
  (layout/render "question/create.html" {:create_update "Create"
                                         :topics        (db-t/get-all-topics)
                                         :quest_count   (range 1 11)}))

(defn update-page [id]
  (let [question (db/get-question-by-id id)]
    (layout/render "question/create.html" {:question      question
                                           ;:answers       answers
                                           :create_update "Update"
                                           :topics        (db-t/get-all-topics)
                                           :quest_count   (range (+ 1 (count (:answer question))) 11)})))

(defn delete-page [id]
  (layout/render "question/delete.html" {:id id}))

(defn params->quest [{:keys [topic_id question]}]
  {:user_email (u-service/get-logged-in-username) :topic_id topic_id
   :question question :rating 0 :rate_count 0})



(defn create [{:keys [params] :as req}]
  ;(clojure.pprint/pprint req)
  (try
    (let [quest-map (params->quest params)
          q-id (db/create-question quest-map)
          answer-maps (q-service/params->answers q-id params 10)]
      (doseq [answer answer-maps]
        (db-a/create-answer answer)))
    (catch Exception e (timb/error e "Something went wrong creating question.")
                       (layout/flash-result (str "An error occured.") "alert-danger")))
  (resp/redirect "/question"))

(defn update [id user_id topic_id question rating rate_count]
  (try
    (db/update-question id {:user_id user_id :topic_id topic_id :question question :rating rating :rate_count rate_count})
    (catch Exception e (timb/error e (str "Something went wrong updating: " id))
                       (layout/flash-result (str "An error occured.") "alert-danger")))
  (resp/redirect "/question"))

(defn delete [id delete_cancel]
  (when (= "Delete" delete_cancel)
    (try
      (db/delete-question id)
      (catch Exception e (timb/error e (str "Something went wrong deleting: " id))
                         (layout/flash-result (str "An error occured.") "alert-danger"))))
  (resp/redirect "/question"))

(defn question-routes []
  (routes
    (GET "/question" [] (index-page))
    (GET "/question/create" [] (create-page))
    (GET "/question/:id" [id] (update-page id))
    (POST "/question/create" req (create req))
    ;(POST "/question/create" req (create topic_id question rating rate_count ))
    (GET "/question/delete/:id" [id] (delete-page id))
    (POST "/question/delete" [id delete_cancel] (delete id delete_cancel))
    (POST "/question/update" [id user_id topic_id question rating rate_count] (update id user_id topic_id question rating rate_count))))
