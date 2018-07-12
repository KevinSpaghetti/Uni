package com.company;

import puzzleboard.PuzzleBoard;

public class Es15 {

    public static void main(String[] args){

        //1: Definizione di una classe per rappresentare la board
        //   del gioco del 15
        PuzzleGame board = new PuzzleGame(5);
        System.out.println(board.toString());

        System.out.println("");

        //2: Implementazione di una GUI che permetta all'utente
        //   di giocare
        int size = 4;
        PuzzleBoard game = new PuzzleBoard(size);
        PuzzleGame state = new PuzzleGame(size);

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                game.setNumber(i,j,state.elementForPosition(i-1,j-1));
            }
        }

        state.setHole(size-1,size-1);
        game.clear(size,size);

        boolean flag = true;
        while(flag) {
            int element = game.get();
            if(state.canMove(element)){
                state.moveTo(element);
                for (int i = 1; i <= size; i++) {
                    for (int j = 1; j <= size; j++) {
                        game.setNumber(i,j,state.elementForPosition(i-1,j-1));
                    }
                }

                PuzzleGame.Position holePos = state.getHolePosition();
                game.clear(holePos.row+1,holePos.column+1);

                //System.out.println(state);

                if(state.isCompleted()){
                    System.out.println("Gioco completo");
                    //Modifca flag per terminare il gioco
                }

            }
            game.display();
        }

    }




}
