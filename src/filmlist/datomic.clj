(ns filmlist.datomic
  (:require [datomic.api :as d]))

(def schema (load-file "resources/datomic/schema.edn"))

(def uri "datomic:mem://film-list")

(d/delete-database uri)
(d/create-database uri)


(def conn (d/connect uri))

(d/transact conn schema)

(defn load-data[data]
  (doseq[d data]
    (let [d (conj '[] (conj d {:db/id (d/tempid :db.part/user)}))]
      (d/transact conn d))))


(def name (:filmlist/name (d/entity (d/db conn) (ffirst (d/q '[:find ?e ?name :where [?e :filmlist/name ?name]] (d/db conn))))))
