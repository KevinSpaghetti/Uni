package com.sorts;

import java.util.Random;

public class Utils {

    public static Integer maxIndexFor(Integer[] array, Integer lowerBound, Integer upperBound) {
        assert (array != null);
        assert (lowerBound >= 0);
        assert (lowerBound < array.length);
        assert (upperBound >= 0);
        assert (upperBound < array.length);

        Integer maxIndex = lowerBound;
        for (int i = lowerBound + 1; i <= upperBound; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void printArray(Number[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printMatrix(Integer[][] matrix) {
        Integer rowCount = matrix.length;
        Integer columnCount = matrix[0].length;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");

        }
    }

    /**
     * Builds a matrix by putting every digit in a colums
     * --|--
     *  1| 2
     *  0| 1
     *  is 12 and 1 with 2 digits
     * @param input
     * @param digits
     * @return
     */
    public static Integer[][] buildDigitMatrix(Integer[] input, Integer digits) {
        Integer[][] output = new Integer[input.length][digits];
        Integer rowCount = input.length;
        Integer columnCount = digits;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                output[i][j] =  input[i] / (int) Math.pow(10, columnCount - j - 1);
                input[i] -= output[i][j] * (int) Math.pow(10, columnCount - j - 1);
            }
        }

        printMatrix(output);
        return output;
    }

    public static Integer max(Integer[] array){
        Integer max = array[0];
        for (int i = 0; i < array.length; i++) {
            if(array[i] > max) max = array[i];
        }
        return max;
    }

    public static Integer[] randomArrayOfSize(Integer size){
        Integer[] array = new Integer[size];
        Random generator = new Random();
        for (int i = 0; i < size; i++){
            array[i] = generator.nextInt(20000);
        }
        return array;
    }

    public static Integer[] randomArrayOfSizeBounded(Integer size, Integer from, Integer to){
        Integer[] array = new Integer[size];
        Random generator = new Random();
        for (int i = 0; i < size; i++){
            array[i] = generator.nextInt(to) + from;
        }
        return array;
    }

    public static Double[] randomArrayOfSizeConstrained(Integer size, Double from, Double to){
        Double[] array = new Double[size];
        Random generator = new Random();
        for (int i = 0; i < size; i++){
            array[i] = generator.nextDouble();
        }
        return array;
    }
}
