package com.company;

public class LongestSequence {

    public static int top_down_lis(int[] s){

        int[] seq = new int[s.length+1];
        for (int i = 0; i < seq.length-1; i++) {
            seq[i] = s[i];
        }
        seq[seq.length-1] = 0;

        int[][] results = new int[seq.length+1][seq.length+1];
        for(int i = 0; i<seq.length+1; i++) {
            for (int j = 0; j < seq.length+1; j++) {
                results[i][j] = -1;
            }
        }

        int res = _flis(seq,0,seq.length-1,results);

        return res;
    }

    //i è la posizione nella lista
    //j è la posizione di s dove posso trovare t
    private static int _flis( int[] s, int i, int j, int[][] cache) {
        final int n = s.length;

        if(cache[i][j] != -1){
            return cache[i][j];
        }

        if ( i == n ) {
            //cache[i][j] = 0;
            return cache[i][j] = 0;
        }

        if ( s[i] <= s[j] ) {
            return cache[i][j] = _flis(s,i+1,j, cache);
        } else {
            return cache[i][j] = Math.max(1+_flis(s,i+1, i, cache), _flis(s,i+1, j, cache));
        }
    }


    /**
     * Applicazione della tecnica bottom-up di programmazione dinamica
     * @param s sequenza dei numeri
     * @return la lunghezza della sequenza crescente più lunga
     */


    public static SList<Integer> bottom_up_lis(int[] s){
        int[] seq = new int[s.length+1];
        for (int i = 0; i < seq.length-1; i++) {
            seq[i] = s[i];
        }
        seq[seq.length-1] = 0;

        SList<Integer> result = _bottom_up_lis(seq);
        return result;
    }

    private static SList<Integer> _bottom_up_lis( int[] s) {
        final int n = s.length;

        int max = 0;

        int[][] cache = new int[s.length+1][s.length+1];

        for (int i = s.length-1; i >= 0; i--) {
            for (int j = s.length-1; j >= 0; j--) {

                if (s[i] <= s[j] ) {
                    cache[i][j] = cache[i+1][j];
                } else {
                    cache[i][j] = Math.max(1+cache[i+1][i],cache[i+1][j]);
                }

            }
        }
        

        SList<Integer> sequence = new SList<Integer>();

        int target = max(cache)-1;

        int index = 0;
        while(index < s.length-1){
            int c = cache[index][index];

            if(c==target){
                sequence = sequence.cons(s[index]);
                target--;
            }

            index++;
        }


        return sequence.reverse();
    }

    //Stampa la matrice
    static void print(int[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j <m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    static void print(int[][] m, int[] header_top){
        for (int i = 0; i < header_top.length; i++) {
            System.out.print(header_top[i]+" ");
        }

        System.out.println("");

        for (int i = 0; i < m.length; i++) {

            for (int j = 0; j <m[0].length; j++) {
                System.out.print(m[i][j] + "  ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    //Ricerca il massimo in una matrice
    static int max(int[][] m){
        int max = 0;

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j <m[0].length; j++) {
                if (max<m[i][j]) { max = m[i][j]; }
            }
        }

        return max;
    }

}
