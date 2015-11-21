(defproject info.youhavethewrong/log "1.0-SNAPSHOT"
  :description "A UI for keeping track of maintenance performed upon durable goods."
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}

  :dependencies [[org.clojure/clojure "1.7.0"] 
                 [org.clojure/clojurescript "1.7.170"]
                 [cljsjs/react "0.14.3-0"]
                 [cljs-http "0.1.37"]
                 [reagent "0.5.1"]]
  :hooks [leiningen.cljsbuild]
  
  :plugins [[lein-cljsbuild "1.1.0"]
            [hiccup-bridge "1.0.1"]]
  :cljsbuild {
              :builds [{:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/app.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})

