package user.cb2109.musictranscription.tools;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import user.cb2109.musictranscription.audioin.format.wave.WaveFileAbstraction;
import user.cb2109.musictranscription.audioin.format.wave.WaveFrame;

import java.util.ArrayList;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
public class WaveGraph {

    private WaveFileAbstraction abstraction;

    public WaveGraph(WaveFileAbstraction abstraction) {
        this.abstraction = abstraction;
        initComponents();
    }

    private void initComponents() {
        final XYSeries dataR = new XYSeries("Right");
        final XYSeries dataL = new XYSeries("Left");
        addDataToSeries(abstraction, dataR, dataL);
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(dataR);
        dataset.addSeries(dataL);

        JFreeChart chart = ChartFactory.createXYLineChart("Music", "time", "amplitude", dataset, PlotOrientation.VERTICAL, true, true, false);

        final ChartFrame frame = new ChartFrame("a chart", chart);
        frame.setVisible(true);
    }

    private static void addDataToSeries(WaveFileAbstraction abstraction, XYSeries dataR, XYSeries dataL) {
        ArrayList<WaveFrame> frames = abstraction.getWaveFrames();
        double i = 0;
        for(WaveFrame f : frames) {
            dataL.add(i, f.getLeft());
            dataR.add(i, f.getRight());
            ++i;
        }
    }

}
