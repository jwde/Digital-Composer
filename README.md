# Digital-Composer
Melody composition AI for the web.

<h3>Concept</h3>
The goal of this project is to create an AI which composes music based on human feedback. The source of this human feedback will be a web frontend which synthesizes and plays compositions. Users will hear two compositions and decide which one they like more. This information will be used to train the AI to compose more pleasing music.<br>
The purpose of using human feedback instead of existing compositions as the source for training the AI is to see if this method allows the algorithm to come up with more original compositions than it could if it were trying to imitate existing music.<br>

<h3>Progress</h3>
This project is in the proof of concept stage.

<h3>Proof of concept:</h3>
Very restricted -- 1 note at a time, fixed time signature/key per melody,
song (for now just a melody) generator module will use <a href="https://en.wikipedia.org/wiki/Neuroevolution_of_augmenting_topologies">NEAT</a> to generate the steps of the song.
Each step is either a note in the scale or a rest.


<h3>Checklist</h3>
Simple melody-synthesizer frontend -- done<br>
Backend skeleton -- done<br>
Backend learning algorithm -- in progress<br>
Frontend feedback -- in progess<br>
Gene database -- in progress<br>
