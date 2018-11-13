package speedtest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.function.Consumer;

/**
 * Class used to test the speed of an algorithm
 */
public class SpeedTest {

    public enum OutputFormat{
        Milliseconds,
        NanoSeconds,
    }

    private class Measure{
        public Integer size;
        public Double time;

        public Measure(Integer size, Double time){
            this.size = size;
            this.time = time;
        }
    }

    PrintStream output;
    OutputFormat format = OutputFormat.Milliseconds;
    ArrayList<Measure> measurements = new ArrayList<>();

    //Times that the algorithm needs to be repeated with a dataset to get the medium value
    int repeatTimes = 5;

    /**
     * Creates a test with the specified output
     * In the output specified will be written the results
     * of the tests in nanoseconds separated by the return
     * @param output
     */
    public SpeedTest(PrintStream output){
        this.output = output;
        measurements = new ArrayList<>();
    }

    /**
     * Performs the test with the specified algorithm and data feed
     * IT REQUIRES that the object returned from the data feed to be equal to the
     * object passed to the algorithm
     * IT DOES NOT check the validity of the algorithm
     * IT DOES NOT account for memory limits
     * IT DOES NOT perform checks on the type returned by the data
     * @param algorithm the algorithm that is being tested
     * @param feed
     */
    public void perform(Consumer<Object> algorithm, DataFeed feed){
        //Clear the data
        //measurements = new ArrayList<>();
        while(!feed.isFinished()){
            Integer dataSetSize = feed.dataSizeForTest();

            Object o = feed.nextDataSet();


            ArrayList<Long> executionTimes = new ArrayList<Long>();
            for (int i=1; i <= this.repeatTimes; i++) {
                long timeBeforeExecution = System.nanoTime();
                algorithm.accept(o);
                long timeAfterExecution = System.nanoTime();
                long timeElapsed = (timeAfterExecution - timeBeforeExecution);
                executionTimes.add(Long.valueOf(timeElapsed));
            }

            long sum = medianTime(executionTimes);
            Long mediumTimeElapsed = sum / executionTimes.size();

            this.logExecutionTime(mediumTimeElapsed, dataSetSize);
        }


    }

    /**
     * Calculate the median time
     * @param times
     * @return
     */
    private long medianTime(ArrayList<Long> times){
        long sum = 0;
        for (Long time: times) {
            sum += time.longValue();
        }
        return sum;
    }

    public void setRepeatTimes(int repeatNTimes){
        this.repeatTimes = repeatNTimes;
    }

    public void plotLastTest(){
        XYSeries dataSeries = readSeriesFromMeasurements();

        SwingUtilities.invokeLater(() -> {
            SpeedChart chart = new SpeedChart(dataSeries);
            chart.setVisible(true);
        });
    }

    private XYSeries readSeriesFromMeasurements(){
        XYSeries dataSeries = new XYSeries("Current Test");
        for (Measure element: this.measurements){
            dataSeries.add(element.size, Double.valueOf(element.time));
        }
        return dataSeries;
    }

    /**
     * Sets the format of the output, changing nanoseconds to milliseconds etc
     * @param outformat
     */
    public void setFormat(OutputFormat outformat){
        this.format = outformat;
    }

    /**
     * Log the execution time on the output following the
     * format specification
     * @param executionTime
     */
    private void logExecutionTime(long executionTime, Integer forDataSize){
        if(output == null){ return ; }

        Double value = 0.0;
        switch (this.format){
            case Milliseconds:
                value = Double.valueOf(executionTime) / 1000000;
                break;
            case NanoSeconds:
                value = Double.valueOf(executionTime);
                break;
        }

        Measure m = new Measure(forDataSize, value);
        measurements.add(m);

        this.output.print(forDataSize);
        this.output.print(",");
        this.output.println(value);
    }

    public static void plotFile(File inputFile) throws IOException {
        XYSeries dataSeries = readSeriesFromFile(inputFile);

        SwingUtilities.invokeLater(() -> {
            SpeedChart chart = new SpeedChart(dataSeries);
            chart.setVisible(true);
        });
    }

    public static void plotFiles(File[] inputFiles) throws IOException{
        XYSeriesCollection collection = new XYSeriesCollection();
        for (File element: inputFiles) {
            XYSeries dataSeries = readSeriesFromFile(element);
            collection.addSeries(dataSeries);
        }

        SwingUtilities.invokeLater(() -> {
            SpeedChart chart = new SpeedChart(collection);
            chart.setVisible(true);
        });
    }

    private static XYSeries readSeriesFromFile(File input) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(input));
        XYSeries dataSeries = new XYSeries(input.getName());
        String line;
        while((line = reader.readLine()) != null){
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            Integer inputSize = Integer.valueOf(tokenizer.nextToken());
            Double time = Double.valueOf(tokenizer.nextToken());

            dataSeries.add(inputSize, time);
        }
        return dataSeries;
    }
}
