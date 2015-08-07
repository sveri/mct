(ns de.sveri.mct.web.user
  (:require [clojure.test :refer :all]
            [clj-webdriver.taxi :refer :all]
            [de.sveri.mct.web.setup :as s]))

(defn sign-in [& [name pw link]]
  (to (str s/test-base-url (or link "admin/users")))
  (quick-fill-submit {"#upper_email" (or name "admin@localhost.de")}
                     {"#upper_password" (or pw "admin")}
                     {"#upper_password" submit}))


(use-fixtures :each s/browser-setup)
(use-fixtures :once s/server-setup)

(deftest ^:integration login-invalid-username
  (to s/test-base-url)
  (quick-fill-submit {"#upper_email" "foo"}
                     {"#upper_password" submit})
  (is (.contains (text "body") (s/t :en :user/username_wrong))))

(deftest ^:integration login-invalid-password
  (to s/test-base-url)
  (quick-fill-submit {"#upper_email" "admin@localhost.de"}
                     {"#upper_password" "uiatern"}
                     {"#upper_password" submit})
  (is (.contains (text "body") (s/t :en :user/pass_correct))))

(deftest ^:integration login-and-forward
  (to (str s/test-base-url "admin/users"))
  (quick-fill-submit {"#upper_email" "admin@localhost.de"}
                     {"#upper_password" "admin"}
                     {"#upper_password" submit})
  (is (.contains (text "body") "Users")))
