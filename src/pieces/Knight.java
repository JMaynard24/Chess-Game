package pieces;

import Alliance;
import board.Board;
import board.Move;

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
