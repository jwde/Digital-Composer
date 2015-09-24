(ns digitalcomposer.generator.neuralnet
 (:use [enclog.nnets]
       [enclog.training]
       [enclog.normalization]
       [enclog.util])
; (use '[cemerick.pomegranate :only (add-dependencies)])
; (add-dependencies :coordinates '[[enclog "0.6.3"]]
;                   :repositories (merge cemerick.pomegranate.aether/maven-central
;                       {"clojars" "http://clojars.org/repo"}))
; (use '[enclog nnets training])
 )
;; an implementation of an Elman Recurrent Neural Network with weights seeded by a
;; genetic algorithm. Provides means to generate initial genes and parse genes.


(def initialWeightRange 2)

;; A gene is a flat structure containing all the weights needed to compute the output
;; of the neural network given an input
(defn makeGene
 "generates a random gene to be used for the neural network"
 [inSize outSize layerSize numLayers]
 (take (+ (* inSize layerSize) (+ (* (- numLayers 1) (* layerSize layerSize)) (* layerSize outSize))) 
  (repeatedly #(- initialWeightRange (rand (* 2 initialWeightRange))))))

(defn parseGene
 "takes in a flat gene and gives it the necessary structure"
 [gene inSize outSize layerSize numLayers]
 (let [inWeightsLen (* inSize layerSize) outWeightsLen (* outSize layerSize)
       midWeightsLen (* (- numLayers 1) (* layerSize layerSize))]
       
    let [inWeights (take inWeightsLen gene) outWeights (take-last outWeightsLen gene)
         midWeights (take (midWeightsLen) (drop (inWeightsLen) gene))]
         (vector ;; left off here -- maybe use LSTM instead of elman?))
 )
