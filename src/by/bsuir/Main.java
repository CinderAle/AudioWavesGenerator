package by.bsuir;

import by.bsuir.modulation.implementation.SinusoidModulator;
import by.bsuir.wavegen.implementation.PulseWaveGenerator;
import by.bsuir.waveplay.WavePlayer;

public class Main {
    public static void main(String[] args) {
        WavePlayer player = new WavePlayer(44100,new PulseWaveGenerator(400, 0.5));
        player.playFM(2, new SinusoidModulator(2, 1));
    }
}
