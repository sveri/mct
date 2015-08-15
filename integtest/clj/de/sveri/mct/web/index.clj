(ns de.sveri.mct.web.index
  (:require [clojure.test :refer :all]
            [clj-webdriver.taxi :refer :all]
            [de.sveri.mct.web.setup :as s]
            [de.sveri.mct.web.user :as u]))

(use-fixtures :each s/browser-setup)
(use-fixtures :once s/server-setup)

(deftest ^:integration dont-repeat-questions
  ;(to (str s/test-base-url "question"))
  (u/sign-in "admin@localhost.de" "admin" "question")
  ;(is (.contains (text "body") (s/t :en :user/user_added)))
  ;(is (find-element {:css "div#flash-message.alert-success"}))
  ;(wait-until #(= (title) "iatern"))
  )

(deftest ^:integration retain-selected-topic
  (to (str s/test-base-url))
  (select-option "#topic_id" {:value "2a5c29d8-05cf-4799-9959-779965732eed"})
  (find-element {:button "div#flash-message.alert-success"})
  ;(u/sign-in "admin@localhost.de" "admin" "question")
  ;(is (.contains (text "body") (s/t :en :user/user_added)))
  ;(is (find-element {:css "div#flash-message.alert-success"}))
  ;(wait-until #(= (title) "iatern"))
  )