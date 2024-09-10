package by.bsuir.modulation.implementation;

import by.bsuir.formula.implementation.TriangleFormula;
import by.bsuir.modulation.Modulator;

public class TriangleModulator extends Modulator {
    public TriangleModulator(double frequency, double amplitude) {
        super(frequency, amplitude);
        this.formula = new TriangleFormula();
    }
}
