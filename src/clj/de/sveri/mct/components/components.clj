(ns de.sveri.mct.components.components
  (:require
    [com.stuartsierra.component :as component]
    (system.components
      [repl-server :refer [new-repl-server]])
    [de.sveri.mct.components.server :refer [new-web-server new-web-server-prod]]
    [de.sveri.mct.components.handler :refer [new-handler]]
    [de.sveri.mct.components.config :as c]
    [de.sveri.mct.components.db :refer [new-db]]
    [de.sveri.mct.components.locale :as l]))


(defn dev-system []
  (component/system-map
    :locale (l/new-locale)
    :config (c/new-config (c/prod-conf-or-dev))
    :db (component/using (new-db) [:config])
    :handler (component/using (new-handler) [:config :locale])
    :web (component/using (new-web-server) [:handler :config])))


(defn prod-system []
  (component/system-map
    :locale (l/new-locale)
    :config (c/new-config (c/prod-conf-or-dev))
    :db (component/using (new-db) [:config])
    :handler (component/using (new-handler) [:config :locale])
    :web (component/using (new-web-server-prod) [:handler :config])))
