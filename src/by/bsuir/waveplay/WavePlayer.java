package by.bsuir.waveplay;

import by.bsuir.modulation.Modulator;
import by.bsuir.wavecollection.WaveCollection;
import by.bsuir.waveconverter.WaveByteConverter;
import by.bsuir.wavegen.WaveGenerator;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class WavePlayer implements IWavePlayer, IModulatedPlayer {

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
        playWave(wave);
    }

    @Override
    public void playWave(double[] wave) {
        byte[] digitWave = WaveByteConverter.convert(wave, 2);
        AudioFormat audioFormat = new AudioFormat(sampleRate, 16, 1, true, false);

        try(SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(audioFormat)) {
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
            sourceDataLine.write(digitWave, 0, digitWave.length);
            AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(digitWave), audioFormat, digitWave.length), AudioFileFormat.Type.WAVE, new File("D:/images/test.wav"));
            sourceDataLine.drain();
        }
        catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            System.out.println("E");
        }
    }

    @Override
    public void addWave(WaveGenerator waveGenerator) {
        this.waves.add(waveGenerator);
    }

    @Override
    public void playAM(double duration, Modulator modulator) {
        double[] wave = this.waves.getAMWaves(duration, modulator);
        playWave(wave);
    }

    @Override
    public void playFM(double duration, Modulator modulator) {
        double[] wave = this.waves.getFMWaves(duration, modulator);
        playWave(wave);
    }
}
