package by.bsuir.wavegen.implementation;

import by.bsuir.formula.implementation.TriangleFormula;
import by.bsuir.wavegen.FrequencyWaveGenerator;

public class TriangleWaveGenerator extends FrequencyWaveGenerator {
    public TriangleWaveGenerator(double frequency) {
        super(frequency);
        this.formula = new TriangleFormula();
    }
}
