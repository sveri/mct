(ns de.sveri.mct.service.response
  (:require [clojure.core.typed :as t]
            [de.sveri.mct.types :as ty]
            [de.sveri.mct.service.answer :as ser-a])
  (:import (clojure.lang Keyword)))

(t/ann params->db [ty/closp-conf ty/response-params ty/user -> (t/Seq ty/response)])
;(t/ann params->db [ty/closp-conf ty/response-params ty/user -> (t/HVec [ty/response *])])
(defn params->db [config params user-id]
  (let [q-id (:question_id params)]
    ;(vec
    ;(t/for [idx (ser-a/get-max-answers config)
    ;             :let [id-key (keyword (str "answer_correct_id_" idx))
    ;                   id-correct (keyword (str "answer_correct_" idx))]
    ;             :when (params id-correct)]
    ;         {:question_id q-id :user_id user-id :answer_id (id-key params)}
    ;[{:question_id q-id :user_id user-id :answer_id "iaern"}]
    (t/for
      [idx (range 1 (ser-a/get-max-answers config))
       :let [id-key (keyword (str "answer_correct_id_" idx))
             id-correct :- Keyword (keyword (str "answer_correct_" idx))]
       ;:when (:answer_correct_1 params )]
       :when (id-correct params)]
      :- ty/response
      {:question_id q-id :user_id user-id :answer_id (id-key params)})
    ;)
    ;(reduce
    ;  (fn [a b]
    ;    (let [id-key (keyword (str "answer_correct_id_" b))
    ;          id-correct (keyword (str "answer_correct_" b))]
    ;      (if (id-correct params)
    ;        (conj a {:question_id q-id :user_id user-id :answer_id (id-key params)})
    ;        a)))
    ;  [] (range 1 (ser-a/get-max-answers config)))

    ))
