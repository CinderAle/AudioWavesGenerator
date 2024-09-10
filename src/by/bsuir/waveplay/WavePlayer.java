package by.bsuir.waveplay;

import by.bsuir.wavecollection.WaveCollection;
import by.bsuir.waveconverter.WaveByteConverter;
import by.bsuir.wavegen.WaveGenerator;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class WavePlayer implements IWavePlayer {

    private static final int DEFAULT_SAMPLE_RATE = 44100;

    private final WaveCollection waves;
    private final int sampleRate;

    public WavePlayer(int sampleRate) {
        this.sampleRate = sampleRate;
        this.waves = new WaveCollection(this.sampleRate);
    }

    public WavePlayer(int sampleRate, WaveGenerator waveGenerator) {
        this.sampleRate = sampleRate;
        this.waves = new WaveCollection(this.sampleRate, waveGenerator);
    }

    public WavePlayer(WaveGenerator waveGenerator) {
        this.sampleRate = DEFAULT_SAMPLE_RATE;
        this.waves = new WaveCollection(this.sampleRate, waveGenerator);
    }

    @Override
    public void play(double duration) {
        double[] wave = this.waves.getWave(duration);
        byte[] digitWave = WaveByteConverter.convert(wave, 2);
        AudioFormat audioFormat = new AudioFormat(sampleRate, 16, 1, true, false);

        try(SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(audioFormat)) {
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
            sourceDataLine.write(digitWave, 0, digitWave.length);
            sourceDataLine.drain();
        }
        catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addWave(WaveGenerator waveGenerator) {
        this.waves.add(waveGenerator);
    }
}
