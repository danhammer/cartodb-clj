(defproject cartodb-clj "1.0.0-SNAPSHOT"
  :description "Access CartoDB data from Clojure."
  :source-path "src/clj"
  :resources-path "resources"
  :dev-resources-path "dev"
  :repositories {"conjars" "http://conjars.org/repo/"}
  :jvm-opts ["-XX:MaxPermSize=128M"
             "-XX:+UseConcMarkSweepGC"
             "-Xms1024M" "-Xmx1048M" "-server"]
  :javac-options {:debug "true" :fork "true"}
  :plugins [[swank-clojure "1.4.0-SNAPSHOT"]
            [lein-clojars "0.9.0"]]
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [clojure-csv/clojure-csv "2.0.0-alpha1"]
                 [clj-http "0.4.3"]
                 [cheshire "4.0.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
<<<<<<< HEAD
                 [org.clojure/data.json "0.1.2"]
                 [org.apache.mahout/mahout-core "0.5"]
                 [org.apache.mahout/mahout-math "0.5"]
                 [org.apache.mahout/mahout-utils "0.5"]]
  :dev-dependencies [[org.apache.hadoop/hadoop-core "0.20.2-dev"]
                     [midje-cascalog "0.4.0"]])
=======
                 [org.clojure/data.json "0.1.2"]]
:dev-dependencies [[midje "1.4.0"]])
>>>>>>> upstream/develop
