(ns de.sveri.mct.service.answer
  (:require [clojure.core.typed :as t]
            [de.sveri.mct.types :as ty]))

(t/ann get-max-answers [ty/closp-conf -> t/Num])
(defn get-max-answers [config]
  (+ 1 (:max-answers config)))
