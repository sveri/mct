(ns de.sveri.mct.web.topic
  (:require [de.sveri.mct.web.setup :as s]
            [de.sveri.mct.web.user :as u]
            [clojure.test :refer :all]
            [clj-webdriver.taxi :refer :all]))

(use-fixtures :each s/browser-setup)
(use-fixtures :once s/server-setup)

(deftest ^:integration create-wo-parent
  (u/sign-in "admin@localhost.de" "admin" "topic/create")
  (let [t "t1"]
    (quick-fill-submit {"#topic-name" t}
                       {"#topic-name" submit})
    (is (.contains (text "body") t))))

(deftest ^:integration create-with-parent
  (u/sign-in "admin@localhost.de" "admin" "topic/create")
  (select-option "#topic_id" {:value "3a5c29d8-05cf-4799-9959-779965732eed"})
  (let [t "t1"]
    (quick-fill-submit {"#topic-name" t}
                       {"#topic-name" submit})
    (is (.contains (text "body") t))))                      ;TODO add test for parent topic

(deftest ^:integration update-wo-parent
  (u/sign-in "admin@localhost.de" "admin" "topic")
  (click (find-element {:tag :a, :text "Computer Science"}))
  (clear "#topic-name")
  (let [t "t1"]
    (quick-fill-submit {"#topic-name" t}
                       {"#topic-name" submit})
    (is (.contains (text "body") t))))

(deftest ^:integration update-with-parent
  (u/sign-in "admin@localhost.de" "admin" "topic")
  (click (find-element {:tag :a, :text "Languages"}))
  (clear "#topic-name")
  (let [t "t1"]
    (quick-fill-submit {"#topic-name" t}
                       {"#topic-name" submit})
    (is (.contains (text "body") t))))