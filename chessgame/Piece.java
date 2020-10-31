
package chessPieces;

import chessBoard.Board;
import chessBoard.Move;
import java.util.Collection;

public abstract class Piece {

    
    protected final int pieceLocation; // Defines a piece's position on the board. 
    protected final Alliance pieceAlliance;  // Defines the side of the board, or player a piece is affiliated with. 
    protected final boolean isFirstMove; 
    protected final PieceType pieceType; 
    
    Piece(final int pieceLocation, final Alliance pieceAlliance, final PieceType pieceType) {
        
        
        this.pieceLocation = pieceLocation; 
        this.pieceAlliance = pieceAlliance;
        this.isFirstMove = false; 
        this.pieceType = pieceType; 
        
        
    }
    
    public abstract Collection<Move> calculateAvailableMoves(final Board board);  // How specific pieces will calculate possible moves. 
    
    public Alliance getPieceAlliance() {
        
        return this.pieceAlliance; 
        
    }

    public boolean isFirstMove() {
        
        return this.isFirstMove; 
        
    }
    
    public Integer getPiecePosition() {
        
        return this.pieceLocation; 
        
    }

    public PieceType getPieceType() {
        
        return this.pieceType; 
        
    }
    
    public enum PieceType {
        
        PAWN("P") {
            @Override
            public boolean isKing() {
                return false; 
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false; 
            }
        }, 
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false; 
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false; 
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false; 
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true; 
            }
        };
        
        private final String pieceName; 
        
        PieceType(final String pieceName) {
    
        this.pieceName = pieceName; 
    
        }
        
        @Override
        public String toString() {
            
            return this.pieceName; 
            
        }
        
        public abstract boolean isKing();
        
    }
    
}
