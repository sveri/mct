(ns de.sveri.mct.routes.topic
  (:require [compojure.core :refer [routes GET POST DELETE]]
            [noir.response :as resp]
            [ring.util.response :refer [response content-type]]
            [taoensso.timbre :as timb]
            [de.sveri.mct.db.topic :as db]
            [de.sveri.mct.layout :as layout]
            [de.sveri.mct.service.topic :as t-ser]))



(defn index-page []
  (let [topics (db/get-all-topics)
        topics' (doall (map #(t-ser/add-parent-topic-name % topics) topics))]
    (layout/render "topic/index.html" {:topics topics' :cols ["name" "Parent Topic"]})))

(defn create-page []
  (layout/render "topic/create.html" {:create_update "Create" :topics (db/get-all-topics)}))

(defn update-page [id]
  (let [topic (db/get-topic-by-id id)]
    (layout/render "topic/create.html" {:topic topic :create_update "Update" :topics (db/get-all-topics)})))

(defn delete-page [id]
  (layout/render "topic/delete.html" {:id id}))

(defn create [name topic_id]
  (try
    (db/create-topic {:name name :topic_id topic_id })
    (catch Exception e (timb/error e "Something went wrong creating topic.")
                       (layout/flash-result (str "An error occured.") "alert-danger")))
  (resp/redirect "/topic"))

(defn update [id topic_id name ]
  (try
    (db/update-topic id {:topic_id topic_id :name name  })
    (catch Exception e (timb/error e (str "Something went wrong updating: " id))
                       (layout/flash-result (str "An error occured.") "alert-danger")))
  (resp/redirect "/topic"))

(defn delete [id delete_cancel]
  (when (= "Delete" delete_cancel)
    (try
     (db/delete-topic id)
     (catch Exception e (timb/error e (str "Something went wrong deleting: " id))
                        (layout/flash-result (str "An error occured.") "alert-danger"))))
  (resp/redirect "/topic"))

(defn topic-routes []
  (routes
    (GET "/topic" [] (index-page))
    (GET "/topic/create" [] (create-page))
    (GET "/topic/:id" [id] (update-page id))
    (POST "/topic/create" [name topic_id] (create name topic_id))
    (GET "/topic/delete/:id" [id] (delete-page id))
    (POST "/topic/delete" [id delete_cancel] (delete id delete_cancel))
    (POST "/topic/update" [id topic_id name ] (update id topic_id name ))))
