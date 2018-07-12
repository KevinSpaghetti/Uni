package com.company;

import queens.ChessboardView;

import java.util.function.Function;

public class Es14 {

    public static void main(String[] args){

        //1: Adattamento della classe Board all'uso di liste
        //   di interi SList<Integer>
        SList<BoardInteger> soluzioni = Queens.listOfAllSolutions(4);
        Function<BoardInteger, Object> show = board -> {
            System.out.println(board.arrangement());
            return null;
        };
        soluzioni.map(show);


        System.out.println("\n");

        //2: Utilizzo della classe fornita per creare una GUI
        //   che permetta di visualizzare le soluzioni
        SList<BoardInteger> solutions = Queens.listOfAllSolutions(5);

        Function<BoardInteger, Object> f = board -> {
            ChessboardView v = new ChessboardView(board.size());
            v.setQueens(board.arrangement());
            return null;
        };

        solutions.map(f);

    }


}
