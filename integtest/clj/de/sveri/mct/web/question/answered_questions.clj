(ns de.sveri.mct.web.question.answered-questions
  (:require [de.sveri.mct.web.setup :as s]
            [clojure.test :refer :all]
            [clj-webdriver.taxi :refer :all]))


(defn browser-setup [f]
  (s/start-browser :htmlunit )
  (f)
  (s/stop-browser))

(use-fixtures :each browser-setup)
(use-fixtures :once s/server-setup)

