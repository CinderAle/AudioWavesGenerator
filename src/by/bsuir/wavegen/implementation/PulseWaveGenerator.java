package by.bsuir.wavegen.implementation;

import by.bsuir.formula.implementation.PulseFormula;
import by.bsuir.wavegen.FrequencyWaveGenerator;

public class PulseWaveGenerator extends FrequencyWaveGenerator {

    public PulseWaveGenerator(double frequency, double dutyCycle) {
        super(frequency);
        this.formula = new PulseFormula(dutyCycle);
    }

}
