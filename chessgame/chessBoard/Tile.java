/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame.chessBoard;
import chessgame.chessPieces.Piece;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author collinflack
 */
public abstract class Tile {


    protected final int tileCoord; 
    
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
    
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        
        for(int i = 0; i < 64; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        
        return Collections.unmodifiableMap(emptyTileMap);
        
    }
    
    public static Tile createTile(final int tileCoord, final Piece piece) {
        
        return piece != null ? new OccupiedTile(tileCoord, piece) : EMPTY_TILES_CACHE.get(tileCoord);
        
    }
    
    private Tile(int tileCoord) {

        this.tileCoord = tileCoord; 

    }

    public abstract boolean isTileOccupied(); // abstact, therefore method defined in sub-class

    public abstract Piece getPiece(); 

    public static final class EmptyTile extends Tile {

        EmptyTile(final int coord) {
            super(coord);
        }

        @Override
        public boolean isTileOccupied() {
            return false; 
        }    

        @Override
        public Piece getPiece() {
            return null; 
        }      
    
    }

    public static final class OccupiedTile extends Tile {

       private final Piece pieceOnTile;

        OccupiedTile(int tileCoord, Piece pieceOnTile) {
            super(tileCoord);
            this.pieceOnTile = pieceOnTile; 
        }

        
        @Override
        public boolean isTileOccupied() {
            return true; 
        }    

        @Override
        public Piece getPiece() {
            return this.pieceOnTile; 
        }      
        
    }

}
