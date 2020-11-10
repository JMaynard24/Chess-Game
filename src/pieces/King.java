package pieces;

import Alliance;
import board.Board;
import board.Move;

import java.util.Collection;

public class King extends Piece {
    public King(Alliance pieceAlliance, final int piecePosition) {

        super(PieceType.KING, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return null;
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }
}
