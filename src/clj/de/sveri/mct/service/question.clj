(ns de.sveri.mct.service.question)

(defn- correct->boolean [idx params]
  (if ((keyword (str "answer_correct_" idx)) params) true false))

(defn params->answers [q-uuid params a-count]
  (reduce
    (fn [a idx]
      (let [answer ((keyword (str "answer_" idx)) params)]
        (if (seq answer)
          (conj a {:question_id q-uuid :correct (correct->boolean idx params)
                   :answer      answer})
          a)))
    [] (range 1 (+ 1 a-count))))

