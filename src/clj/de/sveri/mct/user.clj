(ns de.sveri.mct.user
  (:require [reloaded.repl :refer [go reset stop]]
            [de.sveri.mct.components.components :refer [dev-system]]))

(defn start-dev-system []
  (go))

(reloaded.repl/set-init! dev-system)
