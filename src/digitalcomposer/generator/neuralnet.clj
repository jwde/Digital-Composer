(ns digitalcomposer.generator.neuralnet
 (:use [core.matrix])
; (:use [enclog.nnets]
;       [enclog.training]
;       [enclog.normalization]
;       [enclog.util])
; (use '[cemerick.pomegranate :only (add-dependencies)])
; (add-dependencies :coordinates '[[enclog "0.6.3"]]
;                   :repositories (merge cemerick.pomegranate.aether/maven-central
;                       {"clojars" "http://clojars.org/repo"}))
; (use '[enclog nnets training])
 )

;; a simple Jordan network with one hidden layer:
;; inputs: randomized song-specific vector (inspiration),
;;         most recent 10 notes, or the tonic
;; output: next note

(defn activation [x] (Math/tanh x))
(defn dactivation [x] (- 1.0 (* y y)))

(defn propagateLayer [prev weights]
 (mapv activation (mapv #(reduce + %) (* prev (transpose weights)))))

(defn propagateNetwork [in hidden out]
 (propagateLayer (propagateLayer in hidden) out)
)

(def inputLen 10)
(def hiddenLen 5)
(def outLen 1)
(def initialWeightRange 2)

(defn lazyRandomVec
 "Returns a lazy list of random vectors in [-initialWeightRange, +initialWeightRange] of length n"
  [n]
  (repeatedly #(take n (repeatedly 
    #(- initialWeightRange (rand (* 2 initialWeightRange))))))
)

(defn makeGene
 "Makes a new randomized gene, consisting of hidden and output weights"
 (let [hidden (take inputLen (lazyRandomVec hiddenLen)) 
       out (take hiddenLen ()lazyRandomVec outLen)] (:hidden hidden :out out)) 
)
