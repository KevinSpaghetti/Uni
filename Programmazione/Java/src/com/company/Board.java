package com.company;

import jdk.nashorn.internal.runtime.Debug;

import java.lang.ref.WeakReference;
import java.util.function.BiPredicate;

public class Board {

    private int dimension;
    private int queens;
    private IntSList rows;
    private IntSList columns;
    private IntSList diagonal;
    private IntSList antidiagonal;
    private String configuration = "";

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Board(int _dimension){
        dimension = _dimension;
        queens = 0;

        rows = new IntSList();
        columns = new IntSList();
        diagonal = new IntSList();
        antidiagonal = new IntSList();

        configuration = "";
    }

    public Board addQueen(int _row, int _column){
        IntSList new_rows = rows.cons(_row);
        IntSList new_columns = columns.cons(_column);
        IntSList new_diagonal = diagonal.cons(_row-_column);
        IntSList new_adiagonal = antidiagonal.cons(_row+_column);

        String new_configuration = configuration + coordsToTextRepresentation(_row,_column);

        Board _newBoard = new Board(dimension, queens+1, new_configuration, new_rows, new_columns, new_diagonal, new_adiagonal);
        return _newBoard;
    }

    private Board(int _dimension, int _queens, String _configuration, IntSList _rows, IntSList _columns, IntSList _diagonal, IntSList _adiagonal){
        dimension = _dimension;
        queens = _queens;
        rows = _rows;
        columns = _columns;
        diagonal = _diagonal;
        antidiagonal = _adiagonal;

        configuration = _configuration;
    }

    public boolean underAttack(int _row, int _column){
        IntSList _rowList = rows;
        IntSList _columnsList = columns;
        IntSList _diagonaList = diagonal;
        IntSList _antidiagonalList = antidiagonal;

        while(!_rowList.isNull()){
            int threatened_coord_row = _rowList.car();
            int threatened_coord_column = _columnsList.car();
            int threatened_coord_diag = _diagonaList.car();
            int threatened_coord_adiag = _antidiagonalList.car();

            if(threatened_coord_row == _row){ return true; }
            if(threatened_coord_column == _column){ return true; }
            if(threatened_coord_diag == (_row-_column)){ return true; }
            if(threatened_coord_adiag == (_row+_column)) { return true; }

            _rowList = _rowList.cdr();
            _columnsList = _columnsList.cdr();
            _diagonaList = _diagonaList.cdr();
            _antidiagonalList = _antidiagonalList.cdr();
        }

        return false;
    }

    private String coordsToTextRepresentation(int _row, int _column){
        String _s = "";
        String letter = String.valueOf(alphabet.charAt(_row%alphabet.length()));
        _s = letter+_column+" ";
        return _s;
    }

    public int size(){
        return dimension;
    }

    public int queensOn(){
        return queens;
    }

    public String arrangement(){
        return configuration;
    }
}
