package by.bsuir.modulation.implementation;

import by.bsuir.formula.implementation.SinusoidFormula;
import by.bsuir.modulation.Modulator;

public class SinusoidModulator extends Modulator {
    public SinusoidModulator(double frequency, double amplitude) {
        super(frequency, amplitude);
        this.formula = new SinusoidFormula();
    }
}
