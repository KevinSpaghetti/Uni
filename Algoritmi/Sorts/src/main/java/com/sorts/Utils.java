package com.sorts;

import java.util.Random;

public class Utils {

    public static Integer maxIndexFor(Integer[] array, Integer lowerBound, Integer upperBound){
        assert(array != null);
        assert(lowerBound >= 0);
        assert(lowerBound < array.length);
        assert(upperBound >= 0);
        assert(upperBound < array.length);

        Integer maxIndex = lowerBound;
        for (int i = lowerBound + 1; i <= upperBound; i++) {
            if(array[i] > array[maxIndex]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void printArray(Integer[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print( array[i] +  " ");
        }
        System.out.println("");
    }

    public static Integer[] randomArrayOfSize(Integer size){
        Integer[] array = new Integer[size];
        Random generator = new Random();
        for (int i = 0; i < size; i++){
            array[i] = generator.nextInt(20000);
        }
        return array;
    }

}
