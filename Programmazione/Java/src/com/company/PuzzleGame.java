package com.company;

public class PuzzleGame {

    private final int hole = 0;


    private int[][] gameState;
    private int boardSize;
    private int numberOfMoves;


    public PuzzleGame(int _size){
        gameState = new int[_size][_size];
        boardSize = _size;
        numberOfMoves = 0;

        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                gameState[i][j] = ((i*_size)+j)+1;
            }
        }

    }

    //Scorro la Board utilizzando boardIndex che viene incrementato ogni ciclo,
    //a meno che io non trovi la casella vuota, in tal caso salto l'aggiunta di 1
    //all'indice

    public boolean isCompleted(){
        int boardIndex = 1;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if(gameState[i][j] != hole) {
                    if(gameState[i][j] != boardIndex){
                        //Se la casella che esamino non è una buca e non è
                        //uguale a board index allora il gioco non è ordinato
                        return false;
                    }

                    boardIndex++;
                }

            }
        }
        return true;
    }

    public boolean canMove(int _row, int _column){
        if(_row > boardSize || _row < 0){
            return false;
        }
        if(_column > boardSize || _row < 0){
            return false;
        }

        //Check se la buca è vicino alla riga e alla colonna
        if(checkNear(gameState,_row,_column,hole) == null){
            return false;
        }

        return true;
    }

    public boolean canMove(int _number){
        Position numberPosition = puzzleNumberToMatrixCoords(_number);
        int _row = numberPosition.row;
        int _column = numberPosition.column;

        if(_row > boardSize || _row < 0){
            return false;
        }
        if(_column > boardSize || _row < 0){
            return false;
        }

        //Check se la buca è vicino alla riga e alla colonna
        if(checkNear(gameState,_row,_column,hole) == null){
            return false;
        }

        return true;
    }

    public int elementForPosition(int _row, int _column){
        return gameState[_row][_column];
    }

    public int elementForPosition(Position _position){
        return gameState[_position.row][_position.column];
    }

    //Sets the hole
    public void setHole(int _row, int _column){
        gameState[_row][_column] = hole;
    }

    public void setHole(Position _position){
        gameState[_position.row][_position.column] = hole;
    }

    public Position getHolePosition(){
        return puzzleNumberToMatrixCoords(0);
    }

    public String toString() {
        String result = "";

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                result += " "+gameState[i][j];
            }
            result += "\n";
        }

        return result;
    }

    //Assumiamo che l'utente non abbia validato la posizione
    //Il metodo ritorna vero se è stato possibile
    //muovere la casella, altrimenti ritorna falso
    public boolean moveTo(int _row, int _column){
        if(!canMove(_row,_column)){
            return false;
        }

        Position holePosition = checkNear(gameState,_row,_column,hole);

        gameState[holePosition.row][holePosition.column] = gameState[_row][_column];
        gameState[_row][_column] = hole;


        numberOfMoves++;
        return true;
    }

    public boolean moveTo(int _number){
        Position numberPosition = puzzleNumberToMatrixCoords(_number);
        int _row = numberPosition.row;
        int _column = numberPosition.column;

        return moveTo(_row,_column);
    }

    //Controlla le caselle vicine alla casella [_row][_column] in cerca di un numero
    //_target, se lo trova ritorna la posizione, altrimenti ritorna null
    //Questo metodo controlla anche la casella stessa
    private Position checkNear(int[][] _matrix, int _row, int _column, int _target){

        //Ciclo per controllare le caselle circostanti
        //alternando -1,0,1 alla quantità da sommare alla posizione fornita
        //[(-1,-1),(-1,+0),(-1,+1)]
        //[(+0,-1),(+0,+0),(+0,+1)]
        //[(+1,-1),(+1,+0),(+1,+1)]
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                //Se sono in un angolo salto al prossimo
                if(Math.abs(i) != Math.abs(j)) {
                    //Normalizzo la riga e la colonna in modo che siano sempre nel
                    //range [0-boardSize](boardSize non incluso)
                    int normalizedRow = Math.min(Math.max(0, _row + i), boardSize - 1);
                    int normalizedColumn = Math.min(Math.max(0, _column + j), boardSize - 1);

                    if (_matrix[normalizedRow][normalizedColumn] == _target) {
                        return new Position(normalizedRow, normalizedColumn);
                    }
                }
            }
        }

        return null;
    }


    //Questo metodo trasforma un numero nelle corrispondenti coordinate
    //della matrice gameState
    private Position puzzleNumberToMatrixCoords(int _number){
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if(gameState[i][j] == _number){
                    return new Position(i,j);
                }
            }
        }

        return null;
    }

    public static class Position{
        int row;
        int column;

        public Position(int _row, int _column){
            row = _row;
            column = _column;
        }
    }
}










