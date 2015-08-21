(ns de.sveri.mct.web.index
  (:require [clojure.test :refer :all]
            [clj-webdriver.taxi :refer :all]
            [de.sveri.mct.web.setup :as s]
            [de.sveri.mct.web.user :as u]))

(use-fixtures :each s/browser-setup)
(use-fixtures :once s/server-setup)

(deftest ^:integration dont-repeat-questions
  (to (str s/test-base-url))
  (select-option "#topic_id" {:value "2a5c29d8-05cf-4799-9959-779965732eed"})
  (click (find-element {:css "button#select-topic"}))
  (let [asked-quests (atom [])]
    (while (and (< (count @asked-quests) 6) (= (count @asked-quests) (count (into #{} @asked-quests))))
      (swap! asked-quests conj (value (find-element {:css "input#question_id"})))
      (click (find-element {:css "button#answer-question"}))
      (is (= (count @asked-quests) (count (into #{} @asked-quests)))))))

(deftest ^:integration retain-selected-topic
  (to (str s/test-base-url))
  (select-option "#topic_id" {:value "2a5c29d8-05cf-4799-9959-779965732eed"})
  (click (find-element {:css "button#select-topic"}))
  (is (selected? "option[value='2a5c29d8-05cf-4799-9959-779965732eed']")))

(deftest ^:cur answer-questions
  (to (str s/test-base-url))
  (select-option "#topic_id" {:value "2a5c29d8-05cf-4799-9959-779965732eed"})
  (click (find-element {:css "button#select-topic"}))
  (let [asked-qs (atom [])]
    (dorun (for [idx (range 1 4)]
       (do (swap! asked-qs conj {:answer_id   (value (str "#answer_correct_id_" idx))
                                 :correct     (value (str "#answer_correct_" idx))
                                 :question_id (value "#question_id")})
           (click (find-element {:css "button#answer-question"})))))

    (wait-until #(= "itaen" (title)))
    (to (str s/test-base-url "/question/answered"))))