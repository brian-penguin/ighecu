(ns ighecu.ighecu
  "FIXME: my new org.corfield.new/scratch project."
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [selmer.parser :as selmer])
  (:gen-class))

(defn home-page
  [_req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body (selmer/render-file "templates/home.html" {})})

(defn request-page
  [req]
     {:status  200
      :headers {"Content-Type" "text/html"}
      :body    (->>
                (pp/pprint req)
                (str "Request Object: " req))})

(defroutes app-routes
  (GET "/" [] home-page)
  (GET "/request" [] request-page)
  (route/not-found "Error, page not found")
  )

(defn exec
  "Invoke me with clojure -X ighecu.ighecu/exec"
  [opts]
  (println "exec with" opts))

(defn -main
  "Invoke me with clojure -M -m ighecu.ighecu"
  [& args]
  (println "-main with" args)
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    ; Run the server with Ring.defaults middleware
    (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port})
    ; Run the server without ring defaults
    ;(server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))
