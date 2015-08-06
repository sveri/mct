(ns de.sveri.mct.service.question-test
  (:require [clojure.test :refer :all]
            [de.sveri.mct.service.question :as q]))

(def q-uuid "abc")

(def params {:answer_3 "",
             :question "rdtr",
             :topic_id "e4c7cfc0-d2cf-4ce2-b0a8-f7fb10bba4cb",
             :answer_2 "a2",
             :answer_1 "a1",
             :uuid "",
             :answer_correct_1 "on"})

(deftest params->answers-test
  (is (= (q/params->answers q-uuid params 3)
         [{:question_id q-uuid :answer "a1" :correct true}
          {:question_id q-uuid :answer "a2" :correct false}])))
