package com.sorts;

import sun.nio.ch.Util;

import java.util.Collections;
import java.util.Vector;

public class Sort{

    public static void bubble(Integer[] array){
        boolean swapped = true; //Stop when 0 swaps happen
        while(swapped){
            swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                if(array[i] > array[i + 1]){
                    int a = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = a;
                    swapped = true;
                }
            }
        }
    }

    public static void insertion(Integer[] array){
        for (int i = 1; i < array.length; i++) {
            int pivot = array[i];
            int j = i - 1;
            while( (j >= 0) && (array[j] > pivot) ){
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = pivot;
        }
    }

    public static void selection(Integer[] array){
        for (int i = array.length - 1; i > 0; i--) {
            Integer maxIndex = Utils.maxIndexFor(array, 0, i);
            Integer a = array[i];
            array[i] = array[maxIndex];
            array[maxIndex] = a;
        }
    }

    public static void merge(Integer[] array){
        merge(array, 0, array.length - 1);
    }

    private static void merge(Integer[] array, Integer lowerBound, Integer upperBound){
        if(lowerBound < upperBound){
            Integer medium = lowerBound + ((upperBound - lowerBound) / 2);
            merge(array, lowerBound, medium);
            merge(array, medium + 1,  upperBound);
            mergeArray(array, lowerBound, medium, upperBound);
        }
    }

    public static void mergeArray(Integer[] array, Integer lowerBound, Integer medium, Integer upperBound){

        Integer lowerBufferDimension = medium - lowerBound + 1;
        Integer upperBufferDimension = upperBound - medium;

        Integer[] lowerBuffer = new Integer[ lowerBufferDimension ];
        Integer[] upperBuffer = new Integer[ upperBufferDimension ];

        for (int lowerBufferIndex = 0; lowerBufferIndex < lowerBufferDimension; lowerBufferIndex++) {
            lowerBuffer[lowerBufferIndex] = array[ lowerBound + lowerBufferIndex];
        }
        for (int upperBufferIndex = 0; upperBufferIndex < upperBufferDimension; upperBufferIndex++) {
            upperBuffer[upperBufferIndex] = array[ medium + upperBufferIndex + 1];
        }

        Integer lowerBufferIndex = 0;
        Integer upperBufferIndex = 0;

        Integer arrayIndex = lowerBound;

        while (lowerBufferIndex < lowerBufferDimension && upperBufferIndex < upperBufferDimension){
            if(lowerBuffer[lowerBufferIndex] <= upperBuffer[upperBufferIndex]){
                array[arrayIndex] = lowerBuffer[lowerBufferIndex];
                lowerBufferIndex++;
            }else{
                array[arrayIndex] = upperBuffer[upperBufferIndex];
                upperBufferIndex++;
            }
            arrayIndex++;
        }

        while(lowerBufferIndex < lowerBufferDimension){
            array[arrayIndex] = lowerBuffer[lowerBufferIndex];
            lowerBufferIndex++;
            arrayIndex++;
        }

        while(upperBufferIndex < upperBufferDimension){
            array[arrayIndex] = upperBuffer[upperBufferIndex];
            upperBufferIndex++;
            arrayIndex++;
        }

    }

    public static void heap(Integer[] array){

        for (int i = array.length / 2 - 1; i >= 0 ; i--) {
            heapify(array, i, array.length);
        }

        for (int i = array.length - 1; i >= 0; i--){
            Integer a = array[0];
            array[0] = array[i];
            array[i] = a;

            heapify(array, 0, i);
        }
    }

    public static void heapify(Integer[] array, Integer parent,Integer length){
        Integer leftChild = (2 * parent) + 1;
        Integer rightChild = (2 * parent) + 2;

        Integer largest = parent;

        if(leftChild < length && array[leftChild] > array[largest]){
            largest = leftChild;
        }

        if(rightChild < length && array[rightChild] > array[largest]){
            largest = rightChild;
        }

        if( largest != parent ){

            Integer a = array[parent];
            array[parent] = array[largest];
            array[largest] = a;

            heapify(array, largest, length);
        }

    }

    public static void quick(Integer[] array){
        quick(array, 0, array.length - 1);
    }

    private static void quick(Integer[] array, Integer p, Integer q){
        if(p < q){
            Integer r = partition(array, p, q);
            quick(array, p, r - 1);
            quick(array, r + 1, q);
        }
    }

    public static Integer partition(Integer[] array, Integer p, Integer q){
        Integer x = array[q];
        Integer i = p - 1;
        for (int j = p; j < q; j++) {
            if(array[j] < x){
                i++;
                int a = array[i];
                array[i] = array[j];
                array[j] = a;
            }
        }
        int a = array[i + 1];
        array[i + 1] = array[q];
        array[q] = a;
        return i + 1;
    }

    /** Elements in the array must be in the range 0-range */
    public static void counting(Integer[] array, Integer range){
        Integer[] elementsCount = new Integer[range];
        for (int i = 0; i < range; i++) {
            elementsCount[i]  = 0;
        }
        for (int i = 0; i < array.length; i++) {
            elementsCount[array[i]] += 1;
        }
        for (int i = 1; i < range; i++) {
            elementsCount[i] = elementsCount[i] + elementsCount[i - 1];
        }
        Integer[] output = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            output[elementsCount[array[i]] - 1] = array[i];
            elementsCount[array[i]] -= 1;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = output[i];
        }
    }

    public static void radix(Integer[] array, Integer range){
        Integer digits = Utils.max(array);
        for (int i = 1; digits/i > 0; i *= 10) {
            countingDigit(array, range, i);
        }
    }

    /** Counting sort only evaluating one digit */
    private static void countingDigit(Integer[] array, Integer range, Integer digitPosition){
        Integer[] elementsCount = new Integer[range];
        for (int i = 0; i < range; i++) {
            elementsCount[i] = 0;
        }
        for (int i = 0; i < array.length; i++) {
            elementsCount[(array[i]/digitPosition)%10] += 1;
        }
        for (int i = 1; i < range; i++) {
            elementsCount[i] = elementsCount[i] + elementsCount[i - 1];
        }
        Integer[] output = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            output[elementsCount[(array[i]/digitPosition)%10] - 1] = array[i];
            elementsCount[(array[i]/digitPosition)%10] -= 1;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = output[i];
        }
    }

    public static void bucket(Double[] array, Integer bucketNumber){
        Vector<Double>[] buckets = new Vector[bucketNumber];
        for (int i = 0; i < bucketNumber; i++) {
            buckets[i] = new Vector<Double>();
        }
        for (int i = 0; i < array.length; i++) {
            Integer bucketIndex = (int)(bucketNumber * array[i]);
            buckets[bucketIndex].add(array[i]);
        }
        for (int i = 0; i < bucketNumber; i++) {
            Collections.sort(buckets[i]);
        }
        Integer index = 0;
        for (int i = 0; i < bucketNumber; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                array[index++] = buckets[i].elementAt(j);
            }
        }
        Utils.printArray(array);
    }

}
