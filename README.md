# Digital-Composer
Melody composition AI for the web.

<h3>Concept</h3>
The goal of this project is to create an AI which composes music based on human feedback. The source of this human feedback will be a web frontend which synthesizes and plays compositions. Users will hear two compositions and decide which one they like more. This information will be used to train the AI to compose more pleasing music.<br>
The purpose of using human feedback instead of existing compositions as the source for training the AI is to see if this method allows the algorithm to come up with more original compositions than it could if it were trying to imitate existing music.<br>
Existing attempts to compose music tend to fall into one of two categories:<br>
1.) Compose music completely free-form -- outputs lack theme <br>
2.) Improvise music on top of existing musical structure / chord progression -- requires existing structure<br>

Examples of attempts at music composition AI:<br>
<a href="http://people.idsia.ch/~juergen/blues/IDSIA-07-02.pdf">Using LSTM</a><br>
<a href="http://ttic.uchicago.edu/~oliwa/evomusart/oliwa2008evomusart.pdf">Using neural nets and probabilistic finite state machines</a><br>

<h3>Progress</h3>
This project is in the proof of concept stage.

<h3>Proof of concept:</h3>
To start off, we will create songs with 1 note at a time, using a fixed time signature and key. The backend learning algorithm will produce a sequence of notes in the key, which will be sent to the client in JSON format, then synthesized and played for the user.<br>
The user will compare two melodies and determine a winner. This will be used as feedback for the learning algorithm.<br>


<h3>Checklist</h3>
Simple melody-synthesizer frontend -- audio synthesizing, timing occasionally buggy<br>
Backend learning algorithm -- in progress -- simple jordan network implemented, but not getting useful results<br>
Frontend feedback -- not started<br>
Gene database -- not started<br>


<h3>Backend Learning Algorithm</h3>
<h4>Proposed architecture</h4>
The more successful attempts at music composition AIs have used recurrent neural networks. Candidates for this project include <a href="https://en.wikipedia.org/wiki/Long_short-term_memory">LSTM</a> and <a href="https://en.wikipedia.org/wiki/Recurrent_neural_network#Elman_networks_and_Jordan_networks">Elman / Jordan</a> networks. It may also be helpful to evolve structure as well as weights. This would add a lot of complexity, but might help us evolve better networks faster. For this, we should consider <a href="https://en.wikipedia.org/wiki/Neuroevolution_of_augmenting_topologies">NEAT</a><br>

With the planned approach of ranking melodies comparatively, one logical way to structure the algorithm would be a genetic algorithm to determine behavior of a recurrent neural net. Melody comparisons can contribute to <a href="https://en.wikipedia.org/wiki/Elo_rating_system">ELO</a> for each gene. Then, every so often we can progress to the next generation by breeding the most successful genes probabilistically by ELO ranking.<br>

<h4>Desired attributes of the algorithm:</h4>
<ul>
<li>Individual melodies composed by one gene must be distinct. Having a gene that can only generate one good melody isn't very interesting.</li>
<li>The algorithm must be aware of global structure so that it can create melodies with a cohesive theme</li>
<li>The set of possible outputs must be limited. If the algorithm has too much freedom we will never converge on favorable behavior. For example, it makes senes to limit the algorithm to generating notes within some number of half steps of a baseline.</li>
</ul>

<h4>Ideas</h4>
<ul>
<li>To achieve song-specific global theme without forcing the composition on top of an existing structure as part of the output, we can create a randomized vector for each composition that defines structure but, unlike a chord progression, is not incorporated in the output.</li>
<li>To limit the search space, we can restrict each song's notes to the notes in a chosen key, and snap timing to predefined steps.
</li>
</ul>
