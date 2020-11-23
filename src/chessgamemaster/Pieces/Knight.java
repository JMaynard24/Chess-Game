package chessgamemaster.Pieces;

import chessgamemaster.Board.BoardUtils;
import chessgamemaster.Board.Tile;
import chessgamemaster.Player.Alliance;
import chessgamemaster.Board.Board;
import chessgamemaster.Board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static chessgamemaster.Board.Move.*;
import com.google.common.collect.ImmutableList;

public class Knight extends Piece {

    private final static int[] MOVE_VECTORS = {-17, -15, -10, -6, 10, 15, 17};


    public Knight(Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.KNIGHT, piecePosition, pieceAlliance,true);
    }

    public Knight(Alliance pieceAlliance, final int piecePosition, final boolean isFirstMove) {
        super(PieceType.KNIGHT, piecePosition, pieceAlliance, isFirstMove);
    }


    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidate : MOVE_VECTORS) {

            int destinationCoordinate;
            destinationCoordinate = currentCandidate + this.piecePosition;
            if (BoardUtils.isValidTileCoordinate(destinationCoordinate)) {
                if(isFirstColumnError(this.piecePosition, currentCandidate) ||
                        isSecondColumnError(this.piecePosition, currentCandidate) ||
                        isSeventhColumnError(this.piecePosition, currentCandidate) ||
                        isEighthColumnError(this.piecePosition, currentCandidate)) {
                    continue;
                }
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
        return new Knight(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
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
        return PieceType.KNIGHT.toString();
    }
}

