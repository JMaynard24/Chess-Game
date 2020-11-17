package chessBoard;
import chessPieces.Alliance;
import chessPieces.Bishop;
import chessPieces.King;
import chessPieces.Knight;
import chessPieces.Pawn;
import chessPieces.Piece;
import chessPieces.Queen;
import chessPieces.Rook;
import chessPlayer.BlackPlayer;
import chessPlayer.WhitePlayer;
import java.util.*;

public class Board {

    private final List<Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    
    private final WhitePlayer whitePlayer; 
    private final BlackPlayer blackPlayer; 

    private Board(Builder builder){
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
        
        final Collection<Move> whiteLegalMoves = calculateLegalMoves(this.whitePieces); 
        final Collection<Move> blackLegalMoves = calculateLegalMoves(this.blackPieces);
        
        this.whitePlayer = new WhitePlayer(this, whiteLegalMoves, blackLegalMoves);
        this.blackPlayer = new BlackPlayer(this, whiteLegalMoves, blackLegalMoves); 
        
    }
    
    @Override
    public String toString() {
        
        final StringBuilder builder = new StringBuilder(); 
        
        for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
            
            final String tileTxt = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s", tileTxt)); 
            
            if((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
                
                builder.append("\n");
                
            }
            
            
        }
        
        return builder.toString(); 
        
    }

    private Collection<Piece> calculateActivePieces(final List<Tile> gameBoard, final Alliance alliance){

        final List<Piece> activePieces = new ArrayList<>();

        for(final Tile tile : gameBoard) {
            if (tile.isTileOccupied()) {
                final Piece piece = tile.getPiece();
                if (piece.getPieceAlliance() == alliance) {
                    activePieces.add(piece);
                }
            }
        }
        return activePieces;

    }

    public Tile getTile(final int tileCoordinate){
        
        return gameBoard.get(tileCoordinate);
        
    }

    private static List<Tile> createGameBoard(final Builder builder){
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return Arrays.asList(tiles);
    }

    public static Board createStandardBoard (){
        
        final Builder builder = new Builder(); 
        
        builder.setPiece(new Rook(0, Alliance.BLACK)); 
        builder.setPiece(new Knight(1, Alliance.BLACK));
        builder.setPiece(new Bishop(2, Alliance.BLACK)); 
        builder.setPiece(new Queen(3, Alliance.BLACK));        
        builder.setPiece(new King(4, Alliance.BLACK)); 
        builder.setPiece(new Bishop(5, Alliance.BLACK));
        builder.setPiece(new Knight(6, Alliance.BLACK)); 
        builder.setPiece(new Rook(7, Alliance.BLACK)); 
        builder.setPiece(new Pawn(8, Alliance.BLACK)); 
        builder.setPiece(new Pawn(9, Alliance.BLACK));
        builder.setPiece(new Pawn(10, Alliance.BLACK)); 
        builder.setPiece(new Pawn(11, Alliance.BLACK));        
        builder.setPiece(new Pawn(12, Alliance.BLACK)); 
        builder.setPiece(new Pawn(13, Alliance.BLACK));
        builder.setPiece(new Pawn(14, Alliance.BLACK)); 
        builder.setPiece(new Pawn(15, Alliance.BLACK));  
        
        builder.setPiece(new Pawn(48, Alliance.WHITE)); 
        builder.setPiece(new Pawn(49, Alliance.WHITE));
        builder.setPiece(new Pawn(50, Alliance.WHITE)); 
        builder.setPiece(new Pawn(51, Alliance.WHITE));        
        builder.setPiece(new Pawn(52, Alliance.WHITE)); 
        builder.setPiece(new Pawn(53, Alliance.WHITE));
        builder.setPiece(new Pawn(54, Alliance.WHITE)); 
        builder.setPiece(new Pawn(55, Alliance.WHITE)); 
        builder.setPiece(new Rook(56, Alliance.WHITE)); 
        builder.setPiece(new Knight(57, Alliance.WHITE));
        builder.setPiece(new Bishop(58, Alliance.WHITE)); 
        builder.setPiece(new Queen(59, Alliance.WHITE));        
        builder.setPiece(new King(60, Alliance.WHITE)); 
        builder.setPiece(new Bishop(61, Alliance.WHITE));
        builder.setPiece(new Knight(62, Alliance.WHITE)); 
        builder.setPiece(new King(63, Alliance.WHITE)); 

        builder.setMoveMaker(Alliance.WHITE); 
        return builder.build(); 

    }
    
    public Iterable<Move> getAllLegalMoves() {
        
        return this.whitePlayer.getLegalMoves() + this.blackPlayer.getLegalMoves(); 
        
    }

    private Collection<Move> calculateLegalMoves(Collection<Piece> pieces) {
       
        final List<Move> legalMoves = new ArrayList(); 
        
        pieces.forEach(piece -> {
            legalMoves.addAll(piece.calculateAvailableMoves(this));
        });
        
        return legalMoves; 
        
    }

    public Collection<Piece> getBlackPieces() {
       
        return this.blackPieces; 
        
    }

    public Collection<Piece> getWhitePieces() {
        
        return this.whitePieces; 
        
    }

    Object currentPlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class Builder {

        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;
        private Pawn enPassantPawn; 

        public Builder() {
            
            this.boardConfig = new HashMap<>();
            
        }

        public Builder setPiece(final Piece piece){
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance alliance) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Board build() {
            return new Board(this);
        }

        void setEnPassantPawn(Pawn movedPawn) {
            this.enPassantPawn = movedPawn; 
        }
    }
}
