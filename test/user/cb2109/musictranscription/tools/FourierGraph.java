package user.cb2109.musictranscription.tools;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultKeyedValues2DDataset;

import java.awt.Dimension;

/**
 * Author: Christopher Bates
 * Date: 07/01/14
 */
public class FourierGraph {

    private double[] data;
    private static final double THRESHOLD = 20000;

    public FourierGraph(double[] data) {
        this.data = data;
        initialiseComponents();
    }

    public void initialiseComponents() {

        DefaultKeyedValues2DDataset dataSet = new DefaultKeyedValues2DDataset();
        for(int i = 0; i < data.length; i++) {
            if(data[i] > THRESHOLD) {
                dataSet.addValue((Number) data[i], 0, i);
            }
        }

        JFreeChart chart = ChartFactory.createBarChart("Furier plot", "Frequency", "Value", dataSet, PlotOrientation.VERTICAL, false, false, false);

        final ChartFrame frame = new ChartFrame("a chart", chart);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setVisible(true);
    }

}
