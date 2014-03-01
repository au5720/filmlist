(ns filmlist.core
  (:use [filmlist.datomic]
        [filmlist.file]))



;; Start datomic and load in the Data we have collected

(load-data (dir-files dir))


