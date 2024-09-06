package by.bsuir.wavegen.implementation;

import by.bsuir.wavegen.WaveGenerator;

import java.util.function.Supplier;

public class PulseWaveGenerator extends WaveGenerator {

    private final double dutyCycle;

    public PulseWaveGenerator(double dutyCycle) {
        this.dutyCycle = dutyCycle;
    }

    @Override
    public double[] generateWave(int totalSamples, Supplier<Double> frequencyFunction) {
        double[] wave = new double[totalSamples];
        double fraction = 2 * Math.PI * frequencyFunction.get() / this.sampleRate;

        for (int i = 0; i < totalSamples; i++) {
            double value = (fraction * i) % (2 * Math.PI) / (2 * Math.PI);
            wave[i] = (value <= this.dutyCycle ? 1 : -1);
        }

        return wave;
    }
}
