package com.company;

public class Es11 {

    public static void main(String[] args){
        //1: definire una classe StringSList
        StringSList l = new StringSList();
        l = l.cons("c").cons("b").cons("a");
        System.out.println("Lista");
        System.out.println(l.reverse());
        System.out.println(l.length());
        System.out.println(l.listRef(2));
        System.out.print("\n");

        //2: utilizzare la classe precedentemente definita
        //   per codificare una sequenza di numeri in codifica btr
        StringSList risultato = Codifiche.btr("+-",5);
        System.out.println("Codifica btr");
        System.out.println(risultato);
    }

}
