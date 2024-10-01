package by.bsuir.modulation;

import by.bsuir.formula.IFormula;

public abstract class Modulator implements IModulator {
    protected double frequency;
    protected double amplitude;
    protected double result;
    protected IFormula formula;

    public Modulator(double frequency, double amplitude) {
        this.frequency = frequency;
        this.amplitude = amplitude;
        this.result = 0.0;
    }

    @Override
    public double getModulatedValue(int sample, int sampleRate) {
        this.result += getRelativeValue(sample, sampleRate);
        return amplitude * result * (2 * Math.PI * frequency / sampleRate);
    }

    @Override
    public double getRelativeValue(int sample, int sampleRate) {
        return this.formula.calculate(2 * Math.PI * frequency * sample / sampleRate);
    }
}
