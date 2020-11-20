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

public class Rook extends Piece {

    private final static int[] MOVE_VECTORS = { -8, -1, 1, 8 };

    public Rook(Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.ROOK, piecePosition, pieceAlliance, true);
    }

    public Rook(final Alliance pieceAlliance, final int piecePosition, final boolean isFirstMove){
        super(PieceType.ROOK, piecePosition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int coordinateOffset: MOVE_VECTORS) {
            int destinationCoordinate = this.piecePosition;
            while(BoardUtils.isValidTileCoordinate(destinationCoordinate)) {
                if( isFirstColumnError(destinationCoordinate, coordinateOffset) || isEighthColumnError(destinationCoordinate, coordinateOffset)) {
                    break;
                }
                destinationCoordinate += coordinateOffset;
                if (BoardUtils.isValidTileCoordinate(destinationCoordinate)) {
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
                        break; // Stops given that a specific tile is occupied. (Friend/Foe)
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    public Piece movePiece(Move move) {
        return new Rook(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    private static boolean isFirstColumnError(final int currentPos, final int candidatePos) {

        return BoardUtils.FIRST_COLUMN[currentPos] && (candidatePos == -1);

    }

    private static boolean isEighthColumnError(final int currentPos, final int candidatePos) {

        return BoardUtils.EIGHTH_COLUMN[currentPos] && (candidatePos == 1);

    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }
}
