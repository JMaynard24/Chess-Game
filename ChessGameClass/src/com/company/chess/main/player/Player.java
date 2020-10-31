package com.company.chess.main.player;

import com.company.chess.main.board.Board;
import com.company.chess.main.board.Move;
import com.company.chess.main.pieces.King;
import com.company.chess.main.pieces.Piece;

import java.util.Collection;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;

    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
    }

    private King establishKing() {
        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        return null;
    }

    public abstract Collection<Piece> getActivePieces();
}
