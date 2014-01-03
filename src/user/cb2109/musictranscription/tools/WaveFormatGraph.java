package user.cb2109.musictranscription.tools;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import user.cb2109.musictranscription.audioin.format.wave.WaveFileAbstraction;
import user.cb2109.musictranscription.audioin.format.wave.WaveFrame;

import java.util.ArrayList;

/**
 * Author: Christopher Bates
 * Date: 01/12/13
 */
public class WaveFormatGraph {

    public static void draw(WaveFileAbstraction abstraction) {

        DefaultCategoryDataset data = new DefaultCategoryDataset();
        addRightDataToSeries(abstraction, data);


        JFreeChart chart = ChartFactory.createLineChart("drawing", "time", "amplitude", data, PlotOrientation.HORIZONTAL, true, false, false);

        ChartFrame frame = new ChartFrame("The frame", chart);
        frame.pack();
        frame.setVisible(true);
    }

    private static void addRightDataToSeries(WaveFileAbstraction abstraction, DefaultCategoryDataset series) {

        ArrayList<WaveFrame> frames = abstraction.getFrames();
        int i = 0;
        for(WaveFrame f : frames) {
            series.addValue((Number) f.getRight(), i, ++i);
        }


    }

}
