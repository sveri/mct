(ns joplin.seeds
  (:require [de.sveri.mct.db.question :as q]))

(def user "local@local.de")

;(def topics [{:id "4a5c29d8-05cf-4799-9959-779965732eed" :name "Gesundheit"}
;             {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb" :name "Gesundheit" :topic_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb"}])

(def questions [{:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb" :user_email user :topic_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb"
                 :question "Question 3"}
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba6cb" :user_email user :topic_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb"
                 :question "Question 4" }
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba7cb" :user_email user :topic_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb"
                 :question "Question 5"}
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba8cb" :user_email user :topic_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb"
                 :question "Question 6"}
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba9cb" :user_email user :topic_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb"
                 :question "Question 7"}
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb" :user_email user :topic_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb"
                 :question "Question 8"}
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba3cb" :user_email user :topic_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb"
                 :question "Question 9"}
                ]
  )

(defn run [target & args]
  (dorun (map q/create-question questions))
  )