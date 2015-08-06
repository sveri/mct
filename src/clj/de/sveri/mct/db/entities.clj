(ns de.sveri.mct.db.entities
  (:require [korma.core :refer [defentity belongs-to has-many has-one pk]]))

(declare topic question user answer)

(defentity user
           (pk :email))

(defentity topic
           (has-one topic))

(defentity answer
           (belongs-to question))

(defentity question
           (belongs-to user {:fk :user_email})
           (belongs-to topic)
           (has-many answer))

