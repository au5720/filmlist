(ns filmlist.file)

(def dir "/home/jennifer/Videos")

(defn fileDetails[file]
  (when (.isFile file)
    (let [fName (.getName file)
          fSize (.length file)
          fURI (.toURI file)
          modified (.lastModified file)
          hash (.hashCode file)]
      {:filmlist/name fName
       :filmlist/size fSize
       :filmlist/uri fURI
       :filmlist/modified modified
       :filmlist/hash hash})))

(defn dir-files[dir]
  (let [files (-> dir
                  (java.io.File.)
                  (.listFiles))]
    (map fileDetails files)))




