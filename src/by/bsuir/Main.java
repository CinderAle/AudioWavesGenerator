package by.bsuir;

import by.bsuir.modulation.implementation.SawtoothModulator;
import by.bsuir.wavegen.implementation.SawtoothWaveGenerator;
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
        WavePlayer player = new WavePlayer(44100,new SawtoothWaveGenerator(220));
        //player.addWave(new SawtoothWaveGenerator(262));
        //player.addWave(new SinusoidWaveGenerator(1000));
        //player.addWave(new WhiteNoiseWaveGenerator());
        //player.play(2);
        //player.playFM(4, new SinusoidModulator(0.25, 1));
        player.playAM(4, new SawtoothModulator(0.5, 1));

        XYSeries series = new XYSeries("Graph");
        double[] sine = new SawtoothWaveGenerator(440).generateAMWave(88200, new SawtoothModulator(0.5, 1));
        //double[] sine = new SinusoidWaveGenerator(5).generateWave(88200);
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
