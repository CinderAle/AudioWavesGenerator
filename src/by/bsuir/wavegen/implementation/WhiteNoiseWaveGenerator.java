package by.bsuir.wavegen.implementation;

import by.bsuir.wavegen.WaveGenerator;

import java.util.Random;

public class WhiteNoiseWaveGenerator extends WaveGenerator {

    @Override
    public double[] generateWave(int totalSamples) {
        double[] buffer = new double[totalSamples];
        Random random = new Random();

        for (int i = 0; i < totalSamples; i++) {
            buffer[i] = random.nextDouble(-1, 1);
        }

        return buffer;
    }
}
