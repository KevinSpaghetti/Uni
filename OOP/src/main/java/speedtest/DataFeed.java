package speedtest;

import java.util.Random;
import java.util.function.Function;

public class DataFeed {

    private static final int UPPER_BOUND = 200000;

    protected Function<Integer, Object> dataGenerator;
    protected Function<Integer, Integer> dataSizeForNthTest;
    protected Integer numberOfTestsToPerform = 1;
    protected Integer numberOfTestsPerformed = 1;

    /**
     * The predefined types of DataFeed, common sets of data like array etc, different dimensions
     * array : the dimension of the largest array will be [number of tests * 10]
     * bigArray : the dimension of the array will be [number of tests * 500]
     */
    public enum Type{
        array,
        bigArray,
    }

    /**
     * Constructor for default {@code DataFeed} types
     * @param withType
     * @return
     */
    public static DataFeed forType(DataFeed.Type withType, Integer numberOfTests){
        switch (withType){
            case array:
                return arrayDataFeed(numberOfTests);
            case bigArray:
                return bigArrayDataFeed(numberOfTests);
        }
        return null;
    }

    private static DataFeed arrayDataFeed(Integer numberOfTests){
        //Generate and array of random numbers every time the function gets called
        return new DataFeed(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) {
                Integer[] array = new Integer[integer * 10];
                Random randomGenerator = new Random();
                for (int i = 0; i < array.length; i++) {
                    array[i] = randomGenerator.nextInt(UPPER_BOUND);
                }
                return array;
            }
        }, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 10;
            }
        }, numberOfTests);
    }

    private static DataFeed bigArrayDataFeed(Integer numberOfTests){
        //Generate and array of random numbers every time the function gets called
        return new DataFeed(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) {
                Integer[] array = new Integer[integer * 500];
                Random randomGenerator = new Random();
                for (int i = 0; i < array.length; i++) {
                    array[i] = randomGenerator.nextInt(UPPER_BOUND);
                }
                return array;
            }
        }, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 500;
            }
        }, numberOfTests);
    }

    /**
     * Create a DataFeed by passing a function that returns a set of data for
     * the Nth test
     * @param outputForNthTest
     */
    public DataFeed(Function<Integer, Object> outputForNthTest, Function<Integer, Integer> dataSizeForNthTest, Integer numberOfTests){
        this.dataGenerator = outputForNthTest;
        this.dataSizeForNthTest = dataSizeForNthTest;
        this.numberOfTestsToPerform = numberOfTests;
        this.numberOfTestsPerformed = 1;
    }

    /**
     * Call this method to check if there are anymore tests
     * @return true if the tests have all been performed
     */
    public boolean isFinished(){
        return this.numberOfTestsPerformed > this.numberOfTestsToPerform;
    }

    /**
     * Returns the next dataset as Object
     * @return the next dataset
     */
    public Object nextDataSet(){
        Object dataSet = this.dataGenerator.apply(this.numberOfTestsPerformed);
        this.numberOfTestsPerformed++;
        return dataSet;
    }

    /**
     * returns the current data size of the input to the algorithm
     * IT REQUIRES to be called before the nextDataSet method, since
     * the nextDataSet method skips directly to the next test
     * @return
     */
    public Integer dataSizeForTest(){
        return this.dataSizeForNthTest.apply(this.numberOfTestsPerformed);
    }

}
