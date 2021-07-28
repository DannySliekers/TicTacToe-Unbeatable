package com.example.TicTacToe;

public class BoardColumn {
    private boolean isOccupied;
    private char symbol;


    public BoardColumn(char symbol, boolean isOccupied){
        this.symbol = symbol;
        this.isOccupied = isOccupied;
    }

    public void setSymbol(char symbol){
        this.symbol = symbol;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public void setOccupied(boolean occupied){
        this.isOccupied = occupied;
    }

    public boolean getOccupied(){
        return this.isOccupied;
    }
}
