(ns de.sveri.mct.db.question
  (:require [korma.core :refer [defentity many-to-many select where limit insert values update delete set-fields with
                                fields]]
            [de.sveri.mct.db.entities :refer [question topic answer user]]
            [de.sveri.mct.service.user :as u-ser]
            [clojure.core.typed :as t]
            [de.sveri.mct.types :as ty])
  (:import (java.util UUID)))

(def topic-fields [:topic.name :topic_name])

(t/ann get-all-questions [ty/user -> t/Any])
(defn get-all-questions [user]
  (select question
          (with topic (fields topic-fields))
          (with answer)
          (where (if (u-ser/is-admin? user) {} {:user_email (:email user)}))))

(defn user-or-admin [user-id is-admin?]
  (cond
    is-admin? {}
    (= user-id nil) {}
    :else {:user_email user-id}))

(defn get-question-by-id
  ([id] (get-question-by-id id nil))
  ([id user] (get-question-by-id id user false))
  ([id user is-admin?]
    (first (select question
                   (where (merge {:id id} (user-or-admin user is-admin?)))
                   (with topic (fields topic-fields))
                   (with answer)
                   (limit 1)))))

(defn create-question [data]
  (let [id (if (:id data) (:id data) (str (UUID/randomUUID)))]
    (insert question (values (merge data {:id id})))
    id))

(defn update-question [id fields]
  (update question (set-fields fields) (where {:id id})))

(defn delete-question [id] (delete question (where {:id id})))

(defn get-random-question [topic_id & [answered-questions]]
  (let [qs (select question
                   (where (if (= "Random" topic_id) {} {:topic_id topic_id}))
                   (with answer))
        new-qs (filter #(not (some #{(:id %)} answered-questions)) qs)
        n (rand-int (count new-qs))]
    (nth new-qs n)))