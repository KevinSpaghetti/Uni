package speedtest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class SpeedChart extends JFrame {

    XYSeries dataSeries;

    public SpeedChart(XYSeries series){
        this.dataSeries = series;

        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);

        JFreeChart chart = createChart(collection);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public SpeedChart(XYSeriesCollection collection){
        JFreeChart chart = createChart(collection);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static JFreeChart createChart(XYSeriesCollection dataset){

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Chart",
                "Data size",
                "Time consumed",
                dataset
        );

        return chart;
    }
}
