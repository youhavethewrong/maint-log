(ns maint.core
  (:require [clojure.string :as string] 
            [maint.maint :as maint]
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
       [:button {:on-click #(maint/add-new-maintenance
                             durable-good
                             notes
                             userid)} "Add"]])))

(defn maint-item
  [m]
  [:li [:span m]])

(defn maint-list
  []
  [:div
   [:h1 "Maintenance log"]
   [:ul
    (for [m (:completed @app-state)]
      (maint-item m))]])

(defn maint-app
  []
  [:div
   [:div
    [maint-list]
    [add-maintenance]
    ]])

(defn render
  []
  (reagent/render-component
   [maint-app]
   (.getElementById js/document "app")))

(defn start
  []
  (let [stuff (maint/get-all-maintenance)]
    (do
      (swap! app-state :completed stuff )
      (render))))
