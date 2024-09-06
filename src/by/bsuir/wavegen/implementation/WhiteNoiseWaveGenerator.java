package by.bsuir.wavegen.implementation;

import by.bsuir.wavegen.WaveGenerator;

import java.util.function.Supplier;

public class WhiteNoiseWaveGenerator extends WaveGenerator {

    @Override
    public double[] generateWave(int totalSamples, Supplier<Double> frequencyFunction) {
        double[] buffer = new double[totalSamples];

        for (int i = 0; i < totalSamples; i++) {
            buffer[i] = frequencyFunction.get();
        }

        return buffer;
    }
}
