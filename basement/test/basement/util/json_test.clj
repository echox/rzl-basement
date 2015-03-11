(ns basement.util.json-test
  (:require [clojure.test :refer :all]
            [basement.util.json :refer :all]))

(deftest test-json

  (testing "link without method"
    (let [rel "RELATION"
          type "foo/bar"
          href "/some/link"
          result (link rel type href)]
      (is (= (:rel result) rel))
      (is (= (:type result) type))
      (is (= (:href result) href))))

    (testing "link with method"
    (let [rel "rel"
          type "thisisthetype"
          href "some/other/link"
          method "DELETE"
          result (link rel type href method)]
      (is (= (:rel result) rel))
      (is (= (:type result) type))
      (is (= (:href result) href))
      (is (= (:method result) method))))
  )
