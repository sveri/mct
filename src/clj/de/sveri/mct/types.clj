(ns de.sveri.mct.types
  (:require [clojure.core.typed :refer [HVec defalias HMap Num]])
  (:import (clojure.lang APersistentMap)))

(defalias user (HMap :mandatory {:role String :email String}))

(defalias questions (HVec [String]))

(defalias sess-quests (APersistentMap String questions))

(defalias response (HMap :mandatory {:user_id String :question_id String :answer_id String}))