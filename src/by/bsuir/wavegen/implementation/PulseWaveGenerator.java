package by.bsuir.wavegen.implementation;

import by.bsuir.wavegen.FrequencyWaveGenerator;

public class PulseWaveGenerator extends FrequencyWaveGenerator {

    private final double dutyCycle;

    public PulseWaveGenerator(double dutyCycle, double frequency) {
        super(frequency);
        this.dutyCycle = dutyCycle;
    }

    @Override
    public double[] generateWave(int totalSamples) {
        double[] wave = new double[totalSamples];
        double fraction = 2 * Math.PI * this.frequency / this.sampleRate;

        for (int i = 0; i < totalSamples; i++) {
            double value = (fraction * i) % (2 * Math.PI) / (2 * Math.PI);
            wave[i] = (value <= this.dutyCycle ? 1 : -1);
        }

        return wave;
    }
}
