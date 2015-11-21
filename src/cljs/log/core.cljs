(ns log.core
  (:require [clojure.string :as string] 
            [log.maint :as maint]
            [reagent.core :as reagent]))

(enable-console-print!)

(def app-state
  (reagent/atom {:completed []}))

(declare fetch-maintenance handle-maintenance)

(defn add-maintenance
  []
  (let [durable-good (reagent/atom "")
        notes (reagent/atom "")]
    (fn []
      [:div
       [:form {:on-submit (fn [] (fetch-maintenance handle-maintenance))}
        [:input {:type "text"
                 :placeholder "Durable good."
                 :on-change #(reset! durable-good (-> % .-target .-value))
                 :value @durable-good } ]
        [:input {:type "text"
                 :placeholder "Describe maintenance completed."
                 :on-change #(reset! notes (-> % .-target .-value))
                 :value @notes } ]
        [:button.button
         {:on-click (partial maint/add-new-maintenance @durable-good @notes "13") }
         [:span "Add"]]]])))

(defn maint-item
  [m]
  [:tr
   [:td [:strong (str (js/Date. (:date m)))]]
   [:td (:durableGood m)]
   [:td (:notes m)]])

(defn maintenance-list
  []
  [:div
   [:table.table
    (for [m @app-state]
      (maint-item m))
    ]])

(defn log-app
  []
  [:div
   [:div.navbar.navbar-fixed-top.brand
    [:div.navbar-inner
     [:div.container
      [:div.brand {:id :headline}
       [:a {:href "//youhavethewrong.info"}
        "YouHaveTheWrong.info"]]]]]
   [:div {:id :content :class :container}
    [:h1 {:id :h1} "Maintenance log"]
    [add-maintenance]
    [maintenance-list]
    ]])

(defn render
  []
  (reagent/render-component
   [log-app]
   (.getElementById js/document "app")))

(defn handle-maintenance
  [maintenance]
  (reset! app-state maintenance)
  (render))

(defn fetch-maintenance
  [f]
  (maint/get-all-maintenance f))

(defn start
  []
  (fetch-maintenance handle-maintenance))
