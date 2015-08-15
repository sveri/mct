(ns joplin.seeds
  (:require [de.sveri.mct.db.question :as q]
            [de.sveri.mct.db.answer :as a]
            [de.sveri.mct.db.topic :as t]
            [korma.db :refer [defdb]]))

(def user "local@local.de")
(def admin "admin@localhost.de")

(def topics [{:id "1a5c29d8-05cf-4799-9959-779965732eed" :name "Computer Science"}
             {:id "2a5c29d8-05cf-4799-9959-779965732eed" :name "Languages" :topic_id "1a5c29d8-05cf-4799-9959-779965732eed"}
             {:id "3a5c29d8-05cf-4799-9959-779965732eed" :name "Clojure" :topic_id "2a5c29d8-05cf-4799-9959-779965732eed"}])

(def questions [{:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb" :user_email admin :topic_id "3a5c29d8-05cf-4799-9959-779965732eed"
                 :difficulty 1 :question "What is Clojure?"}
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba6cb" :user_email admin :topic_id "3a5c29d8-05cf-4799-9959-779965732eed"
                 :question "What is leiningen?" }
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba7cb" :user_email admin :topic_id "3a5c29d8-05cf-4799-9959-779965732eed"
                 :difficulty 1 :question "Whats the ending of clojure source files?"}
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba8cb" :user_email admin :topic_id "3a5c29d8-05cf-4799-9959-779965732eed"
                 :difficulty 1 :question "What stands \"REPL\" for?"}
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba9cb" :user_email admin :topic_id "3a5c29d8-05cf-4799-9959-779965732eed"
                 :difficulty 1 :question "Which paradigm follows Clojure?"}
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb" :user_email admin :topic_id "3a5c29d8-05cf-4799-9959-779965732eed"
                 :difficulty 1 :question "Is Clojure statically or dynamically typed?"}
                {:id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba3cb" :user_email admin :topic_id "3a5c29d8-05cf-4799-9959-779965732eed"
                 :difficulty 1 :question "Who is the creator of Clojure?"}]
  )

(def answers [{:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb" :answer "Clojure the Language." :correct 1}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb" :answer "Clojure the Compiler." :correct 1}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb" :answer "Clojure is a hosted language." :correct 1}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb" :answer "Clojure the Framework." :correct 0}

              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba6cb" :answer "A build tool." :correct 1}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba6cb" :answer "A JDBC library." :correct 0}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba6cb" :answer "A Java to Clojure converter." :correct 0}

              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba7cb" :answer ".clc" :correct 0}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba7cb" :answer ".cla" :correct 0}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba7cb" :answer ".clj" :correct 1}

              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba8cb" :answer "Read Easy Print Loop" :correct 0}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba8cb" :answer "Ready Eval Print Loop" :correct 0}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba8cb" :answer "Read Eval Print Loop" :correct 1}

              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba9cb" :answer "Functional." :correct 1}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba9cb" :answer "Object Oriented." :correct 0}

              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb" :answer "Statically." :correct 0}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb" :answer "Dynamically." :correct 1}

              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba3cb" :answer "Stuart Halloway." :correct 0}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba3cb" :answer "James Gosling." :correct 0}
              {:question_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba3cb" :answer "Rich Hickey." :correct 1}])


(defn clojure-quest [target & args]
  (defdb db (get-in target [:db :url]))
  (dorun (map t/create-topic topics))
  (dorun (map q/create-question questions))
  (dorun (map a/create-answer answers)))