(ns de.sveri.mct.service.topic)

(defn add-parent-topic-name [topic topics]
  (if-let [t (first (filter #(= (:topic_id topic) (:id %)) topics))]
    (assoc topic :parent-name (:name t))
    topic))
