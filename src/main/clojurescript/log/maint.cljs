(ns log.maint
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :as async]
            [cljs-http.client :as http]))

(def maint-uri "http://bulwark/maint/maint")

(defn return-body-when-ok
  [response]
  (if (http/unexceptional-status? (:status response))
    (:body response)
    (.log js/console
          (str "Error while calling webservice "
               (:status response)
               " "
               (:body response)))))

(defn get-all-maintenance
  [f]
  (go (let [response (async/<! (http/get maint-uri))]
        (f (return-body-when-ok response)))))

(defn add-new-maintenance
  [durable-good notes userid]
  (.log js/console (str "Going to post "
                        durable-good
                        " "
                        notes
                        " "
                        userid))
  (http/post maint-uri
                                          {:json-params
                                           {:durableGood durable-good
                                            :notes notes
                                            :userid userid
                                            :date (js/Date.)}}))
