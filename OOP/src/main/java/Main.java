import speedtest.DataFeed;
import speedtest.SpeedTest;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        //testSpeedTest(500, 10);

        /*
        for (int i = 1; i <= 10; i++) {
            testSpeedTest(i * 100, 5);
        }
        */

        plotAllFilesInDatafilesDirectory();
    }

    private static void testSpeedTest(int algorithm_input_size, int test_number){
        OutputStream output = null;
        PrintStream outstream = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss" + algorithm_input_size);
            Date date = new Date();
            output = new FileOutputStream(new File( "datafiles/" + formatter.format(date)+ ".apd"));
            outstream = new PrintStream(output);
        }catch(FileNotFoundException e){
            System.err.println("File not found" + e);
            outstream = System.out;
        }

        SpeedTest test = new SpeedTest(outstream);
        DataFeed feed = new DataFeed(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer testNumber) {
                Integer[] array = new Integer[testNumber * algorithm_input_size];
                Random random = new Random();
                for (int i = 0; i < array.length; i++) {
                    array[i] = random.nextInt(200000);
                }
                return array;
            }
        }, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * algorithm_input_size;
            }
        }, test_number);
        Consumer<Object> algorithm = new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                Integer[] array = (Integer[]) o;
                algorithm(array);
            }
        };

        test.setFormat(SpeedTest.OutputFormat.Milliseconds);
        test.setRepeatTimes(3);

        test.perform(algorithm, feed);

        //test.plotLastTest();

        //plotAllFilesInDatafilesDirectory();
    }

    private static void plotAllFilesInDatafilesDirectory(){
        try {
            File[] filesInDatafilesDirectory = new File("datafiles").listFiles();
            SpeedTest.plotFiles(filesInDatafilesDirectory);
        }catch(IOException e){
            System.err.println("Error in reading file");
        }
    }

    private static void algorithm(Integer[] array){
        boolean flag = true;
        while(flag){
            flag = false;
            for (int i = 0; i < array.length - 1; i++) {
                if(array[i] > array[i+1]) {
                    int a = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = a;
                    flag = true;
                }
            }
        }

    }
}
