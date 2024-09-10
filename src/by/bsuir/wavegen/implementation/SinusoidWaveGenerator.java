package by.bsuir.wavegen.implementation;

import by.bsuir.formula.implementation.SinusoidFormula;
import by.bsuir.wavegen.FrequencyWaveGenerator;

public class SinusoidWaveGenerator extends FrequencyWaveGenerator {
    public SinusoidWaveGenerator(double frequency) {
        super(frequency);
        this.formula = new SinusoidFormula();
    }

}

