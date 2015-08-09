(ns de.sveri.mct.db.answer
  (:require [korma.core :refer [defentity many-to-many select where limit insert values update delete set-fields
                                with fields]]
            [de.sveri.mct.db.entities :refer [answer question]])
  (:import (java.util UUID)))


(defn get-all-answers [] (select answer (with question)))

;(defn get-answer-by-uuid [uuid] (first (select answer (where {:id uuid}) (limit 1))))

(defn create-answer [data]
  (let [id (if (:id data) (:id data) (str (UUID/randomUUID)))]
    (insert answer (values (merge data {:id id})))))

(defn update-answer [id fields]
  (update answer (set-fields fields) (where {:id id})))

(defn create-or-update [data]
  (if-let [id (:id data)]
    (update-answer id data)
    (create-answer data)))

(defn delete-answer [id] (delete answer (where {:id id})))

;(defn by-question [id]
;  (vec (select answer (where {:question_id id}))))