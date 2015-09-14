(ns digitalcomposer.generator.song)

(defn makeSteps
  "Generates the notes in a song"
  [bpm songKey]
  [1 0 2 0 3 0 4 0 5 0 6 0 7 0 8 0]
)

;; Song format: Melody only (for now)
;; {
;;   token : (unique song id),
;;   bpm : 128,
;;   instrument : [1, .6, .2, .05] (harmonic amplitudes)
;;   songKey : {base: 0-11 (starting at C), notes: [0, 2, 4, 5, 7, 9, 11] (notes in scale)},
;;   steps : [], // where each step is a rest (0) or a # 1 - 21 (7 note scales, 3 octaves)
;; }
(defn getSong
  "Generates a song"
  []
    (let [token 0 bpm 128 instrument [1 0.2 0 0.1 0.3 0.1] 
          songKey {:base 0 :notes [0 2 4 5 7 9 11]}]
      {:token token :bpm bpm :instrument instrument :songKey songKey
       :steps (makeSteps bpm songKey)}
    )
)
