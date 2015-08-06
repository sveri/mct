(ns de.sveri.mct.db.answer
  (:require [korma.core :refer [defentity many-to-many select where limit insert values update delete set-fields
                                with fields]]
            [de.sveri.mct.db.entities :refer [answer question]])
  (:import (java.util UUID)))


(defn get-all-answers [] (select answer (with question)))

;(defn get-answer-by-uuid [uuid] (first (select answer (where {:id uuid}) (limit 1))))

(defn create-answer [data]
  (insert answer (values (merge data {:id (str (UUID/randomUUID))}))))

(defn update-answer [id] fields
  (update answer (set-fields fields) (where {:id id})))

(defn delete-answer [id] (delete answer (where {:id id})))