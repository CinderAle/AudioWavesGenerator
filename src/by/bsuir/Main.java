package by.bsuir;

import by.bsuir.filter.HighFrequencyFilter;
import by.bsuir.filter.LowFrequencyFilter;
import by.bsuir.fourier.FastFourierTransformer;
import by.bsuir.fourier.IFourierTransformer;
import by.bsuir.wavegen.implementation.SinusoidWaveGenerator;
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

        double[] wave = new SinusoidWaveGenerator(660).generateWave(1024);
        IFourierTransformer transformer = new FastFourierTransformer();
        transformer.calculateSpectrum(wave);
        double[] amplitudeSpectrum = transformer.getAmplitudeSpectrum();
        double[] frequencySpectrum = transformer.getPhaseSpectrum();

        // ВЧ и НЧ одновременно дают полосовой фильтр
        double[] restored = transformer.restoreSignal();
        double[] filtered = new HighFrequencyFilter(new LowFrequencyFilter(wave).filter(25)).filter(10);

        XYSeries seriesAmplitude = new XYSeries("Amplitude spectrum");
        XYSeries seriesFrequency = new XYSeries("Phase spectrum");
        XYSeries seriesRestored = new XYSeries("Restored");
        XYSeries seriesFiltered = new XYSeries("Filtered");

        for (int i = 0; i < frequencySpectrum.length; i++) {
            seriesAmplitude.add(i, amplitudeSpectrum[i]);
            seriesFrequency.add(i, frequencySpectrum[i]);
            seriesRestored.add(i, restored[i]);
            seriesFiltered.add(i, filtered[i]);
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
