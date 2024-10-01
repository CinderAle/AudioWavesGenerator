package by.bsuir.wavegen;

import by.bsuir.formula.IFormula;
import by.bsuir.modulation.Modulator;

public abstract class FrequencyWaveGenerator extends WaveGenerator {
    protected double frequency;
    protected double fraction;
    protected IFormula formula;

    public FrequencyWaveGenerator(double frequency) {
        super();
        this.frequency = frequency;
        this.fraction = 2 * Math.PI * frequency / this.sampleRate;
    }

    @Override
    public double[] generateWave(int totalSamples) {
        double[] wave = new double[totalSamples];
        double fi = 0;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = formula.calculate(fi);
            fi += fraction;
        }

        return wave;
    }

    public double[] generateAMWave(int totalSamples, Modulator modulator) {
        double[] wave = new double[totalSamples];
        double fi = 0;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = formula.calculate(fi) * (modulator.getRelativeValue(i, sampleRate));
            fi += fraction;
        }

        return wave;
    }

    public double[] generateFMWave(int totalSamples, Modulator modulator) {
        double[] wave = new double[totalSamples];
        double fi = 0;

        for (int i = 0; i < totalSamples; i++) {
            wave[i] = formula.calculate(fi);
            fi += fraction + modulator.getModulatedValue(i, this.sampleRate);
        }

        return wave;
    }
}