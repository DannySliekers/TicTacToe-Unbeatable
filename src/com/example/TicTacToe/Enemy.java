package com.example.TicTacToe;

public class Enemy {

    private char enemyChoice;

    public void setEnemyChoice(char playChoice){
        this.enemyChoice = playChoice;
    }

    public char getEnemyChoice(){
        return this.enemyChoice;
    }

    public Pair generateMove(Board board, User user){
        int bestMove = Integer.MIN_VALUE;
        int row = -1;
        int col = -1;

        for(int i = 0; i <= 2; i++){
            for(int j = 0; j<= 2; j++){
                if(!board.isColumnOccupied(i,j)){
                    board.makeMove(i, j, null, this);
                    int moveEval = this.miniMax(board, 0, false, user);
                    board.makeMove(i, j, null, null);
                    if(moveEval > bestMove){
                        row = i;
                        col = j;
                        bestMove = moveEval;
                    }
                }
            }
        }

        return new Pair<Integer>(row, col);
    }

    private int evaluateBoard(Board board, User user) {
        for (int i = 0; i <= 2; i++) {
            //checks horizontal rows for wins
            if (board.getSymbol(i, 0) == board.getSymbol(i, 1) && board.getSymbol(i, 1) == board.getSymbol(i, 2)) {
                if (board.getSymbol(i, 0) == this.enemyChoice) {
                    return 10;
                } else if (board.getSymbol(i, 0) == user.getPlayChoice()) {
                    return -10;
                }
                //checks vertical rows for wins
            } else if (board.getSymbol(0, i) == board.getSymbol(1, i) && board.getSymbol(1, i) == board.getSymbol(2, i)) {
                if (board.getSymbol(0, i) == this.enemyChoice) {
                    return 10;
                } else if (board.getSymbol(0, i) == user.getPlayChoice()) {
                    return -10;
                }
            }
        }
        //checks first diagonal for wins
        if (board.getSymbol(0, 0) == board.getSymbol(1, 1) && board.getSymbol(1, 1) == board.getSymbol(2, 2)) {
            if (board.getSymbol(0, 0) == this.enemyChoice) {
                return 10;
            } else if (board.getSymbol(0, 0) == user.getPlayChoice()) {
                return -10;
            }
            //checks second diagonal for wins
        } else if (board.getSymbol(0, 2) == board.getSymbol(1, 1) && board.getSymbol(1, 1) == board.getSymbol(2, 0)) {
            if (board.getSymbol(1, 1) == this.enemyChoice) {
                return 10;
            } else if (board.getSymbol(1, 1) == user.getPlayChoice()) {
                return -10;
            }
        }
        // its a tie so return 0
        return 0;
    }

    private boolean movesLeft(Board board){
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                if(!board.isColumnOccupied(i, j)){
                    return true;
                }
            }
        }
        return false;
    }
    //minimax algorithm
    private int miniMax(Board board, int depth, boolean maxPlayer, User user){
        int score = evaluateBoard(board, user);

        if(score == 10){
            return score;
        } else if(score == -10){
            return score;
        }

        if(!this.movesLeft(board)){
            return 0;
        }

        if(maxPlayer){
            int best = Integer.MIN_VALUE;

            for(int i = 0; i <= 2; i++){
                for(int j = 0; j <= 2; j++){
                    if(!board.isColumnOccupied(i, j)){
                        board.makeMove(i, j, null, this);
                        best = Math.max(best, this.miniMax(board, depth + 1, false, user));
                        board.makeMove(i, j, null, null);
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;

            for(int i = 0; i <= 2; i++){
                for(int j = 0; j <= 2; j++){
                    if(!board.isColumnOccupied(i, j)){
                        board.makeMove(i, j, user, null);
                        best = Math.min(best, this.miniMax(board, depth + 1, true, user));
                        board.makeMove(i, j, null, null);
                    }
                }
            }
            return best;
        }
    }
}

