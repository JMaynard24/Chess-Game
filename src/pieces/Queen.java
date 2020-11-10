package pieces;

import Alliance;
import board.Board;
import board.Move;

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
