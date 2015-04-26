(ns maint.core
  (:require [clojure.string :as string] 
            [maint.maint :as maint]
            [reagent.core :as reagent]))

(enable-console-print!)

(def app-state
  (reagent/atom {:completed []}))

;; (defn add-maintenance
;;   []
;;   (let [maintenance (reagent/atom {})]
;;     (fn []
;;       [:div
;;        [:input {:type "text"
;;                 :placeholder "Describe maintenance completed. "
;;                 :value 
;;                 :on-change #(swap! maintenance :description (-> % .-target .-value))}]
;;        [:button {:on-click #(get-counts @request)} "Add"]])))

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
    ;; [add-maintenance]
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
