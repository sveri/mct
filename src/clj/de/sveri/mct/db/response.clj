(ns de.sveri.mct.db.response
  (:require [korma.core :refer [defentity many-to-many select where limit insert values update delete set-fields with
                                fields]]
            [de.sveri.mct.db.entities :refer [response]]
            [clojure.core.typed :as t]
            [de.sveri.mct.types :as ty]))

(t/ann responses [String -> ty/response])
(defn responses [q-id]
  (select response (where {:question_id q-id})))
