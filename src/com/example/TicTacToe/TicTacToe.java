package com.example.TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        System.out.println("Welcome to this simple tic-tac-toe game");

        Scanner nameAsk = new Scanner(System.in);
        System.out.println("Please enter your username");

        String username = nameAsk.nextLine();
        System.out.println("Hello " + username);

        Game game = new Game(username);
    }
}
