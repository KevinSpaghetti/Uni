package com.adt;

import java.util.Vector;

public class IntBag {


    private Vector<Integer> elements;

    /**
     * Create a new empty IntBag
     */
    public IntBag(){
        this.elements = new Vector<>();
    }

    public IntBag(int[] elements){
        this.elements = new Vector<>();
        for (int i = 0; i < elements.length; i++) {
            this.elements.add(elements[i]);
        }
        //
    }


}
