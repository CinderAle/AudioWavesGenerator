package by.bsuir.wavegen;

public abstract class WaveGenerator implements IWaveGenerator {
    public int sampleRate;
    private static final int DEFAULT_SAMPLE_RATE = 44100;

    public WaveGenerator() {
        this.sampleRate = DEFAULT_SAMPLE_RATE;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }
}
