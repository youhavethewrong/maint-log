(ns maint.maint
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :as async]
            [cljs-http.client :as http]))

(def maint-uri "http://bastion:8080/maint")

(defn return-body-when-ok
  [response]
  (if (http/unexceptional-status? (:status response))
    (:body response)
    (str "Error while calling webservice "
         (:status response)
         " "
         (:body response))))

(defn get-all-maintenance
  []
  (go (let [response (async/<! (http/get maint-uri))]
        (return-body-when-ok response))))

(defn add-new-maintenance
  [durable-good notes userid]
  (go (let [response
            (async/<!
             (http/post
              maint-uri
              {:json-params
               {:durableGood durable-good
                :notes notes
                :userid userid}}))]
        (return-body-when-ok response))))
