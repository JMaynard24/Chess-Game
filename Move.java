package chessBoard;

import chessBoard.Board.Builder;
import chessPieces.Pawn;
import chessPieces.Piece;
import chessPieces.Rook;

public abstract class Move {
    
    
    final Board board; // Incoming board
    final Piece movedPiece; 
    final int destination; 
    
    public static final Move NULL_MOVE = new NullMove(); 
    
    
    private Move(final Board board, final Piece movedPiece, final int destination) {
        
        this.board = board; 
        this.movedPiece = movedPiece; 
        this.destination = destination; 
        
    }
    
    @Override 
    public int hashCode() {
        
        final int prime = 31; 
        int result = 1; 
        result = prime * result + this.destination;
        result = prime * result + this.movedPiece.hashCode(); 
        return result; 
        
        
    }
    
    @Override 
    public boolean equals(final Object other) {
        
        if(this == other) {
            
            return true; 
            
        }
        
        if(!(other instanceof Move)) {
            
            return false; 
            
        }
        
        final Move otherMove = (Move) other; 
        return getCurrentCoord() == otherMove.getDestinationCoord() && getMovedPiece().equals(otherMove.getMovedPiece()); 
        
    }
    
    public boolean isAttack() {
        
        return false; 
        
    }
    
    public boolean isCastlingMove() {
        
        return false; 
        
    }
    
    public Piece getAttackedPiece() {
        
        return null; 
        
    }
    
    public int getCurrentCoord() {
        
        return this.movedPiece.getPiecePosition(); 
        
    }
    
    public Piece getMovedPiece() {
        
        return movedPiece; 
        
    }
    
    public int getDestinationCoord() {
        
        return this.destination; 
        
    }

    public Board executeMove() {
        final Builder builder = new Builder();
        for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
            if(!this.movedPiece.equals(piece)) {
                
                builder.setPiece(piece);
                
            }
        }
        for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
        return builder.build();
    }
  
    public static final class PieceMove extends Move {
        
        public PieceMove(final Board board, final Piece movedPiece, final int destination) {
            
            super(board, movedPiece, destination);
            
        }
        
        
    }
    
    public static class AttackMove extends Move {
        
        final Piece attackedPiece; 
        
        public AttackMove(final Board board, final Piece movedPiece, final int destination, final Piece attackedPiece) {
            
            super(board, movedPiece, destination);
            this.attackedPiece = attackedPiece; 
            
        }
        
        @Override
        public boolean isAttack() {
            
            return true; 
            
        }
        
        @Override 
        public Board executeMove() {
            
            return null; 
            
        }
        
        @Override
        public Piece getAttackedPiece() {
            
            return this.attackedPiece; 
            
        }
        
        @Override
        public int hashCode() {
            
            return this.attackedPiece.hashCode() + super.hashCode(); 
            
        }
        
        @Override 
        public boolean equals(final Object other) {
            
            if(this == other) {
                
                return true; 
               
            }
            
            if(!(other instanceof AttackMove)) {
                
                return false; 
                
            }
            
            final AttackMove otherAttackMove = (AttackMove) other; 
            return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece()); 
            
            
        }
    }
    
    public static final class PawnMove extends Move {
        
        public PawnMove(final Board board, final Piece movedPiece, final int destination) {
            super(board, movedPiece, destination);
        }
    }
    
    public static class PawnAttackMove extends AttackMove {
        
            public PawnAttackMove(final Board board, final Piece movedPiece, final int destination, final Piece attackedPiece) {
                super(board, movedPiece, destination, attackedPiece);
            }
        
        
        
    }
    
    public static final class PawnEnPassantAttackMove extends PawnAttackMove {
        
        public PawnEnPassantAttackMove(final Board board, final Piece movedPiece, final int destination, final Piece attackedPiece) {
            super(board, movedPiece, destination, attackedPiece);
        }
        
        
        
    }
    
    public static final class PawnJump extends Move {
        
        public PawnJump(final Board board, final Piece movedPiece, final int destination) {
            super(board, movedPiece, destination);
        }
        
        public Board execute() {
            
            final Builder builder = new Builder(); 
            
            for(final Piece piece : this.board.currentPlayer().getActivePieces()) {
                
                if(!this.movedPiece.equals(piece)) {
                    
                    builder.setPiece(piece); 
                    
                }
                
            }
            
            for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
                
                builder.setPiece(piece); 
                
            }
            
            final Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this); 
            builder.setPiece(movedPawn); 
            builder.setEnPassantPawn(movedPawn); 
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance()); 
            return builder.build();          
            
        }
        
    }   
    
    public static class CastleMove extends Move {
        
        protected final Rook castleRook; 
        protected final int castleRookStart; 
        protected final int castleRookDestination; 
        
        public CastleMove(final Board board, final Piece movedPiece, final int destination,
                          final Rook castleRook, final int castleRookStart, final int castleRookDestination) {
            
            super(board, movedPiece, destination);
            this.castleRook = castleRook; 
            this.castleRookStart = castleRookStart; 
            this.castleRookDestination = castleRookDestination; 
            
        }
        
        public Rook getCastleRook() {
            
            return this.castleRook; 
            
        }
        
        @Override 
        public boolean isCastlingMove() {
            
            return true; 
            
        }
        
        @Override
        public Board executeMove() {
            
            final Builder builder = new Builder(); 
            for(final Piece piece : this.board.currentPlayer().getActivePieces()) {
                
                if(!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
                    
                    builder.setPiece(piece); 
                    
                }
                
            }
            
            for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
                
                builder.setPiece(piece);
                
            }
            
            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setPiece(new Rook(this.castleRookDestination, this.castleRook.getPieceAlliance())); 
            builder.setMoveMaker(this.board.currentplayer().getOpponent().getAlliance()); 
            
            return builder.build(); 
            
        }
        
    }    
    
    public static final class KingSideCastleMove extends CastleMove {
        
        public KingSideCastleMove(final Board board, final Piece movedPiece, final int destination, final Rook castleRook, final int castleRookStart, final int castleRookDestination) {
            
            super(board, movedPiece, destination, castleRook, castleRookStart, castleRookDestination);
            
        }
        
        @Override 
        public String toString() {
            
            return "0-0"; 
            
        }
        
    }   

    public static final class QueenSideCastleMove extends CastleMove {
        
        public QueenSideCastleMove(final Board board, final Piece movedPiece, final int destination, final Rook castleRook, final int castleRookStart, final int castleRookDestination) {
            
            super(board, movedPiece, destination, castleRook, castleRookStart, castleRookDestination);
            
        }
        
                
        @Override 
        public String toString() {
            
            return "0-0-0"; 
            
        }
    } 
    
    public static final class NullMove extends Move {
        
        public NullMove() {
            
            super(null, null, -1);
           
            
        }
        
        @Override 
        public Board executeMove() {
         
            throw new RuntimeException("Can't execute null move");
            
        }
        
    }   
    
    public static class MoveFactory {
        
        private MoveFactory() {
            
           throw new RuntimeException("Not instantiable");  
            
        }
        
        public static Move createMove(final Board board, final int currentCoord, final int destinationCoord) {
            
            for(final Move move : board.getAllLegalMoves()) {
                
                if(move.getCurrentCoord() == currentCoord && move.getDestinationCoord() == destinationCoord) {
                    
                    return move; 
                    
                }
                
            }
            
            return NULL_MOVE;
            
        }
        
    }
    
}
