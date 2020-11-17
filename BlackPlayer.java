package chessPlayer;

import chessBoard.Board;
import chessBoard.Move;
import chessBoard.Move.KingSideCastleMove;
import chessBoard.Move.QueenSideCastleMove;
import chessBoard.Tile;
import chessPieces.Piece;
import chessPieces.Rook;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlackPlayer extends Player {

    public BlackPlayer(Board board, Collection<Move> whiteLegalMoves, Collection<Move> blackLegalMoves) {
        
    super(board, blackLegalMoves, whiteLegalMoves);   
        
    }

    @Override
    public Collection<Piece> getActivePieces() {
        
        return this.board.getBlackPieces(); 
        
    }

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentsLegals) {
        
                final List<Move> kingCastles = new ArrayList<>(); 
        
        if(this.playerKing.isFirstMove() && !this.isInCheck()) {
            // black's king side castle 
            if(!this.board.getTile(5).isTileOccupied() && !this.board.getTile(6).isTileOccupied()) {
                
                final Tile rookTile = this.board.getTile(7); 
                
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                       
                    if(Player.calculateAttacksOnTile(5, opponentsLegals).isEmpty() &&
                       Player.calculateAttacksOnTile(6, opponentsLegals).isEmpty() &&
                       rookTile.getPiece().getPieceType().isRook()) {
                        
                        kingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 6, (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 5)); 
                        
                        
                    }
                    
                }
                
            }
        // black's Queen side castle
        if(!this.board.getTile(1).isTileOccupied() && 
            !this.board.getTile(2).isTileOccupied() && 
            !this.board.getTile(3).isTileOccupied()) {
            
            final Tile rookTile = this.board.getTile(0); 
            
            if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                
                // TODO castle move 
                kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 2, (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 3)); 
                
            }
            
            
        }
            
        }
       
        return kingCastles; 
        
    }

    private boolean isInCheck() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
