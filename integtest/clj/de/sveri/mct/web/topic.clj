(ns de.sveri.mct.web.topic
  (:require [de.sveri.mct.web.setup :as s]
            [clojure.test :refer :all]
            [clj-webdriver.taxi :refer :all]))

(use-fixtures :each s/browser-setup)
(use-fixtures :once s/server-setup)

(deftest ^:cur admin-sees-all
         (sign-in "admin@localhost.de" "admin" "question")
         (is (= 3 (count (find-elements {:tag :a, :text "Delete"})))))
