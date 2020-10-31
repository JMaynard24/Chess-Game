package com.company.chess.main.pieces;

import com.company.chess.main.Alliance;
import com.company.chess.main.board.Board;
import com.company.chess.main.board.Move;

import java.util.Collection;

public class Queen extends Piece {
    public Queen(Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.QUEEN, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return null;
    }

    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }
}