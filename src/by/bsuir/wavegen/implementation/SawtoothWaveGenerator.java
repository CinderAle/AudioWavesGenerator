package by.bsuir.wavegen.implementation;

import by.bsuir.formula.implementation.SawtoothFormula;
import by.bsuir.wavegen.FrequencyWaveGenerator;

public class SawtoothWaveGenerator extends FrequencyWaveGenerator {
    public SawtoothWaveGenerator(double frequency) {
        super(frequency);
        this.formula = new SawtoothFormula();
    }

}
