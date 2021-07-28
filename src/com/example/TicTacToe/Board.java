package com.example.TicTacToe;

public class Board {
    private BoardColumn[][] board;

    public Board(){
        this.board = new BoardColumn[3][3];
        this.GenerateInitialBoard();
    }

    private void GenerateInitialBoard(){
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                this.board[i][j] = new BoardColumn(' ', false);
            }
        }
    }



    public void makeMove(int row, int col, User user, Enemy enemy){
        if(enemy == null &&  user == null){
            this.board[row][col].setSymbol(' ');
            this.board[row][col].setOccupied(false);
        }
         else if(enemy == null){
            this.board[row][col].setSymbol(user.getPlayChoice());
            this.board[row][col].setOccupied(true);
        } else if(user == null) {
            this.board[row][col].setSymbol(enemy.getEnemyChoice());
            this.board[row][col].setOccupied(true);
        }
    }

    public void printBoard(){
        String boardToPrint = "";
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                if(j == 2){
                    boardToPrint += "[" + this.board[i][j].getSymbol() + "] \n";
                } else {
                    boardToPrint += "[" + this.board[i][j].getSymbol() + "]-";
                }
            }

        }
        System.out.println(boardToPrint);
    }

    public boolean isColumnOccupied(int row, int col){
        if(this.board[row][col].getOccupied()){
            return true;
        }else {
            return false;
        }
    }

    public char getSymbol(int row, int col){
        return this.board[row][col].getSymbol();
    }

}
