package chessPieces;
import chessBoard.Tile;
import chessBoard.Board;
import chessBoard.BoardUtils;
import chessBoard.Move;
import chessBoard.Move.AttackMove;
import chessBoard.Move.PieceMove;
import java.util.ArrayList;
import java.util.List;


public class Knight extends Piece {

    private final static int[] POSSIBLE_MOVES = { -17, -15, -10, -6, 6, 10, 15, 17 };
    
    public Knight(final int pieceLocation, final Alliance pieceAlliance) {
        super(pieceLocation, pieceAlliance, PieceType.KNIGHT);
    }
  
    @Override    
    public List<Move> calculateAvailableMoves(final Board board) {   
        

        final List<Move> legalMoves = new ArrayList<>();
        
        for(final int currentCandidate : POSSIBLE_MOVES) {
            
            int destinationCoordinate; 
            destinationCoordinate = currentCandidate + this.pieceLocation; 
            if (BoardUtils.isValidTileCoordinate(destinationCoordinate)) {
            
                if( isFirstColumnError(this.pieceLocation, currentCandidate) || isSecondColumnError(this.pieceLocation, currentCandidate) || isSeventhColumnError(this.pieceLocation, currentCandidate) || isEighthColumnError(this.pieceLocation, currentCandidate)) {
                    
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
                        
                        legalMoves.add(new AttackMove(board, this, destinationCoordinate, destinationPiece)); 
                    
                    }
                    
                }
                
            }    
        }
        
        return legalMoves;
        
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
