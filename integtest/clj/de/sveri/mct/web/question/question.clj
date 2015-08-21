(ns de.sveri.mct.web.question.question
  (:require [de.sveri.mct.web.setup :as s]
            [clojure.test :refer :all]
            [clj-webdriver.taxi :refer :all]
            [de.sveri.mct.web.user :as u]
            [de.sveri.mct.db.question :as db-q]))

(use-fixtures :each s/browser-setup)
(use-fixtures :once s/server-setup)

(deftest ^:integration create-question
  (u/sign-in "local@local.de" "local" "question/create")
  (let [q "quest1"
        a1 "a1"
        a2 "a2"]
    (select-option "#topic_id" {:value "1a5c29d8-05cf-4799-9959-779965732eed"})
    (select "#answer_correct_1")
    (quick-fill-submit {"#question" q}
                       {"#answer_1" a1}
                       {"#answer_2" a2}
                       {"#question" submit})
    (is (.contains (text "body") q))
    (click (find-element {:tag :a, :text q}))
    (is (selected? "option[value='1a5c29d8-05cf-4799-9959-779965732eed']"))
    (is (.contains (text "#answer_1") a1))
    (is (selected? "#answer_correct_1"))))

(deftest ^:integration update-question
  (u/sign-in "local@local.de" "local" "question/e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba3cb")
  (clear "#question")
  (clear "#answer_1")
  (clear "#answer_5")
  (clear "#answer_7")
  (select-option "#topic_id" {:value "1a5c29d8-05cf-4799-9959-779965732eed"})
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
    (is (selected? "option[value='1a5c29d8-05cf-4799-9959-779965732eed']"))
    (is (.contains (text "#answer_1") a1))
    (is (.contains (text "#answer_4") a5))
    (is (.contains (text "#answer_5") a7))
    (is (selected? "#answer_correct_3"))))

(deftest ^:integration admin-sees-all
  (u/sign-in "admin@localhost.de" "admin" "question")
  (is (= (count (db-q/get-all-questions {:email "" :role "admin"})) (count (find-elements {:tag :a, :text "Delete"})))))

(deftest ^:integration user-sees-own
  (u/sign-in "local@local.de" "local" "question/create")
  (let [q "quest1"
        a1 "a1"
        a2 "a2"]
    (select-option "#topic_id" {:value "1a5c29d8-05cf-4799-9959-779965732eed"})
    (select "#answer_correct_1")
    (quick-fill-submit {"#question" q}
                       {"#answer_1" a1}
                       {"#answer_2" a2}
                       {"#question" submit}))
  (is (= 2 (count (find-elements {:tag :a, :text "Delete"})))))

(deftest ^:integration user-sees-no-other
  (u/sign-in "local@local.de" "local" "question/e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba5cb")
  (is (.contains (text "body") "Page not found.")))

(deftest ^:integration admin-sees-other
  (u/sign-in "admin@localhost.de" "admin" "question/e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba3cb")
  (is (.contains (text "body") "Who is the creator of Clojure")))
