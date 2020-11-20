package pieces;

import board.BoardUtils;
import com.google.common.collect.ImmutableList;
import player.Alliance;
import board.Board;
import board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static board.Move.*;

public class Pawn extends Piece {

    private final static int[] MOVE_VECTORS = {8, 16, 7, 9};

    public Pawn(Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.PAWN, piecePosition, pieceAlliance, true);
    }

    public Pawn(Alliance pieceAlliance, final int piecePosition, final boolean isFirstMove) {
        super(PieceType.PAWN, piecePosition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for(final int coordinateOffset : MOVE_VECTORS) {
            final int destinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * coordinateOffset); // The multiplication accounts for the fact the pawns may only move in one direction
            if (!BoardUtils.isValidTileCoordinate(destinationCoordinate)) {
                continue;
            }
            if (!board.getTile(destinationCoordinate).isTileOccupied() && coordinateOffset == 8) {
                if (this.pieceAlliance.isPawnPromotionSquare(destinationCoordinate)) {
                    legalMoves.add(new PawnPromotion(new PawnMove(board, this, destinationCoordinate)));
                } else {
                    legalMoves.add(new PieceMove(board, this, destinationCoordinate));
                }
            } else if (coordinateOffset == 16 && this.isFirstMove() &&
                    ((BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                            (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite()))) { //True or true conditions to be filled out later
                final int nextDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * 8);
                if (!board.getTile(nextDestinationCoordinate).isTileOccupied() &&
                        !board.getTile(destinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new PawnJump(board, this, destinationCoordinate));
                }
            } else if (coordinateOffset == 7 &&
                    !((BoardUtils.EIGHTH_COLUMN[piecePosition] && this.getPieceAlliance().isWhite()) ||
                            (BoardUtils.FIRST_COLUMN[piecePosition] && this.getPieceAlliance().isBlack()))) {
                if (board.getTile(destinationCoordinate).isTileOccupied()) {
                    final Piece candidatePiece = board.getTile(destinationCoordinate).getPiece();
                    if (this.getPieceAlliance() != candidatePiece.getPieceAlliance()) {
                        if (this.pieceAlliance.isPawnPromotionSquare(destinationCoordinate)) {
                            legalMoves.add(new PawnPromotion(new PawnAttackMove(board, this, destinationCoordinate, candidatePiece)));
                        } else {
                            legalMoves.add(new PawnAttackMove(board, this, destinationCoordinate, candidatePiece));
                        }
                    }
                }
                } else if (board.getEnPassantPawn() != null) {
                    if (board.getEnPassantPawn().getPiecePosition() == (this.piecePosition + (this.pieceAlliance.getOppositeDirection()))) {
                        final Piece pieceOnCandidate = board.getEnPassantPawn();
                        if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                            legalMoves.add(new PawnEnPassantAttackMove(board, this, destinationCoordinate, pieceOnCandidate));
                        }
                    }
            } else if (coordinateOffset == 9 &&
                    !((BoardUtils.EIGHTH_COLUMN[piecePosition] && this.getPieceAlliance().isBlack()) ||
                            (BoardUtils.FIRST_COLUMN[piecePosition] && this.getPieceAlliance().isWhite()))) {
                if (board.getTile(destinationCoordinate).isTileOccupied()) {
                    final Piece candidatePiece = board.getTile(destinationCoordinate).getPiece();
                    if (this.getPieceAlliance() != candidatePiece.getPieceAlliance()) {
                        if (this.pieceAlliance.isPawnPromotionSquare(destinationCoordinate)) {
                            legalMoves.add(new PawnPromotion(new PawnAttackMove(board, this, destinationCoordinate, candidatePiece)));
                        } else {
                            legalMoves.add(new PawnAttackMove(board, this, destinationCoordinate, candidatePiece));
                        }
                    }
                    } else if (board.getEnPassantPawn() != null) {
                        if (board.getEnPassantPawn().getPiecePosition() == (this.piecePosition - (this.pieceAlliance.getOppositeDirection()))) {
                            final Piece pieceOnCandidate = board.getEnPassantPawn();
                            if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                                legalMoves.add(new PawnEnPassantAttackMove(board, this, destinationCoordinate, pieceOnCandidate));
                            }
                        }
                    }
                }
            }

        return ImmutableList.copyOf(legalMoves);

    }

    public Piece movePiece(Move move) {
        return new Pawn(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }

    public Piece getPromotionPiece() {
        return new Queen(this.pieceAlliance, this.piecePosition, false);
    }
}
