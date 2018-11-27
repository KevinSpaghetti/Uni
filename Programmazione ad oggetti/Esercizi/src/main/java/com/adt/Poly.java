package com.adt;

import com.adt.Interfaces.PolynomialOperations;
import com.adt.exceptions.NegativeExponentException;

import java.util.Vector;

/**
 * Represents polynomials with integer non-null
 * coefficents and non-negative exponents
 * Poly is immutable, unbounded.
 * The empty poly is 0 = 0*x^0
 */
public class Poly implements PolynomialOperations<Poly> {

    private Vector<PolyTerm> terms;

    /**
     * Returns an empty polynomial
     */
    public Poly(){
        terms = new Vector<>();
    }

    /**
     * Creates a Polynomial from the provided coefficent and exponent
     * @param coefficent the coefficent
     * @param exponent the exponent
     * @throws NegativeExponentException if the exponent provided is negative
     */
    public Poly(Integer coefficent, Integer exponent) throws NegativeExponentException{
        if(exponent < 0) { throw new NegativeExponentException(); }

        terms = new Vector<>();
        if(coefficent != 0){
            PolyTerm term = new PolyTerm(coefficent, exponent);
            terms.add(term);
        }
    }

    /**
     * Create a copy of an instance of Poly
     * @param original the source of the copy
     */
    public Poly(Poly original){
        this.terms = new Vector<>();
        for (PolyTerm t: original.terms){
            PolyTerm l = new PolyTerm(t.coefficent, t.exponent);
            this.terms.add(l);
        }
    }

    /**
     * Returns the degree of the polynomial
     * @return the largest exponent in this polynomial,
     * (0) if it is the empty polynomial
     */
    public Integer degree(){
        assert(this.terms != null);

        if(this.terms.isEmpty()){ return 0; }
        Integer highest = 0;
        for( PolyTerm t: this.terms){
            if(t.exponent > highest){ highest = t.exponent; }
        }
        return highest;
    }

    /**
     * Finds the coefficent of the term that is associated with the
     * {@code n} exponent
     * @return the coefficent of the term that has {@code n} exponent
     */
    public Integer coefficent(Integer n){
        assert(this.terms != null);
        assert(n != null);

        for (PolyTerm t: this.terms){
            if(t.exponent == n) { return t.coefficent; }
        }
        return (0);
    }

    /**
     * Add a polynomial to another
     * @param element the polynomial to add
     * @return a new polynomial by adding element
     */
    public Poly add(Poly element){
        Poly result = new Poly();
        Integer highestDegree = Math.max(this.degree(), element.degree());
        Poly highestPoly;
        Poly lowestPoly;
        if(highestDegree == this.degree()){
            highestPoly = new Poly(this);
            lowestPoly = new Poly(element);
        }else{
            highestPoly = new Poly(element);
            lowestPoly = new Poly(this);
        }
        Integer degree = highestDegree;
        while(degree > 0){
            Integer coefficient = highestPoly.coefficent(degree);
            Integer coefficientToAdd = lowestPoly.coefficent(degree);
            if(!(coefficient + coefficientToAdd == 0)) {
                result.terms.add(new PolyTerm(coefficient + coefficientToAdd, degree));
            }
            degree--;
        }
        return result;
    }

    /**
     * Returns the opposite of the polynomial
     * @return the polynomial with the sign changed
     */
    public Poly minus(){
        return this.multiply(-1);
    }

    /**
     * Multiplies the polynomial with the element
     * @param element the element
     * @return a polynomial that is this*p
     */
    public Poly multiply(Poly element){
        Poly result = new Poly();
        for (PolyTerm t: this.terms){
            for (PolyTerm a: element.terms){
                Integer coeff = t.coefficent * a.coefficent;
                Integer expt = t.exponent + a.exponent;
                PolyTerm polyResult = new PolyTerm(coeff, expt);
                result.terms.add(polyResult);
            }
        }
        return result.trim();
    }

    /**
     * Reduces the polynomial to have only 1
     * coefficient per degree by summing all the coefficients
     * with that degree
     * @return
     */
    private Poly trim(){
        Poly result = new Poly();
        Integer size = this.degree();
        //Add all elements that have exponent n under the n-th list
        //at the end sum every coefficient of the n-th list to obtain
        //the sum in one polynomial term
        Vector<Vector<PolyTerm>> polyMatrix = new Vector<>();

        //Init all the vectors
        Integer index = 0;
        while (index <= size){
            polyMatrix.add(new Vector<PolyTerm>());
            index++;
        }

        //Add every term with the same exponent to the n-th list
        for (PolyTerm t: this.terms){
            PolyTerm p = new PolyTerm(t.coefficent, t.exponent);
            Vector<PolyTerm> sameExponentTerms = polyMatrix.elementAt(t.exponent);
            sameExponentTerms.add(p);
        }

        //Sum all the list coefficients to obtain the coefficient of one term
        for (int i = size; i >= 0; i--){
            Vector<PolyTerm> sameExponentTerms = polyMatrix.elementAt(i);
            Integer coefficientSum = 0;
            for (PolyTerm t: sameExponentTerms){
                coefficientSum += t.coefficent;
            }
            if(coefficientSum != 0){
                PolyTerm trimmedTerm = new PolyTerm(coefficientSum, i);
                result.terms.add(trimmedTerm);
            }
        }
        return result;
    }

    /**
     * Multiply the polynomial by a scalar value
     * @param scalar the scalar value
     * @return the multiplied polynomial
     */
    public Poly multiply(Integer scalar){
        Poly result = new Poly();
        for (PolyTerm t: this.terms){
            PolyTerm p = new PolyTerm(scalar*t.coefficent, t.exponent);
            result.terms.add(p);
        }
        return result;
    }

    @Override
    public String toString(){
        assert (this.terms != null);
        StringBuffer result = new StringBuffer();
        for (PolyTerm t: this.terms){
            String formattedPoly = String.format("+%d*x^%d", t.coefficent, t.exponent);
            result.append(formattedPoly);
        }
        return result.toString();
    }

    public static void main(String[] args){
        try {
            Poly nomial = new Poly(2, 5);
            Poly l = new Poly(2, 1);
            Poly ml = new Poly(7,2);
            System.out.println(nomial.multiply(l));
            System.out.println(nomial.multiply(5));
            System.out.println(l.add(ml).multiply(nomial));
        }catch (NegativeExponentException e){

        }
    }
}
