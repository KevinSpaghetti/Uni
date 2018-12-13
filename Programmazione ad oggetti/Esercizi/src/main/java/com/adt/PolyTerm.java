package com.adt;

/**
 * Represents a single term of the polynomial
 */
class PolyTerm {
    final Integer coefficent;
    final Integer exponent;

    /**
     * Init a polynomial
     * @param coefficent the coefficent of the polynomial
     * @param exponent the exponent associated with the polinomial
     */
    PolyTerm(Integer coefficent, Integer exponent){
        this.coefficent = coefficent;
        this.exponent = exponent;
    }

    /**
     * Copies the original PolyTerm
     * @param original the original from which the copy
     *                 will be made
     */
    PolyTerm(PolyTerm original){
        assert(original != null) : "copy source CAN'T be null";
        this.coefficent = original.coefficent;
        this.exponent = original.exponent;
    }
}
