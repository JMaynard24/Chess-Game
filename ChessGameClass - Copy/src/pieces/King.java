package pieces;

import board.BoardUtils;
import board.Tile;
import com.google.common.collect.ImmutableList;
import player.Alliance;
import board.Board;
import board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static board.Move.*;

public class King extends Piece {

    private final static int[] MOVE_VECTORS = { 1, 7, 8, 9, -1, -7, -8, -9 };

    public King(final Alliance pieceAlliance, final int piecePosition) {

        super(PieceType.KING, piecePosition, pieceAlliance, true);
    }

    public King(final Alliance pieceAlliance, final int piecePosition, final boolean isFirstMove) {

        super(PieceType.KING, piecePosition, pieceAlliance, isFirstMove);
    }


    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        for(final int candidateOffset : MOVE_VECTORS) {
            final int destinationCoordinate = this.piecePosition + candidateOffset;
            if( isFirstColumnError(this.piecePosition, candidateOffset) || isEighthColumnError(this.piecePosition, candidateOffset) ) {
                continue;
            }
            if(BoardUtils.isValidTileCoordinate(destinationCoordinate)) {
                final Tile destinationTile = board.getTile(destinationCoordinate);
                if (!destinationTile.isTileOccupied()) {
                    legalMoves.add(new PieceMove(board, this, destinationCoordinate));
                }
                else {
                    final Piece destinationPiece = destinationTile.getPiece();
                    final Alliance destinationPieceAlliance = destinationPiece.getPieceAlliance();
                    if(this.pieceAlliance != destinationPieceAlliance) {
                        legalMoves.add(new MajorAttackMove(board, this, destinationCoordinate, destinationPiece));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    public Piece movePiece(Move move) {
        return new King(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    private static boolean isFirstColumnError(final int currentPos, final int candidateOffset) {

        return BoardUtils.FIRST_COLUMN[currentPos] && ((candidateOffset == -17) || (candidateOffset == -10) || (candidateOffset == 6) || (candidateOffset == 15));

    }

    private static boolean isSecondColumnError(final int currentPos, final int candidateOffset) {

        return BoardUtils.SECOND_COLUMN[currentPos] && ((candidateOffset == -10) || (candidateOffset == 6));

    }

    private static boolean isSeventhColumnError(final int currentPos, final int candidateOffset) {

        return BoardUtils.SEVENTH_COLUMN[currentPos] && ((candidateOffset == -6) || (candidateOffset == 10));

    }

    private static boolean isEighthColumnError(final int currentPos, final int candidateOffset) {

        return BoardUtils.EIGHTH_COLUMN[currentPos] && ((candidateOffset == -15) || (candidateOffset == -6) || (candidateOffset == 10) || (candidateOffset == 17));

    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }
}
