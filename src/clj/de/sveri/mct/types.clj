(ns de.sveri.mct.types
  (:require [clojure.core.typed :refer [HVec defalias HMap Num U]])
  (:import (clojure.lang APersistentMap)))

(defalias param-checkbox '{:op '"left" :left String})

(defalias user (HMap :mandatory {:role String :email String}))

(defalias closp-conf (HMap :mandatory {:max-answers Num}))

(defalias questions (HVec [String]))

(defalias sess-quests (APersistentMap String questions))

(defalias response (HMap :mandatory {:user_id String :question_id String :answer_id String}))

(defalias response-params (HMap :mandatory {:question_id String :topic_id String}
                               :optional {:answer_correct_id_1 String :answer_correct_1 param-checkbox
                                          :answer_correct_id_2 String :answer_correct_2 param-checkbox
                                          :answer_correct_id_3 String :answer_correct_3 param-checkbox
                                          :answer_correct_id_4 String :answer_correct_4 param-checkbox
                                          :answer_correct_id_5 String :answer_correct_5 param-checkbox
                                          :answer_correct_id_6 String :answer_correct_6 param-checkbox
                                          :answer_correct_id_7 String :answer_correct_7 param-checkbox
                                          :answer_correct_id_8 String :answer_correct_8 param-checkbox
                                          :answer_correct_id_9 String :answer_correct_9 param-checkbox
                                          :answer_correct_id_10 String :answer_correct_10 param-checkbox}))