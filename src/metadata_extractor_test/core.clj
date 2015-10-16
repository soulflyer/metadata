(ns metadata-extractor-test.core
  (:import com.drew.metadata.Metadata
           com.drew.metadata.Directory
           com.drew.metadata.Tag
           com.drew.imaging.ImageMetadataReader))

(def testfile "/Users/iain/Pictures/Published/fullsize/2015/09/01-Dragon/IMG_6666.jpg")
(def file (java.io.File. testfile))

file
;; (def metadata (Metadata.))
;; metadata

(def imagedata (ImageMetadataReader/readMetadata file))
imagedata

(seq (.getDirectories imagedata))
(map (fn [n] (.getTags n)) (.getDirectories imagedata))
(pprint (seq (map (fn [n] (.getTags n)) (.getDirectories imagedata))))

(def listtags (seq (map (fn [n] (.getTags n)) (.getDirectories imagedata))))
listtags
(map (fn [n] (.getTagName n)) listtags)

(defn getmeta
  [file]
  (let [imagedata (ImageMetadataReader/readMetadata file)]
    (map (fn [dir] (.getTags dir)) (.getDirectories imagedata))))
