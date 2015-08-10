(ns joplin.seeds
  (:require [de.sveri.mct.db.question :as q]
            [de.sveri.mct.db.answer :as a]))

(def user "local@local.de")

(def topics [{:id "1a5c29d8-05cf-4799-9959-779965732eed" :name "Computer Science"}
             {:id "2a5c29d8-05cf-4799-9959-779965732eed" :name "Languages" :topic_id "2a5c29d8-05cf-4799-9959-779965732eed"}
             {:id "3a5c29d8-05cf-4799-9959-779965732eed" :name "Clojure" :topic_id "1a5c29d8-05cf-4799-9959-779965732eed"}])

(def questions [{:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb" :user_email user :topic_id "3a5c29d8-05cf-4799-9959-779965732eed"
                 :question "What is Clojure?"}
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

(def answers [{:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb" :answer "Clojure the Language" :correct 1}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb" :answer "Clojure the Compiler" :correct 1}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb" :answer "Clojure the Framework" :correct 0}])

(defn clojure [target & args]
  (dorun (map q/create-question questions))
  (dorun (map a/create-answer answers))

  )