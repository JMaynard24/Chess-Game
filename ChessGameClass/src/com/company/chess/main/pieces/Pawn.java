package com.company.chess.main.pieces;

import com.company.chess.main.Alliance;
import com.company.chess.main.board.Board;
import com.company.chess.main.board.Move;

import java.util.Collection;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = {8};

    public Pawn(Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.PAWN, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return null;
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
}
