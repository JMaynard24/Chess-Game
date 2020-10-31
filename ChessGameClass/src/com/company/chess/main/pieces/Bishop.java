package com.company.chess.main.pieces;

import com.company.chess.main.Alliance;
import com.company.chess.main.board.Board;
import com.company.chess.main.board.Move;

import java.util.Collection;

public class Bishop extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9,-7,7,9};

    public Bishop(Alliance pieceAlliance, final int piecePosition){
        super(PieceType.BISHOP, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return null;
    }

    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }
}
