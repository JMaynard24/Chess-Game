package chessPlayer;

import chessBoard.Board;
import chessBoard.Move;
import chessBoard.Move.QueenSideCastleMove;
import chessBoard.Tile;
import chessPieces.Piece;
import chessPieces.Rook;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhitePlayer extends Player {

    public WhitePlayer(Board board, Collection<Move> whiteLegalMoves, Collection<Move> blackLegalMoves) {
        
    super(board, whiteLegalMoves, blackLegalMoves);
        
    }

    @Override
    public Collection<Piece> getActivePieces() {
        
        return this.board.getWhitePieces(); 
        
    }

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentsLegals) {
        
        final List<Move> kingCastles = new ArrayList<>(); 
        
        if(this.playerKing.isFirstMove() && !this.isInCheck()) {
            // white's king side castle 
            if(!this.board.getTile(61).isTileOccupied() && !this.board.getTile(62).isTileOccupied()) {
                
                final Tile rookTile = this.board.getTile(63); 
                
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                       
                    if(Player.calculateAttacksOnTile(61, opponentsLegals).isEmpty() &&
                       Player.calculateAttacksOnTile(61, opponentsLegals).isEmpty() &&
                       rookTile.getPiece().getPieceType().isRook()) {
                        
                        kingCastles.add(new Move.KingSideCastleMove(this.board, this.playerKing, 62, (Rook)rookTile.getPiece(), rookTile.getTileCoordinate(), 59));
                        
                        
                        
                    }
                    
                }
                
            }
        
        if(!this.board.getTile(59).isTileOccupied() && 
            !this.board.getTile(58).isTileOccupied() && 
            !this.board.getTile(57).isTileOccupied()) {
            
            final Tile rookTile = this.board.getTile(56); 
            
            if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                
                kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 58, (Rook)rookTile.getPiece(), rookTile.getTileCoordinate(), 61)); 
                
            }
            
            
        }
            
        }
       
        return kingCastles; 
          
    }

    private boolean isInCheck() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
