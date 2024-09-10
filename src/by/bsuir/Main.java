package by.bsuir;

import by.bsuir.wavegen.implementation.SinusoidWaveGenerator;
import by.bsuir.waveplay.WavePlayer;

public class Main {
    public static void main(String[] args) {
        WavePlayer player = new WavePlayer(44100,new SinusoidWaveGenerator(900));
        player.addWave(new SinusoidWaveGenerator(300));
        player.play(2);
    }
}
