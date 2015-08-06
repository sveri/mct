(ns de.sveri.mct.web.question
  (:require [de.sveri.mct.web.setup :as s]
            [clojure.test :refer :all]
            [clj-webdriver.taxi :refer :all]))

(use-fixtures :each s/browser-setup)
(use-fixtures :once s/server-setup)

(defn sign-in [& [name pw link]]
  (to (str s/test-base-url (or link "admin/users")))
  (quick-fill-submit {"#upper_email" (or name "admin@localhost.de")}
                     {"#upper_password" (or pw "admin")}
                     {"#upper_password" submit}))

(deftest ^:cur create-question
  (sign-in "local@local.de" "local" "question/create")
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
    (is (selected? "#answer_correct_1"))
    ;(wait-until #(= (title) "iatern"))
    ))
