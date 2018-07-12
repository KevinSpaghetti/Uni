package com.company;

public class BoardInteger {

    private int dimension;
    private int queens;
    private SList<Integer> rows;
    private SList<Integer> columns;
    private SList<Integer> diagonal;
    private SList<Integer> antidiagonal;
    private String configuration;

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public BoardInteger(int _dimension){
        dimension = _dimension;
        queens = 0;

        rows = new SList<Integer>();
        columns = new SList<Integer>();
        diagonal = new SList<Integer>();
        antidiagonal = new SList<Integer>();

        configuration = "";
    }

    public BoardInteger addQueen(int _row, int _column){
        SList<Integer> new_rows = rows.cons(_row);
        SList<Integer> new_columns = columns.cons(_column);
        SList<Integer> new_diagonal = diagonal.cons(_row-_column);
        SList<Integer> new_adiagonal = antidiagonal.cons(_row+_column);

        String new_configuration = configuration.concat(coordsToTextRepresentation(_row,_column));

        BoardInteger _newBoard = new BoardInteger(dimension, queens+1, new_configuration, new_rows, new_columns, new_diagonal, new_adiagonal);
        return _newBoard;
    }

    private BoardInteger(int _dimension, int _queens, String _configuration, SList<Integer> _rows, SList<Integer> _columns, SList<Integer> _diagonal, SList<Integer> _adiagonal){
        dimension = _dimension;
        queens = _queens;
        rows = _rows;
        columns = _columns;
        diagonal = _diagonal;
        antidiagonal = _adiagonal;

        configuration = _configuration;
    }

    public boolean underAttack(int _row, int _column){
        SList<Integer> _rowList = rows;
        SList<Integer> _columnsList = columns;
        SList<Integer> _diagonaList = diagonal;
        SList<Integer> _antidiagonalList = antidiagonal;

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
