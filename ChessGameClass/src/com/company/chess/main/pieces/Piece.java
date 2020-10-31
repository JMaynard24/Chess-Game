package com.company.chess.main.pieces;

import com.company.chess.main.Alliance;
import com.company.chess.main.board.Board;
import com.company.chess.main.board.Move;

import java.util.Collection;

public abstract class Piece {

        protected final PieceType pieceType;
        protected final int piecePosition;
        protected final Alliance pieceAlliance;
        protected final boolean isFirstMove;

        Piece(final PieceType pieceType,
                final int piecePosition, final Alliance pieceAlliance) {
            this.pieceType = pieceType;
            this.pieceAlliance = pieceAlliance;
            this.piecePosition = piecePosition;

            this.isFirstMove = false;
        }
    public int getPiecePosition() {
            return this.piecePosition;
    }
    public Alliance getPieceAlliance() {
            return this.pieceAlliance;
    }
    public boolean isFirstMove() {
            return this.isFirstMove;
    }

    public PieceType getPieceType() {
            return this.pieceType;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public enum PieceType {

        PAWN("P"),
        KNIGHT("N"),
        BISHOP("B"),
        ROOK("R"),
        QUEEN("Q"),
        KING("K");

        private String pieceName;

        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();
    }
}


