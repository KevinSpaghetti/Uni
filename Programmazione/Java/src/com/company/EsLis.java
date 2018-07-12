package com.company;

public class EsLis {

    public static void main(String[] args){

        //1: Applicazione della tecnica top-down di memoization
        //   all'algoritmo della sottosequenza crescente più lunga
        int expected;
        int[] sequence;
        int result;

        expected = 1;
        sequence = new int[]{5,4,3,2,1};
        result = LongestSequence.top_down_lis(sequence);
        System.out.println(expected + ":" +result);


        expected = 3;
        sequence = new int[]{47,38,39,25,44};
        result = LongestSequence.top_down_lis(sequence);
        System.out.println(expected + ":" +result);


        expected = 4;
        sequence = new int[]{27,90,7,29,49,8,53,1,28,6};
        result = LongestSequence.top_down_lis(sequence);
        System.out.println(expected + ":" +result);


        expected = 5;
        sequence = new int[]{9,46,54,71,60,47,0,32,25,61};
        result = LongestSequence.top_down_lis(sequence);
        System.out.println(expected + ":" +result);

        expected = 3;
        sequence = new int[]{54,52,42,33,14,40,37,61,53,1};
        result = LongestSequence.top_down_lis(sequence);
        System.out.println(expected + ":" +result);

        System.out.println("");

        //2: Applicazione dell'algoritmo bottom-up di programmazione dinamica
        //   e ricostruzione della sottosequenza crescente più lunga

        sequence = new int[]{1,3,2,5};
        System.out.println(LongestSequence.bottom_up_lis(sequence));

        sequence = new int[]{47,38,39,25,44};
        System.out.println(LongestSequence.bottom_up_lis(sequence));

        sequence = new int[]{27,90,7,29,49,8,53,1,28,6};
        System.out.println(LongestSequence.bottom_up_lis(sequence));

        sequence = new int[]{9,46,54,71,60,47,0,32,25,61};
        System.out.println(LongestSequence.bottom_up_lis(sequence));

        sequence = new int[]{54,52,42,33,14,40,37,61,53,1};
        System.out.println(LongestSequence.bottom_up_lis(sequence));


    }

}
