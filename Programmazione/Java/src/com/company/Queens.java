
/*
 * Rompicapo delle "n regine"
 *
 * Ultimo aggiornamento: 12/04/2018
 *
 *
 * Dato astratto "configurazione della scacchiera":  Board
 *
 * Operazioni:
 *
 *   new Board( int n )           :  costruttore (scacchiera vuota)
 *
 *   size()                       :  int
 *
 *   queensOn()                   :  int
 *
 *   underAttack( int i, int j )  :  boolean
 *
 *   addQueen( int i, int j )     :  Board
 *
 *   arrangement()                :  String
 *
 *
 * Board b;
 *
 *   new Board(n)           costruttore della scacchiera n x n vuota;
 *   b.size()               dimensione n della scacchiera b;
 *   b.queensOn()           numero di regine collocate nella scacchiera b;
 *   b.underAttack(i,j)     la posizione <i,j> e' minacciata?
 *   b.addQueen(i,j)        scacchiera che si ottiene dalla configurazione b
 *                          aggiungendo una nuova regina in posizione <i,j>
 *                          (si assume che la posizione non sia minacciata);
 *   b.arrangement() :      descrizione "esterna" della configurazione
 *                          (convenzioni scacchistiche).
 */
package com.company;

import com.company.Board;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Stack;
import java.util.function.Function;

import queens.*;

public class Queens {


  /*
   * I. Numero di soluzioni:
   *
   *
   * Il numero di modi diversi in cui si possono disporre n regine
   *
   *   numberOfSolutions( n )
   *
   * in una scacchiera n x n e' dato dal numero di modi diversi in
   * cui si puo' completare la disposizione delle regine a partire
   * da una scacchiera n x n inizialmente vuota
   *
   *   numberOfCompletions( new Board(n) )
   */
  
  public static int numberOfSolutions( int n ) {
      Stack<Board> _stack = new Stack<Board>();
      _stack.push(new Board(n));

      int solutions_n = 0;

      while(!_stack.empty()){
          Board b = _stack.pop();
          int queensOn = b.queensOn();

          if(queensOn==n){
              solutions_n += 1;
          }else{
              int i = queensOn + 1;

              for ( int j=n; j>=1; j-- ) {
                  if ( !b.underAttack(i,j) ) {
                      _stack.push(b.addQueen(i,j));
                  }
              }
          }
      }

      return solutions_n;
  }

  public static SList<BoardInteger> listOfAllSolutions(int n){
      Stack<BoardInteger> _stack = new Stack<BoardInteger>();
      _stack.push(new BoardInteger(n));

      SList<BoardInteger> solutions = new SList<BoardInteger>();

      int solutions_n = 0;

      while(!_stack.empty()){
          BoardInteger b = _stack.pop();
          int queensOn = b.queensOn();

          if(queensOn==n){
              solutions_n += 1;
              solutions = solutions.cons(b);
          }else{
              int i = queensOn + 1;

              for ( int j=n; j>=1; j-- ) {
                  if ( !b.underAttack(i,j) ) {
                      _stack.push(b.addQueen(i,j));
                  }
              }
          }
      }

      return solutions;
  }
  
  public static int numberOfSolutionsState(int n){
      Board b = new Board(n);
      Stack<Integer> stack = new Stack<Integer>();

      stack.push(0);

      int solutions = 0;

      while(!stack.empty()){
          int queens = b.queensOn();
          int j = stack.pop();
          int i = queens;

          if( (i==0) && (queens == n)){
             solutions += 1;
          }else{

              if(j>0){
                  //b.removeQueen(i,j);
              }else{
                  i += 1;
              }

              do{

                  j += 1;
              }while(!b.underAttack(i,j) && (j<=n));

              if(j<=n) {
                  stack.push(j);
                  b.addQueen(i, j);
                  stack.push(0);
              }
          }
      }


      return solutions;
  }
  /*
   * Il numero di modi in cui si puo' completare la disposizione
   * a partire da una scacchiera b parzialmente configurata
   *
   *   numberOfCompletions( b )   : int
   *
   * dove k regine (0 <= k < n) sono collocate nelle prime k righe
   * di b, si puo' determinare a partire dalle configurazioni
   * che si ottengono aggiungendo una regina nella riga k+1 in tutti
   * i modi possibili (nelle posizioni che non sono gia' minacciate)
   *
   *   for ( int j=1; j<=n; j=j+1 ) {
   *     if ( !b.underAttack(i,j) ) { ... b.addQueen(i,j) ... }
   *   }
   *
   * calcolando ricorsivamente per ciascuna di queste il numero
   * di modi in cui si puo' completare la disposizione
   *
   *   numberOfCompletions( b.addQueen(i,j) )
   *
   * e sommando i valori che ne risultano
   *
   *   count = count + numberOfCompletions( ... )
   *
   * Se invece la scacchiera rappresenta una soluzione (q == n)
   * c'e' un solo modo (banale) di completare la disposizione:
   * lasciare le cose come stanno!
   */
  
  private static int numberOfCompletions( Board b ) {
  
    int n = b.size();
    int q = b.queensOn();
    
    if ( q == n ) {
    
      return 1;
    
    } else {
    
      int i = q + 1;
      int count = 0;
      
      for ( int j=1; j<=n; j=j+1 ) {
        if ( !b.underAttack(i,j) ) {
          count = count + numberOfCompletions( b.addQueen(i,j) );
      }}
      return count;
    }
  }

  public static void main( String args[] ) {
  
    int n = Integer.parseInt( args[0] );

    SList<BoardInteger> solutions = new SList<BoardInteger>();

    solutions = listOfAllSolutions(5);

    Function<BoardInteger, Object> print_f = board -> {
        System.out.println(board.arrangement());
        return board;
    };
    solutions.map(print_f);

    visualizeSolutions(solutions);
  }



  static void visualizeSolutions(SList<BoardInteger> _solutions){

      Function<BoardInteger, Object> f = board -> {
          ChessboardView v = new ChessboardView(board.size());
          v.setQueens(board.arrangement());
          return board;
      };


      _solutions.map(f);

  }


}  // class Queens

