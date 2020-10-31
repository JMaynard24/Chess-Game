package chessPieces;

import chessBoard.Board;
import chessBoard.BoardUtils;
import chessBoard.Move;
import chessBoard.Tile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Queen extends Piece {

    private final static int[] MOVE_VECTORS = { -9, -7, 7, 9, -8, -1, 1, 8 };
    
    public Queen(final int pieceLocation, final Alliance pieceAlliance) {
        super(pieceLocation, pieceAlliance, PieceType.QUEEN);
    }
 
    @Override
    public Collection<Move> calculateAvailableMoves(final Board board) {
        
        final List<Move> legalMoves = new ArrayList<>(); 
        
        for(final int coordinateOffset: MOVE_VECTORS) {
            
            int destinationCoordinate = this.pieceLocation; 
            
            while(BoardUtils.isValidTileCoordinate(destinationCoordinate)) {
                
                if( isFirstColumnError(destinationCoordinate, coordinateOffset) || isEighthColumnError(destinationCoordinate, coordinateOffset)) {
                    
                    break; 
                    
                }
                
                destinationCoordinate += coordinateOffset; 
                
                if (BoardUtils.isValidTileCoordinate(destinationCoordinate)) {
                    
                    final Tile destinationTile = board.getTile(destinationCoordinate); 
                
                    if (!destinationTile.isTileOccupied()) {

                        legalMoves.add(new Move.PieceMove(board, this, destinationCoordinate)); 

                    }

                    else {

                        final Piece destinationPiece = destinationTile.getPiece(); 
                        final Alliance destinationPieceAlliance = destinationPiece.getPieceAlliance(); 

                        if(this.pieceAlliance != destinationPieceAlliance) {

                            legalMoves.add(new Move.AttackMove(board, this, destinationCoordinate, destinationPiece)); 

                        }
                        
                    break; // Stops given that a specific tile is occupied. (Friend/Foe)   
                        
                    } 
                }     
            } 
        }

        return legalMoves;
        
    }
    
    private static boolean isFirstColumnError(final int currentPos, final int candidatePos) {
        
        return BoardUtils.FIRST_COLUMN[currentPos] && (candidatePos == -9 || candidatePos == 7 || candidatePos == -1);
        
    }
    
    private static boolean isEighthColumnError(final int currentPos, final int candidatePos) {
        
        return BoardUtils.EIGHTH_COLUMN[currentPos] && (candidatePos == -7 || candidatePos == 9 || candidatePos == 1);
        
    }    
    
     @Override
    public String toString() {
        
       return PieceType.QUEEN.toString(); 
        
    }   
    
}
