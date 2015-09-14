$(window).load(function() {
    var context = new AudioContext();

    /* master output gain channel */
    var masterGain = context.createGain();
    masterGain.gain.value = 1;
    masterGain.connect(context.destination);

    /* middle 3 piano octave frequencies */
    var pianoFreqs = [130.813, 138.591, 146.832, 155.563, 164.814, 174.614, 184.997, 195.998, 207.652, 220.000, 233.082, 246.942, 
    261.626, 277.183, 293.665, 311.127, 329.628, 349.228, 369.994, 391.995, 415.305, 440.000, 466.164, 493.883, 
    523.251, 554.365, 587.330, 622.254, 659.255, 698.456, 739.989, 783.991, 830.609, 880.000, 932.328, 987.767]

    /* memoize a function -- not currently in use */
    function memoize(func) {
        var res = {};
        return function() {
            var args = Array.prototype.slice.call(arguments);
            return (args in res) ? res[args] : 
                        (res[args] = func.apply(this, args));
        }
    }

    /* creates a simple sine wave oscillator with a frequency and amplitude,
       connected to the masterGain channel */
    function sineWave(freq, amp) {
        this.osc = context.createOscillator();
        this.osc.type = 'sine';
        this.osc.frequency.value = freq;
        this.osc.start(0);
        this.gain = context.createGain();
        this.gain.gain.value = 0;
        this.gain.connect(masterGain);
        this.osc.connect(this.gain);
        this.amp = amp;
        this.play = function() {
            this.gain.gain.value = this.amp;
        }
        this.pause = function() {
            this.gain.gain.value = 0;
        }
    }

    /* creates a wave generator composed of some fundamental frequency and some of
       its harmonics at specified amplitudes */
    function compoundWave(freq, harmonicAmps) {
        this.sineWaves = [];
        for (var i = 0; i < harmonicAmps.length; ++i) {
            this.sineWaves.push(new sineWave(freq * (i + 1), harmonicAmps[i]));
        }
        this.play = function() {
            for (var i = 0; i < this.sineWaves.length; ++i) this.sineWaves[i].play();
        }
        this.pause = function () {
            for (var i = 0; i < this.sineWaves.length; ++i) this.sineWaves[i].pause();
        }
    }

    /* plays a song using the webaudio API */
    function playSong(bpm, instrument, songKey, steps) {
        if (steps.length == 0) return;
        var delay = 60000 / bpm;
        var note;
        if (steps[0] != 0) {
            var noteIndex = (songKey.base + songKey.notes[(steps[0] - 1) % 7]);
            var octaveMul = Math.floor(Math.pow(2, (steps[0] - 1) / 7));
            var note = new compoundWave(pianoFreqs[noteIndex] * octaveMul, instrument);
            note.play();
        }
        setTimeout(function() {
            if (note != undefined) note.pause();
            playSong(bpm, instrument, songKey, steps.slice(1, steps.length));
        }, delay);
    }

    /* get a song from the server */
    $.ajax({
        url: "./song",
        success: function(resultJson) {
            var result = JSON.parse(resultJson);
            playSong(result["bpm"], result["instrument"], result["songKey"], result["steps"]);
        }
    });
});
