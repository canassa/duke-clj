(ns duke-clj.core
  (:import (javax.swing JFrame)))

(def window-width 600)
(def window-height 600)

(def sector {
             :walls [
                     {:coord [[100 100] [100 300]]}
                     {:coord [[100 300] [300 300]]}
                     {:coord [[300 300] [300 100]]}
                     {:coord [[300 100] [100 100]]}]

             :holes [
                     ]})


(defn get-buffer []
  (.getBufferStrategy
    (doto (new JFrame "Clojure")
      (.setVisible true)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize (java.awt.Dimension. window-width window-height))
      (.createBufferStrategy 2))))  ; Double buffering


(defn draw-sector [sector buffer]
  (let [graphics (.getDrawGraphics buffer)]
    ; Clear background
    (.setColor graphics java.awt.Color/BLACK)
    (.fillRect graphics 0 0 window-width window-height)

    (.setColor graphics java.awt.Color/WHITE)

    (doseq [[[x1 y1] [x2 y2]] (map :coord (:walls sector))]
      (.drawLine graphics x1 y1 x2 y2))

    (.fillOval graphics 200 200 50 50)

    ; It is best to dispose() a Graphics object when done with it.
    (.dispose graphics))

  ; Shows the contents of the backbuffer on the screen.
  (.show buffer))


(defn -main []
  (draw-sector sector (get-buffer)))
