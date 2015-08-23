(ns de.sveri.mct.service.response
  (:require [clojure.core.typed :as t]
            [de.sveri.mct.types :as ty]
            [de.sveri.mct.service.answer :as ser-a]))

(t/ann params->db [ty/closp-conf ty/response-params ty/user -> (t/HVec [ty/response])])
(defn params->db [config params user]
  (let [user-id (:email user)
        q-id (:question_id params)]
    (reduce
      (fn [a b]
        (let [id-key (keyword (str "answer_correct_id_" b))
              id-correct (keyword (str "answer_correct_" b))]
          (if (id-correct params)
            (conj a {:question_id q-id :user_id user-id :answer_id (id-key params)})
            a)))
      [] (range 1 (ser-a/get-max-answers config)))))

(reduce (fn [a b] (if any-cond (conj a {:any "map}"}) a)) [] some-seq)