package com.adt;

import com.adt.exceptions.EmptyIntBagException;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * IntBag represents a collection of Integers
 * that allows duplicates and does not guarantee
 * the ordering of the elements
 */
public class IntBag {

    private Vector<Integer> elements;

    /**
     * Create a new empty IntBag
     */
    public IntBag(){
        this.elements = new Vector<>();
    }

    /**
     * Create a new IntBag by boxing and copying
     * {@code elements} inside
     * @param array array of int
     */
    public IntBag(int[] array){
        assert(array != null);
        this.elements = new Vector<>();
        for (int i = 0; i < array.length; i++) {
            this.elements.add(array[i]);
        }
    }

    /**
     * Create a new IntBag by copying the contents
     * of {@code original}
     * @param original A non null instance of IntBag
     */
    public IntBag(IntBag original){
        assert(original != null);
        this.elements = (Vector<Integer>) original.elements.clone();
    }

    /**
     * Add a element to the IntBag
     * @param element element to be appended to the IntBag
     */
    public void insert(Integer element){
        assert(element != null);
        this.elements.add(element);
    }

    /**
     * Removes the Integer
     * @param target the element to remove from the IntBag
     * @return true if the target has been found and removed,
     * false otherwise
     */
    public boolean remove(Integer target){
        boolean result = this.elements.remove(target);
        return result;
    }

    /**
     * Checks if the IntBag contains the {@code element}
     * @param element the element to find
     * @return true if the element is in the IntBag,
     * false otherwise
     */
    public boolean contains(Integer element){
        assert(element != null);
        boolean result = this.elements.contains(element);
        return result;
    }

    /**
     * Returns the count of all the elements contained in the
     * IntBag, every number gets counted even if it is duplicated
     * @return The number of elements in the IntBag
     */
    public long sizeOf(){
        return this.elements.size();
    }

    /**
     * generator handles the generations of random numbers,
     * emulates the lazy initialization method so it's not
     * guaranteed that it will always be initialized,
     * check for null and initialize it if needed
     */
    private Random generator;

    /**
     * Picks a random Integer contained in the IntBag but
     * does not remove it
     * @return a random integer in the IntBag
     * @throws EmptyIntBagException if the IntBag has no elements
     */
    public Integer pick() throws EmptyIntBagException {
        if(elements.size() == 0) {
            throw new EmptyIntBagException();
        }
        //Avoid creating the generator every time since
        //initialization is expensive, initialize only
        //on the first request
        if(generator == null) {
            this.generator = new Random();
        }
        Integer bagSize = this.elements.size();
        Integer randomIndex = this.generator.nextInt(bagSize);
        return this.elements.elementAt(randomIndex);
    }

    /**
     * Check if two instances of IntBag contain the same numbers,
     * does not account for the order
     * @param other
     * @return returns true if the two instances contain the
     * exact same elements repeated the exact same number of times,
     * false otherwise
     */
    public boolean sameValue(IntBag other){
        assert(other != null);
        Collections.sort(this.elements);
        Collections.sort(other.elements);
        boolean result = this.elements.equals(other.elements);
        return result;
    }

}
