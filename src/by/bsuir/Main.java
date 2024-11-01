package by.bsuir;

import by.bsuir.fourier.FastFourierTransformer;
import by.bsuir.wavegen.implementation.WhiteNoiseWaveGenerator;
import by.bsuir.waveplay.WavePlayer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        int discrete = 32768;
        double[] wave = new WhiteNoiseWaveGenerator().generateWave(discrete);

        FastFourierTransformer transformer = new FastFourierTransformer();
        transformer.calculateSpectrum(wave);
        transformer.highPassFilter(10000, discrete);
        double[] amplitudeSpectrum = transformer.getAmplitudeSpectrum();
        double[] frequencySpectrum = transformer.getPhaseSpectrum();
        double[] restored = transformer.restoreSignal();

        WavePlayer player = new WavePlayer(discrete);
        player.playWave(restored);

        XYSeries seriesAmplitude = new XYSeries("Amplitude spectrum");
        XYSeries seriesFrequency = new XYSeries("Phase spectrum");
        XYSeries seriesRestored = new XYSeries("Original");
        XYSeries seriesFiltered = new XYSeries("Filtered");

        for (int i = 0; i < frequencySpectrum.length; i++) {
            seriesAmplitude.add(i, amplitudeSpectrum[i]);
            seriesFrequency.add(i, frequencySpectrum[i]);
            seriesRestored.add(i, wave[i]);
            seriesFiltered.add(i, restored[i]);
        }
        XYSeriesCollection collectionAmplitude = new XYSeriesCollection(seriesAmplitude);
        JFreeChart chartAmplitude = ChartFactory.createXYLineChart(null, "x", "y", collectionAmplitude, PlotOrientation.VERTICAL, true, true, false);

        XYSeriesCollection collectionFrequency = new XYSeriesCollection(seriesFrequency);
        JFreeChart chartFrequency = ChartFactory.createXYLineChart(null, "x", "y", collectionFrequency, PlotOrientation.VERTICAL, true, true, false);

        XYSeriesCollection collectionRestored = new XYSeriesCollection(seriesRestored);
        JFreeChart chartRestored = ChartFactory.createXYLineChart(null, "x", "y", collectionRestored, PlotOrientation.VERTICAL, true, true, false);

        XYSeriesCollection collectionFiltered = new XYSeriesCollection(seriesFiltered);
        JFreeChart chartFiltered = ChartFactory.createXYLineChart(null, "x", "y", collectionFiltered, PlotOrientation.VERTICAL, true, true, false);

        JFrame frame = new JFrame("Spectrum");
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(new ChartPanel(chartAmplitude));
        frame.getContentPane().add(new ChartPanel(chartFrequency));
        frame.getContentPane().add(new ChartPanel(chartRestored));
        frame.getContentPane().add(new ChartPanel(chartFiltered));

        frame.setSize(1400, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
