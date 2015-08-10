(ns de.sveri.mct.web.setup
  (:require [reloaded.repl :refer [go stop]]
            [clj-webdriver.taxi :as w]
            [com.stuartsierra.component :as component]
            [joplin.core :as j]
            [taoensso.tower :as tower]
            [de.sveri.mct.components.server :refer [new-web-server new-web-server-prod]]
            [de.sveri.mct.components.handler :refer [new-handler]]
            [de.sveri.mct.components.config :as c]
            [de.sveri.mct.components.db :refer [new-db]]
            [de.sveri.mct.components.components :refer [prod-system]]
            [de.sveri.mct.components.locale :as l]))

(def db-uri "jdbc:sqlite:./db/mct-integ-test.sqlite")
(def migrators "resources/migrators/sqlite")

; custom config for configuration
(def test-config
  {:hostname                "http://localhost/"
   :mail-from               "info@localhost.de"
   :mail-type               :test
   :activation-mail-subject "Please activate your account."
   :activation-mail-body    "Please click on this link to activate your account: {{activationlink}}
Best Regards,

Your Team"
   :activation-placeholder  "{{activationlink}}"
   :smtp-data               {}                                ; passed directly to postmap like {:host "postfix"}
   :jdbc-url                db-uri
   :env                     :dev
   :registration-allowed?   true
   :captcha-enabled?        false
   :captcha-public-key      "your public captcha key"
   :private-recaptcha-key   "your private captcha key"
   :recaptcha-domain        "yourdomain"
   :port                    3001})


(defn test-system []
  (component/system-map
    :locale (l/new-locale)
    :config (c/new-config test-config)
    :db (component/using (new-db) [:config])
    :handler (component/using (new-handler) [:config :locale])
    :web (component/using (new-web-server) [:handler :config])))

(def test-base-url (str "http://localhost:3001/"))

(defn start-browser [browser]
  (j/reset-db
    {:db       {:type :sql,
                :url  db-uri}
     :migrator migrators
     :seed "joplin.seeds/clojure"})
  (w/set-driver! {:browser browser}))

(defn stop-browser []
  (w/quit))

(defn start-server []
  (reloaded.repl/set-init! test-system)
  (go))

(defn stop-server []
  (stop))

(defn server-setup [f]
  (start-server)
  (f)
  (stop-server))

(defn browser-setup [f]
  (start-browser :firefox)
  (f)
  (stop-browser))

;; locale stuff

(def t (tower/make-t l/tconfig))
