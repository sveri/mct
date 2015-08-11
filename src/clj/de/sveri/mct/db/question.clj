(ns de.sveri.mct.db.question
  (:require [korma.core :refer [defentity many-to-many select where limit insert values update delete set-fields with
                                fields]]
            [de.sveri.mct.db.entities :refer [question topic answer user]]
            [de.sveri.mct.service.user :as u-ser])
  (:import (java.util UUID)))

(def topic-fields [:topic.name :topic_name])

(defn get-all-questions []
  (select question
          (with topic (fields topic-fields))
          (with answer)
          (where (if (u-ser/is-admin?) {} {:user_email (u-ser/get-logged-in-username)}))))

(defn get-question-by-id [id] (first (select question (where {:id id})
                                             (with topic (fields topic-fields))
                                             (with answer)
                                             (limit 1))))

(defn create-question [data]
  (let [id (if (:id data) (:id data) (str (UUID/randomUUID)))]
    (insert question (values (merge data {:id id})))
    id))

(defn update-question [id fields]
  (update question (set-fields fields) (where {:id id})))

(defn delete-question [id] (delete question (where {:id id})))

(defn get-random-question [topic_id]
  (let [qs (select question
                   (where (if (= "Random" topic_id) {} {:topic_id topic_id}))
                   (with answer))
        n (rand-int (count qs))]
    (nth qs n)))