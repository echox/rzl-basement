(ns basement.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [basement.handler :refer :all]))

(deftest test-handler

  (testing "content negotiation"
    (let [request (assoc-in (mock/request :get "/foo/bar")
                            [:headers "accept"] "text/html,application/xhtml+xml,ene/muh")
          handler {"application/xhtml+xml" (fn [request] "xml")}
          selected ((negotiator handler) request)]
      (is (= (selected request) "xml"))))

    (testing "content negotiation with no match"
    (let [request (assoc-in (mock/request :get "/foo/bar")
                            [:headers "accept"] "text/html,ene/muh")
          handler {"application/xhtml+xml" (fn [request] "xml")}
          selected ((negotiator handler) request)]
      (is (= (selected request) nil))))

    (testing "content negotiation with only one accept"
    (let [request (assoc-in (mock/request :get "/foo/bar")
                            [:headers "accept"] "text/html")
          handler {"text/html" (fn [request] "xml")}
          selected ((negotiator handler) request)]
      (is (= (selected request) "xml"))))

      (testing "content negotiation with only one accept and no match"
    (let [request (assoc-in (mock/request :get "/foo/bar")
                            [:headers "accept"] "text/html")
          handler {"application/xhtml+xml" (fn [request] "xml")}
          selected ((negotiator handler) request)]
      (is (= (selected request) nil))))
  )
