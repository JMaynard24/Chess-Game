package pieces;

import Alliance;
import board.Board;
import board.Move;

import java.util.Collection;

public class Rook extends Piece {
    public Rook(Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.ROOK, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return null;
    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }
}
