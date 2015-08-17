(ns de.sveri.mct.types
  (:require [clojure.core.typed :refer [HVec defalias]])
  (:import (clojure.lang APersistentMap)))

(defalias questions (HVec [String]))

(defalias sess-quests (APersistentMap String questions))
