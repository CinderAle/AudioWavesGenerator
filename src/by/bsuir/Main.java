package by.bsuir;

import by.bsuir.modulation.implementation.SawtoothModulator;
import by.bsuir.wavegen.implementation.TriangleWaveGenerator;
import by.bsuir.waveplay.WavePlayer;

public class Main {
    public static void main(String[] args) {
        WavePlayer player = new WavePlayer(44100,new TriangleWaveGenerator(400));
        player.playFM(2, new SawtoothModulator(2, 1));
    }
}
