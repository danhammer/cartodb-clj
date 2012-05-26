(ns cartodb.client
  (:use [cascalog.api]
        [clojure.data.json :only [read-json]])
  (:require [clojure.contrib.str-utils :as str-utils]
            [clj-http.client :as client]
            [cheshire.core :as json]))

(defn url-base
  "returns a CartoDB http address base an SQL query, given a CartoDB
  account name

  Example usage:
  (url-base \"wri-01\") => \"http://wri-01.cartodb.com/api/v2/sql\""
  [account]
  (let [api-sql ".cartodb.com/api/v2/sql"]
    (str "http://" account api-sql)))

(defn sqlize
  "returns a string that is compatible with SQL queries, based on
  clojure-style inputs (e.g., keywords and strings)."
  [x]
  (cond
   (keyword? x) (str-utils/re-sub #"\/" "." (str-utils/re-sub #"^:" "" (str x)))
   (string? x) (str "'" x "'")
   :else x))

(defn sql-exp
  "returns a SQL expression."
  [op p1 p2]
  (str (sqlize p1) " " op " " (sqlize p2)))

(defn cartodb-collection
  "returns CartoDB records based on the `sql` query (input as a
  string) as an array of clojure dictionaries, with the columns as
  keys and the values as strings."
  [account sql]
  (let [base (url-base account)]
    (->> (client/get base {:query-params {"q" sql}})
         :body
         read-json
         :rows)))

(defn str-append
  "append multiple strings, with spaces interposed."
  [& strs]
  (apply str (interpose " " strs)))

(defn grab-forma-pts
  "returns a vector of x- and y-coordinates for `n` forma points in
  Indonesia, with probability greater than 50 (the only points stored
  in the `forma_cdm` table)."
  [n]
  (let [sql (sql-stmt "SELECT x,y"
                      "FROM forma_cdm"
                      "LIMIT" n)
        cdb-data (cartodb-collection "wri-01" sql)
        grab-data (fn [key-vec] (map (comp read-string key-vec) cdb-data))]
    (map grab-data [:x :y])))

