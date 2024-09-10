package by.bsuir.modulation.implementation;

import by.bsuir.formula.implementation.SawtoothFormula;
import by.bsuir.modulation.Modulator;

public class SawtoothModulator extends Modulator {
    public SawtoothModulator(double frequency, double amplitude) {
        super(frequency, amplitude);
        this.formula = new SawtoothFormula();
    }
}
