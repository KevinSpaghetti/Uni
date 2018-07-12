package com.company;

public class Es13 {

    public static void main(String[] args){

        //1: Definire la classe Board e utilizzarla per testare
        //   l'algoritmo che trova tutte le possibili soluzioni al problema delle regine

        //Soluzioni da 1 a 10
        for (int i = 1; i <= 10; i++) {
            int n_soluzioni = Queens.numberOfSolutions(i);
            System.out.println("Soluzioni per "+ i +" : "+n_soluzioni);
        }


    }




}
