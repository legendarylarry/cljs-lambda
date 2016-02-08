(defproject cljs-lambda/lein-template "0.3.2-SNAPSHOT"
  :description "Clojurescript on AWS Lambda"
  :url "https://github.com/nervous-systems/cljs-lambda"
  :license {:name "Unlicense" :url "http://unlicense.org/UNLICENSE"}
  :scm {:name "git" :url "https://github.com/nervous-systems/cljs-lambda"}
  :deploy-repositories [["clojars" {:creds :gpg}]]
  :signing {:gpg-key "moe@nervous.io"}
  :eval-in-leiningen true)
