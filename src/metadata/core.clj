(ns metadata.core
  (:require [clojure.string :as string])
  (:import com.drew.metadata.Metadata
           com.drew.metadata.Directory
           com.drew.metadata.Tag
           com.drew.imaging.ImageMetadataReader))

(defn gettags [taglist]
  (let [acc {}]
    (reduce
     (fn [a b]
       (assoc a (.getTagName b) (.getDescription b)))
     acc (.getTags taglist))))

(defn getmeta
  "Returns a hash-map containing the metadata from an image file.
  Expects a java.io.File and an optional sequence of field names."
  ([file]
   (let [imagedata (ImageMetadataReader/readMetadata file)
         acc {}]
     (reduce (fn [list tags]
               (merge list (gettags tags)))
             acc (.getDirectories imagedata))))
  ([file fields]
   (select-keys (getmeta file) fields)))

(defn keywords
  "returns a vector of strings containing the keywords extracted from the metadata"
  [metadata-map]
  (string/split (metadata-map "Keywords") #";"))

;; (def filename "/Users/iain/Pictures/Published/fullsize/2015/09/01-Dragon/IMG_6666.jpg")
;; (def file (java.io.File. filename))
;; (getmeta file)
;; (getmeta file ["Model"])
;; (keywords (getmeta file))
