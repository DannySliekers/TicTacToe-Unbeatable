package com.example.TicTacToe;

public class Pair<Integer> {
        private final int row;
        private final int column;

        public Pair(int row, int column){
            this.row = row;
            this.column = column;
        }

        public int row() {
            return this.row;
        }

        public int column() {
            return this.column;
        }

}
