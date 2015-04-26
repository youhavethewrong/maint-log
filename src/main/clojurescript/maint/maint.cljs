(ns maint.maint
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :as async]
            [cljs-http.client :as http]))

(def maint-uri "http://bastion:8080/maint")

(defn get-all-maintenance
  []
  (go (let [response (async/<! (http/get maint-uri))]
        (if (http/unexceptional-status? (:status response))
          (:body response)
          (str "Error while calling webservice "
               (:status response)
               " "
               (:body response))))))

