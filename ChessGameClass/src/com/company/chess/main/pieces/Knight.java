package com.company.chess.main.pieces;

import com.company.chess.main.Alliance;
import com.company.chess.main.board.Board;
import com.company.chess.main.board.Move;

import java.util.Collection;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 10, 15, 17}; 


    public Knight(Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.KNIGHT, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return null;
    }

    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }
}
