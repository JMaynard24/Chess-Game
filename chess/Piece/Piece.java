
package chessgame.chessPieces;

import chessgame.Alliance; 
import chessgame.chessBoard.Board;
import chessgame.chessBoard.Move;
import java.util.List;







/**
 *
 * @author collinflack
 */
public abstract class Piece {

    
    protected final int pieceLocation; 
    protected final Alliance pieceAlliance; 
    
    
    Piece(final int pieceLocation, final Alliance pieceAlliance) {
        
        
        this.pieceLocation = pieceLocation; 
        this.pieceAlliance = pieceAlliance; 
        
        
    }
    
    public abstract List<Move> calculateAvailableMoves(final Board board);  // How specific pieces will calculate possible moves. 
    
}
