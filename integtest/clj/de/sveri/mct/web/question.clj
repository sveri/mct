(ns de.sveri.mct.web.question
  (:require [de.sveri.mct.web.setup :as s]
            [clojure.test :refer :all]
            [clj-webdriver.taxi :refer :all]
            [de.sveri.mct.web.user :as u]))

(use-fixtures :each s/browser-setup)
(use-fixtures :once s/server-setup)

(deftest ^:integration create-question
  (u/sign-in "local@local.de" "local" "question/create")
  (let [q "quest1"
        a1 "a1"
        a2 "a2"]
    (select-option "#topic_id" {:value "4a5c29d8-05cf-4799-9959-779965732eed"})
    (select "#answer_correct_1")
    (quick-fill-submit {"#question" q}
                       {"#answer_1" a1}
                       {"#answer_2" a2}
                       {"#question" submit})
    (is (.contains (text "body") q))
    (click (find-element {:tag :a, :text q}))
    (is (selected? "option[value='4a5c29d8-05cf-4799-9959-779965732eed']"))
    (is (.contains (text "#answer_1") a1))
    (is (selected? "#answer_correct_1"))))

(deftest ^:integration update-question
  (u/sign-in "local@local.de" "local" "question/e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba6cb")
  (clear "#question")
  (clear "#answer_1")
  (select-option "#topic_id" {:value "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb"})
  (click (find-element {:tag :input :id "answer_correct_1"}))
  (select "#answer_correct_3")
  (let [q "quest changed"
        a1 "a1 changed"
        a5 "a5"
        a7 "a7"]
    (quick-fill-submit {"#question" q}
                      {"#answer_1" a1}
                      {"#answer_5" a5}
                      {"#answer_7" a7}
                      {"#question" submit})
    (click (find-element {:tag :a, :text q}))
    (is (selected? "option[value='e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb']"))
    (is (.contains (text "#answer_1") a1))
    (is (.contains (text "#answer_5") a5))
    (is (.contains (text "#answer_6") a7))
    (is (selected? "#answer_correct_3"))))
  ;(wait-until #(= (title) "iatern"))

(deftest ^:integration admin-sees-all
  (u/sign-in "admin@localhost.de" "admin" "question")
  (is (= 3 (count (find-elements {:tag :a, :text "Delete"})))))

(deftest ^:integration user-sees-own
  (u/sign-in "local@local.de" "local" "question")
  (is (= 2 (count (find-elements {:tag :a, :text "Delete"})))))
