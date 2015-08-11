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