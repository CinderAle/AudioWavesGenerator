package by.bsuir.wavegen.implementation;

import by.bsuir.wavegen.WaveGenerator;

import java.util.function.Supplier;

public class SawtoothWaveGenerator extends WaveGenerator {
    @Override
    public double[] generateWave(int totalSamples, Supplier<Double> frequencyFunction) {
        double frequency = frequencyFunction.get();
        double[] wave = new double[totalSamples];
        double fraction = 2 * Math.PI * frequency / totalSamples;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = 1 / Math.PI * (((fraction * i + Math.PI) % (2 * Math.PI)) - Math.PI);
        }

        return wave;
    }
}
