(ns log.core
  (:require [clojure.string :as string] 
            [log.maint :as maint]
            [reagent.core :as reagent]))

(enable-console-print!)

(def app-state
  (reagent/atom {:completed []}))

(defn add-maintenance
  []
  (let [durable-good ""
        notes ""
        userid 13]
    (fn []
      [:div
       [:input {:type "text"
                :placeholder "Durable good."
                :value durable-good } ]
       [:input {:type "text"
                :placeholder "Describe maintenance completed."
                :value notes } ]
       [:input {:type "text"
                :placeholder "User ID"
                :value userid } ]
       [:button "Add"]])))

(defn maint-item
  [m]
  [:li [:span (str (js/Date. (:date m)))]
   [:ul
    [:li (:durableGood m)]
    [:li (:notes m)]]])

(defn maintenance-list
  []
  [:div
   [:h1 "Maintenance log"]
   [:ul
    (for [m @app-state]
      (maint-item m))]])

(defn log-app
  []
  [:div
   [:div
    [maintenance-list]
    [add-maintenance]
    ]])

(defn render
  []
  (reagent/render-component
   [log-app]
   (.getElementById js/document "app")))

(defn handle-maintenance
  [maintenance]
  (reset! app-state maintenance)
  (.log js/console @app-state)
  (render))

(defn fetch-maintenance
  [f]
  (maint/get-all-maintenance f))

(defn start
  []
  (fetch-maintenance handle-maintenance))
