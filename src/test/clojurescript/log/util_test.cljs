(ns log.util-test
    (:require-macros [cemerick.cljs.test :refer (is deftest with-test run-tests testing test-var)])
    (:require [cemerick.cljs.test :as test]
              [sample.util :as util]
              [goog.crypt :as crypt]
              [goog.crypt.Sha1 :as Sha1]))

(deftest interweave
  (is (= [:a :s :b :s :c :s]
         (util/interweave :s [:a :b :c]))))

(deftest hash-factory
  (testing "instantiate Sha1"
    (is (= (type (crypt/Sha1.))
         (type (util/get-hash :sha1)))))
  (testing "instantiate Sha256"
    (is (= (type (goog.crypt.Sha256.))
           (type (util/get-hash :sha256))))))

(def-test)














;; (deftest javascript-allows-div0
;;     (is (= js/Infinity (/ 1 0) (/ (int 1) (int 0)))))

;; (with-test
;;     (defn pennies->dollar-string
;;           [pennies]
;;               {:pre [(integer? pennies)]}
;;                   (str "$" (int (/ pennies 100)) "." (mod pennies 100)))
;;       (testing "assertions are nice"
;;             (is (thrown-with-msg? js/Error #"integer?" (pennies->dollar-string 564.2)))))
