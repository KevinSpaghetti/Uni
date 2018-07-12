package com.company;

public class Es12 {

    public static long timeCost( int n ) {

        long t0 = System.currentTimeMillis();   // Avvio cronomentro

        for ( int k=1; k<=n; k=k+1 ) {

            int j = Josephus.josephus(k);         // valore non utilizzato
        }

        long t1 = System.currentTimeMillis();   // Stop cronomentro

        return ( t1 - t0 );
    }


    public static void main( String args[] ) {

        int n = Integer.parseInt( args[0] );

        System.out.println( timeCost(n) + " msec" );
    }






}
