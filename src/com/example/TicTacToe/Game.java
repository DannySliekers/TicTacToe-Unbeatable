package com.example.TicTacToe;

import java.util.Scanner;

public class Game {
    private String username;
    private Board board;
    private User user;
    private Enemy enemy;

    public Game(String username) {
        this.username = username;
        this.board = new Board();
        this.user = new User();
        this.enemy = new Enemy();
        startNewGame();
    }

    private void startNewGame() {
        playChoice();
        turn();
    }

    private void turn() {
        System.out.println(username + ", please make a move");
        System.out.println("Which row do you want to play?");
        int row = 0;
        int col = 0;
        Scanner rowInput = new Scanner(System.in);
        if (rowInput.hasNextInt()) {
            row = rowInput.nextInt();
        } else {
            System.out.println("Please enter a number");
            this.turn();
        }
        if (row < 1 | row > 3) {
            System.out.println("Invalid input, please select a number between 1-3");
            this.turn();
        }
        System.out.println("Which column do you want to play?");
        Scanner colInput = new Scanner(System.in);
        if (colInput.hasNextInt()) {
            col = colInput.nextInt();
        } else {
            System.out.println("Please enter a number");
            this.turn();
        }
        if (col < 1 | col > 3) {
            System.out.println("Invalid input, please select a number between 1-3");
            this.turn();
        }
        if (!this.board.isColumnOccupied(row - 1, col - 1)) {
            this.board.makeMove(row - 1, col - 1, this.user, null);
        } else {
            System.out.println("This square is occupied, try another one");
            this.turn();
        }


        this.board.printBoard();
        if (this.checkWin(this.user.getPlayChoice())) {
            System.out.println("You win");
            System.exit(0);
        } else if (this.checkTie()) {
            System.out.println("Its a tie");
            System.exit(0);
        }
        System.out.println("Enemy has made a move");
        this.board.makeMove(this.enemy.generateMove(this.board, this.user).row(), this.enemy.generateMove(this.board, this.user).column(), null, this.enemy);
        this.board.printBoard();
        if (this.checkWin(this.enemy.getEnemyChoice())) {
            System.out.println("Enemy wins");
            System.exit(0);
        } else {
            this.turn();
        }
    }

    private void playChoice() {
        System.out.println("Do you want to be X or O?");
        Scanner userInput = new Scanner(System.in);
        char playChoice = userInput.nextLine().charAt(0);
        if (playChoice == 'X' | playChoice == 'x') {
            user.setPlayChoice('X');
            enemy.setEnemyChoice('O');
        }
        else if (playChoice == 'O' | playChoice == 'o') {
            user.setPlayChoice('O');
            enemy.setEnemyChoice('X');
        }
        else {
            System.out.println("Non valid input, try again");
            playChoice();
        }
    }

    private boolean checkWin(char symbol) {
        for (int i = 0; i <= 2; i++) {
            //checks horizontal rows for wins
                if (this.board.getSymbol(i, 0) == symbol && this.board.getSymbol(i, 1) == symbol && this.board.getSymbol(i, 2) == symbol) {
                    return true;
                    //checks vertical rows for wins
                } else if (this.board.getSymbol(0, i) == symbol && this.board.getSymbol(1, i) == symbol && this.board.getSymbol(2, i) == symbol) {
                    return true;
                }
            }
        //checks first diagonal for wins
        if (this.board.getSymbol(0, 0) == symbol && this.board.getSymbol(1, 1) == symbol && this.board.getSymbol(2, 2) == symbol) {
            return true;
            //checks second diagonal for wins
        } else if (this.board.getSymbol(0, 2) == symbol && this.board.getSymbol(1, 1) == symbol && this.board.getSymbol(2, 0) == symbol) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkTie() {
        int columnOccupiedCounter = 0;
        for (int i =  0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (this.board.isColumnOccupied(i, j)) {
                    columnOccupiedCounter++;
                }
            }
        }
        if (columnOccupiedCounter == 9) {
            return true;
        } else {
            return false;
        }
    }
}
