package com.company.chess.main;

import com.company.chess.main.board.Board;

public class JavaChess {
    public static void main(String[] args) {

        Board board = Board.createStandardBoard();

        System.out.println(board);
    }
}
