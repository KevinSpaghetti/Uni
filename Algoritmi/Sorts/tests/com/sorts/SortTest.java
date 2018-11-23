package com.sorts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void bubble() {
        Integer[] array =Utils.randomArrayOfSize(1000);
        Integer[] target = array.clone();

        Sort.bubble(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }

    @Test
    void insertion() {
        Integer[] array = Utils.randomArrayOfSize(100);
        Integer[] target = array.clone();

        Sort.insertion(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }

    @Test
    void selection() {
        Integer[] array =Utils.randomArrayOfSize(1000);
        Integer[] target = array.clone();

        Sort.selection(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }

    @Test
    void merge() {
        Integer[] array =Utils.randomArrayOfSize(1000);
        Integer[] target = array.clone();

        Sort.merge(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }

    @Test
    void heap() {
        Integer[] array =Utils.randomArrayOfSize(1000);
        Integer[] target = array.clone();

        Sort.heap(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }

    @Test
    void quick() {
        Integer[] array =Utils.randomArrayOfSize(1000);
        Integer[] target = array.clone();

        Sort.quick(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }
}