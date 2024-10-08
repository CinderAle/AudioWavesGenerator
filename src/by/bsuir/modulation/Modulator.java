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
        return amplitude * formula.calculate(2 * Math.PI * frequency * sample / sampleRate);
    }

    @Override
    public double getRelativeValue(int sample, int sampleRate) {
        return (1 + amplitude * this.formula.calculate(2 * Math.PI * frequency * sample / sampleRate + Math.PI)) / (2 * amplitude);
    }
}
