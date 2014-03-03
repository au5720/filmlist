(ns filmlist.name)

;; http://deanclatworthy.com/imdb/?q=world+war&year=2013

(def f "World.War.Z.2013.720p.BluRay.x264.YIFY.mp4")

(def f  "The.Last.Airbender.2010.1080p.BrRip.x264.YIFY.mp4")

;; Need to look up these words and exclude ones that are not proper words.
;; also need to determine if any of them are years
(def years #{"2000" "2001" "2002" "2003" "2004" "2005" "2006" "2007" "2008" "2009" "2010" "2011" "2012" "2013" "2014"})

(def dict (into #{} (.split (slurp "/home/jennifer/2of12inf.txt") "\\r\\n")))

(defn make-words [f]
  (map #(.toLowerCase %)  (re-seq #"\w+" f)))

(defn look-up-words [words]
  (filter #(not (nil? %)) (map #(dict %) words)))

(defn look-up-years [words]
  (filter #(not (nil? %)) (map #(years %) words)))

(defn query-api[f]
  (let [q-api "http://deanclatworthy.com/imdb/?q="
        words (make-words f)
        title-words (look-up-words words)
        str1 (clojure.string/join "+" title-words)
        query (str q-api str1)
        y (first  (look-up-years words))]
    (if y
      (str query "&year=" y)
      query)))
