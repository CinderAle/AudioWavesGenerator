package by.bsuir.wavegen;

public abstract class FrequencyWaveGenerator extends WaveGenerator {
    protected double frequency;

    public FrequencyWaveGenerator(double frequency) {
        super();
        this.frequency = frequency;
    }
}
