package pieces;

import Alliance;
import board.Board;
import board.Move;

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
