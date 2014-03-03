(ns filmlist.name)

(def f "World.War.Z.2013.720p.BluRay.x264.YIFY.mp4")

(def words (map #(.toLowerCase %)  (re-seq #"\w+" f)))

;; Need to look up these words and exclude ones that are not proper words.
;; also need to determine if any of them are years
(def years #{"2000" "2001" "2002" "2003" "2004" "2005" "2006" "2007" "2008" "2009" "2010" "2011" "2012" "2013" "2014"})

(def dict   (into #{}  (.split (slurp "/home/jennifer/2of12inf.txt") "\\r\\n")))


(def look-up-words (filter #(not (nil? %)) (map #(dict %) words)))

(def look-up-years (filter #(not (nil? %)) (map #(years %) words)))

(def search-str (clojure.string/join " " (flatten  (conj look-up-words look-up-years))))
