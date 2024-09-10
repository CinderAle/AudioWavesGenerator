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
}
