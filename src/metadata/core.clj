(ns metadata.core
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
  Expects a java.io.File"
  [file]
  (let [imagedata (ImageMetadataReader/readMetadata file)
        acc {}]
    (reduce (fn [list tags]
              (merge list (gettags tags)))
            acc (.getDirectories imagedata))))

(def testfile "/Users/iain/Pictures/Published/fullsize/2015/09/01-Dragon/IMG_6666.jpg")
(def file (java.io.File. testfile))
(getmeta file)
