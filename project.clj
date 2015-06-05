(defproject info.youhavethewrong/log "1.0-SNAPSHOT"
  :description "A UI for keeping track of maintenance performed upon durable goods."
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}

  :source-paths ["src-clj"]
  :dependencies [[org.clojure/clojure "1.6.0"] 
                 [org.clojure/clojurescript "0.0-3196"]
                 [cljsjs/react "0.13.1-0"]
                 [cljs-http "0.1.30"]
                 [reagent "0.5.0"]
                 [com.cemerick/clojurescript.test "0.2.3"]]
  :plugins [[lein-cljsbuild "1.0.5"]
            [hiccup-bridge "1.0.1"]
            [com.cemerick/clojurescript.test "0.2.3"]]
  :cljsbuild {:test-commands {"phantom" ["phantomjs" :runner "phantom/bind-polyfill.js" "target/test-classes/test.js"]}
              :builds {:test {:source-paths ["src/main/clojurescript" "src/test/clojurescript"]
                              :compiler {:output-to "target/test-classes/test.js"
                                        :optimizations :whitespace
                                        :pretty-print true}}
                       :build {:source-paths ["src/main/clojurescript"]
                             :compiler {:output-to "target/classes/app.js"
                                        :optimizations :advanced
                                        :pretty-print true}}}})
              
