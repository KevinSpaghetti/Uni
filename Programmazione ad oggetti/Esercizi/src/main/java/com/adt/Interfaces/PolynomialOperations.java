package com.adt.Interfaces;

/**
 * Interface to represent all the operations
 * on collections of polynomials
 */
public interface PolynomialOperations<T>{

    /**
     * Returns the highest degree in
     * the polynomial collection
     * @return the degree of the polynomial
     */
    Integer degree();

    /**
     * Returns the coefficent of the exponent n
     * @param n the exponent
     * @return the coefficient of the exponent n
     */
    Integer coefficent(Integer n);

    /**
     * Returns T that is the sum of the object
     * and element
     * @param element the 2nd term of the sum
     * @return an object representing the sum
     */
    T add(T element);

    /**
     * Multiplies this with another polynomial
     * @param element the 2nd term of the multiplication
     * @return another polynomial obtained by multiplication
     */
    T multiply(T element);

    /**
     * Flips the sign of every term of the polynomial
     * @return another polynomial with the signs flipped
     */
    T minus();

}
