/*
 * Modulo Josephus:
 *
 * Programma per risolvere il problema di Giuseppe Flavio
 * (metodi statici))
 *
 * Ultimo aggiornamento: 10/04/2018
 */
package com.company;

public class Josephus {

  public static int josephus (int n) {
      return josephusRec (new CRoundTable(n));
  }
  
  private static int josephusRec (CRoundTable rt) {
    if (rt.get_number_of_knights_in() > 1) {
      return josephusRec(rt.after_next_night_quits());
    } else {
      return rt.knight_with_jug_in();
    }
  }

  public static void main(String[] args){
      for (int i = 1; i < 100; i++) {
          System.out.println(josephus(i));
      }
  }

}  // class Josephus