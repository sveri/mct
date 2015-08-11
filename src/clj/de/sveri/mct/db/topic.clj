(ns de.sveri.mct.db.topic
  (:require [korma.core :refer [defentity many-to-many select where limit insert values update delete set-fields
                                with fields]]
            [de.sveri.mct.db.entities :refer [topic]])
  (:import (java.util UUID)))

(defn get-all-topics [] (select topic))

(defn get-topic-by-id [id] (first (select topic (where {:id id}) (limit 1))))

(defn create-topic [data]
  (let [id (if (:id data) (:id data) (str (UUID/randomUUID)))]
  (insert topic (values (merge data {:id id})))))

(defn update-topic [uuid fields]
  (update topic (set-fields fields) (where {:id uuid})))

(defn delete-topic [uuid] (delete topic (where {:id uuid})))