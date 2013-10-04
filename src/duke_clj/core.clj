(ns duke-clj.core
  (:import (javax.swing JFrame)))

(def window-width 600)
(def window-height 600)

(def sector {
    :walls [
        {:coord [0 0]}
        {:coord [0 1]}
        {:coord [1 1]}
        {:coord [1 0]}]

    :holes [
    ]})

(seq (map :coord (:walls sector)))


(let [coords (map :coord (:walls sector))]
  (reduce
   (fn [w1 w2] (println w1) w2)
   (last coords)
   coords))


(defn get-frame []
  (let [frame (new JFrame "Clojure")]
    (doto frame
      (.setVisible true)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize (java.awt.Dimension. window-width window-height)))
    frame))


(def frame (get-frame))

(defn draw-sector [sector frame]
  (let [buffer (.getBufferStrategy frame)
        graphics (.getDrawGraphics buffer)]
    (doto graphics
      ; Clear background
      (.setColor java.awt.Color/BLACK)
      (.fillRect 0 0 window-width window-height)

      (.setColor java.awt.Color/WHITE)
      (.fillOval 200 200 50 50)

      ; It is best to dispose() a Graphics object when done with it.
      (.dispose))

    ; Shows the contents of the backbuffer on the screen.
    (.show buffer)))


(draw-sector sector frame)

(defn -main []
  (get-frame))
