package by.bsuir;

import by.bsuir.modulation.implementation.SinusoidModulator;
import by.bsuir.wavegen.implementation.SinusoidWaveGenerator;
import by.bsuir.waveplay.WavePlayer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WavePlayer player = new WavePlayer(44100,new SinusoidWaveGenerator(400));
        //player.play(2);
        //player.playFM(2, new PulseModulator(1, 0.25, 0.25));
        player.playAM(2, new SinusoidModulator(0.25, 2));

        XYSeries series = new XYSeries("Graph");
        double[] sine = new SinusoidWaveGenerator(5).generateAMWave(88200, new SinusoidModulator(2, 1));
        for(int i = 0; i < sine.length; i++){
            series.add(i, sine[i]);
        }
        XYSeriesCollection collection = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(null, "x", "y", collection, PlotOrientation.VERTICAL, true, true, false);

        try {
            ChartUtilities.saveChartAsPNG(new File("D:/images/chart.png"), chart, 1280, 720);
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
