package chessgame.chessPieces;
import chessgame.chessBoard.Board;
import chessgame.chessBoard.BoardUtils;
import chessgame.chessBoard.Move;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] MOVE_VECTORS = { 8 };
    
    public Pawn(int pieceLocation, Alliance pieceAlliance) {
       
        super(pieceLocation, pieceAlliance);
        
    }

    @Override
    public Collection<Move> calculateAvailableMoves(Board board) {
        
        final List<Move> legalMoves = new ArrayList<>(); 
        
        for(final int coordinateOffset : MOVE_VECTORS) {
            
            final int destinationCoordinate = this.pieceLocation + (this.getPieceAlliance().getDirection() * coordinateOffset); // The multiplication accounts for the fact the pawns may only move in one direction
           
            if(!BoardUtils.isValidTileCoordinate(destinationCoordinate)) {
                
                continue;
                
            }
            
            if(!board.getTile(destinationCoordinate).isTileOccupied() && coordinateOffset == 8) {
            
                // Promotion & En passant not implemented yet 
                
                legalMoves.add(new Move.PieceMove(board, this, destinationCoordinate)); 
            
            } 
            
            else if(coordinateOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.pieceLocation] && this.getPieceAlliance().isBlack()) || 
                    (BoardUtils.SEVENTH_ROW[this.pieceLocation] && this.getPieceAlliance().isWhite())) { //True or true conditions to be filled out later
                   
                final int nextDestinationCoordinate = this.pieceLocation + (this.getPieceAlliance().getDirection() * 8);
                
                if(!board.getTile(nextDestinationCoordinate).isTileOccupied() && 
                   !board.getTile(destinationCoordinate).isTileOccupied()) {
                    
                    legalMoves.add(new Move.PieceMove(board, this, destinationCoordinate)); 
                    
                }
            
            }
            
            else if(coordinateOffset == 7 &&
                    !( (BoardUtils.EIGHTH_COLUMN[pieceLocation] && this.getPieceAlliance().isWhite()) ||
                    (BoardUtils.FIRST_COLUMN[pieceLocation] && this.getPieceAlliance().isBlack()) ) ) {
                
                    if(board.getTile(destinationCoordinate).isTileOccupied()) {
                        
                        final Piece candidatePiece = board.getTile(destinationCoordinate).getPiece(); 
                        
                        if(this.getPieceAlliance() != candidatePiece.getPieceAlliance()) {
                            
                            legalMoves.add(new Move.AttackMove(board, this, destinationCoordinate, candidatePiece)); 
                            
                        }
                        
                    }
              
            }
            
            else if(coordinateOffset == 9 &&
                    !( (BoardUtils.EIGHTH_COLUMN[pieceLocation] && this.getPieceAlliance().isBlack()) ||
                    (BoardUtils.FIRST_COLUMN[pieceLocation] && this.getPieceAlliance().isWhite()) ) )  {
                
                    if(board.getTile(destinationCoordinate).isTileOccupied()) {
                        
                        final Piece candidatePiece = board.getTile(destinationCoordinate).getPiece(); 
                        
                        if(this.getPieceAlliance() != candidatePiece.getPieceAlliance()) {
                            
                            legalMoves.add(new Move.AttackMove(board, this, destinationCoordinate, candidatePiece)); 
                            
                        }
                        
                    }
                
            }
        
        }    
    
        return legalMoves; 
        
    }
    
}
