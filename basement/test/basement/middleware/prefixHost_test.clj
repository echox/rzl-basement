(ns basement.middleware.prefixHost-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [basement.middleware.prefixHost :refer :all]))

(deftest test-prefix

  (testing "prefix-href"
    (let [testdata {:test "txt"
                    :data "/test"
                    :link [{:title "t" :href "without/"}
                           {:with "href" :href "/with"}]
                    :href "/replace"
                    }
          host "http://example.org:8080"
          result (prefix-href testdata host)]
      (is (= (:test result) (:test testdata)))
      (is (= (:data result) (:data testdata)))
      (is (= (:href (get (:link result) 0)) (:href (get (:link testdata) 0))))
      (is (= (:title (get (:link result) 0)) (:title (get (:link testdata) 0))))
      (is (= (:with (get (:link result) 1)) (:with (get (:link testdata) 1))))
      (is (= (:href (get (:link result) 1)) (str host (:href (get (:link testdata) 1)))))
      (is (= (:href result) (str host (:href testdata))))))

  (testing "request parsing in hoststring with http"
    (let [uri "http://example.org:8080"
          request (mock/request :get uri)
          result (get-hoststring request)]
      (is (= result uri))))

  (testing "request parsing in hoststring with https"
    (let [uri "https://example.org:8080"
          request (mock/request :get uri)
          result (get-hoststring request)]
      (is (= result uri)))))
