(ns de.sveri.mct.service.question
  (:require [clojure.core.typed :as t]))

(def asked-questions (atom {}))

(defn- correct->boolean [idx params]
  (if ((keyword (str "answer_correct_" idx)) params) true false))

(defn params->answers [q-uuid params a-count]
  (reduce
    (fn [a idx]
      (let [answer ((keyword (str "answer_" idx)) params)]
        (if (seq answer)
          (conj a {:question_id q-uuid :correct (correct->boolean idx params)
                   :answer      answer :id ((keyword (str "answer_id_" idx)) params)})
          a)))
    [] (range 1 (+ 1 a-count))))

(defn parse-answered-question [sess-id {:keys [question_id topic_id] :as params}]
  (swap! asked-questions (fn [old] (let [v (get old sess-id [])] (assoc old sess-id (conj v question_id)))))
  (println @asked-questions))


(t/defn get-answered-questions [sess-id :- String ])

