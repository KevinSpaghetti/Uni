package com.company;

import java.util.ArrayList;


public class CRoundTable {

    private int n; //nights currently in the game

    IntSList first_list;
    IntSList second_list;

    static public void main(){

    }

    public CRoundTable(final int _n){
        n = _n;

        first_list = CRoundTable.range(1,_n);
        second_list = new IntSList();
    }

    public CRoundTable(final int _n, IntSList _first_list, IntSList _second_list){
        n = _n;
        first_list = _first_list;
        second_list = _second_list;
    }

    public int get_number_of_knights_in(){
        return n;
    }

    public int knight_with_jug_in(){
        return first_list.car();
    }

    public CRoundTable after_next_night_quits(){
        int list_length = first_list.length();

        IntSList first_list_target = new IntSList();
        IntSList second_list_target = new IntSList();

        if(list_length > 2) {

            first_list_target = first_list.cdr().cdr();
            second_list_target = second_list.cons(first_list.car());
            return new CRoundTable(n-1,first_list_target,second_list_target);
        }

        if(list_length == 2){

            first_list_target = (second_list.cons(first_list.car())).reverse();
            second_list_target = new IntSList();
            return new CRoundTable(n-1,first_list_target,second_list_target);

        }else{
            first_list_target = (second_list.cons(first_list.car())).reverse().cdr();
            return new CRoundTable(n-1,first_list_target,second_list_target);
        }

    }


    private static IntSList range(int inf, int sup) {
        if (inf > sup) {
            return new IntSList();
        } else {
            return range (inf + 1, sup).cons(inf);
        }
    }

}
