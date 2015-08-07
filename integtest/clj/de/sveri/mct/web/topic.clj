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
  (select-option "#topic_id" {:value "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb"})
  (let [t "t1"]
    (quick-fill-submit {"#topic-name" t}
                       {"#topic-name" submit})
    (is (.contains (text "body") t))))                      ;TODO add test for parent topic

(deftest ^:integration update-wo-parent
  (u/sign-in "admin@localhost.de" "admin" "topic")
  (click (find-element {:tag :a, :text "Gesundheit"}))
  (clear "#topic-name")
  (let [t "t1"]
    (quick-fill-submit {"#topic-name" t}
                       {"#topic-name" submit})
    (is (.contains (text "body") t))))

(deftest ^:integration update-with-parent
  (u/sign-in "admin@localhost.de" "admin" "topic")
  (click (find-element {:tag :a, :text "Heilpraktiker"}))
  (clear "#topic-name")
  (let [t "t1"]
    (quick-fill-submit {"#topic-name" t}
                       {"#topic-name" submit})
    (is (.contains (text "body") t))))