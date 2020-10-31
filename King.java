package chessgame.chessPieces;
import chessgame.chessBoard.Board;
import chessgame.chessBoard.BoardUtils;
import chessgame.chessBoard.Move;
import chessgame.chessBoard.Tile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    private final static int[] MOVE_VECTORS = { 1, 7, 8, 9, -1, -7, -8, -9 };
    
    public King(int pieceLocation, Alliance pieceAlliance) {
        
        super(pieceLocation, pieceAlliance);
        
    }

    @Override
    public Collection<Move> calculateAvailableMoves(Board board) {
     
        final List<Move> legalMoves = new ArrayList<>(); 
        
        for(final int candidateOffset : MOVE_VECTORS) {
            
           final int destinationCoordinate = this.pieceLocation + candidateOffset; 
           
                if( isFirstColumnError(this.pieceLocation, candidateOffset) || isEighthColumnError(this.pieceLocation, candidateOffset`                         ) ) {
                    
                    continue;
                    
                }
           
           if(BoardUtils.isValidTileCoordinate(destinationCoordinate)) {
               
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
                    
                }
               
            }
            
        }
       
        return legalMoves; 
        
    }
    
    
    private static boolean isFirstColumnError(final int currentPos, final int candidateOffset) {
        
       return BoardUtils.FIRST_COLUMN[currentPos] && ((candidateOffset == -9) || (candidateOffset == -1) || (candidateOffset == 7));
        
    }
    
    private static boolean isEighthColumnError(final int currentPos, final int candidateOffset) {
        
         return BoardUtils.EIGHTH_COLUMN[currentPos] && ((candidateOffset == -7) || (candidateOffset == 1) || (candidateOffset == 9));
        
    } 
    
}
