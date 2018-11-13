package com.sorts;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void bubble() {
        Integer[] array = {2, 1, 4, 3, 5, 8, 6, 7, 9};
        Integer[] target = array.clone();

        Sort.bubble(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }

    @Test
    void insertion() {
        Integer[] array = {2, 1, 4, 3, 5, 8, 6, 7, 9};
        Integer[] target = array.clone();

        Sort.insertion(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }

    @Test
    void selection() {
        Integer[] array = {2, 1, 4, 3, 5, 8, 6, 7, 9};
        Integer[] target = array.clone();

        Sort.selection(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }

    @Test
    void merge() {
        Integer[] array = {2, 1, 4, 3, 5, 8, 6, 7, 9, 10};
        Integer[] target = array.clone();

        Sort.merge(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }

    @Test
    void mergeArray(){
        Integer[] array = {1, 3, 5, 10, 12, 2, 4, 7, 9};

        Sort.mergeArray(array, 0, array.length / 2, array.length - 1);

        Integer[] target = {1, 2, 3, 4, 5, 7, 9, 10, 12};
        assertArrayEquals(array, target,"Array not merged correctly");
    }

    @Test
    void heap() {
        Integer[] array = {2, 1, 4, 3, 5, 8, 6, 7, 9, 10};
        Integer[] target = array.clone();

        Sort.heap(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }

    @Test
    void quick() {
        Integer[] array = {2, 1, 4, 3, 5, 8, 6, 7, 9};
        Integer[] target = array.clone();

        Sort.quick(array);
        Arrays.sort(target);

        assertArrayEquals(target, array, "Unordered array");
    }
}