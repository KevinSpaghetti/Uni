package com.company;

public class Codifiche {

    //Versione iterativa della codifica complemento a 1
    public static String one_complement(String _input){
        String output = "";

        for(int index = 0; index < _input.length(); index++){
            if(_input.charAt(index) == '0'){
                output += "1";
            }
            if(_input.charAt(index) == '1'){
                output += "0";
            }

        }

        return output;
    }

    //Versione ricorsiva della codifica complemento a 1
    public static String r_one_complement(String _input){
        if(_input.length() == 1){
            if(_input.charAt(0) == '0'){
                return "1";
            }else{
                return "0";
            }
        }else{
            if(_input.charAt(0) == '0'){
                return "1" + r_one_complement(_input.substring(1));
            }else{
                return "0" + r_one_complement(_input.substring(1));
            }
        }
    }

    /**
     *
     * @param _input La stringa contenente il numero da cui partire
     * @param n numero degli interi consecutivi da rappresentare
     * @return  una lista di stringhe rappresentanti i numeri da input a input+n
     *          in codifica btr
     */
    public static StringSList btr(String _input, int n){
        StringSList output = new StringSList();

        String current = _input;
        String next = _input;

        output = output.cons(current);

        while(n>1){
            next = btr_inc(current);
            output = output.cons(next);

            current = next;
            n--;
        }

        return output.reverse();
    }

    /**
     * @param _input stringa in codifica btr
     * @return  la stringa incrementata di 1 in codifica btr
     */
    private static String btr_inc(String _input){
        if (_input.length() == 1){
            if(_input.charAt(_input.length()-1) == '+'){
                return "+-";
            }else{
                return "+";
            }
        }else{
            if(_input.charAt(_input.length()-1) == '+'){
                return btr_inc((String)_input.subSequence(0,_input.length()-1)) + "-";
            }else{
                String toAppend = "";
                if(_input.charAt(_input.length()-1) == '-'){
                    toAppend = ".";
                }else{
                    toAppend = "+";
                }
                return _input.substring(0,_input.length()-1) + toAppend;
            }
        }

    }
}
