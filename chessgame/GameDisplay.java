/**
 * The GameDisplay class is used to display the actual chess board and game play.
 * @author Elizabeth Dooley
 */

package Gui;

//imports from other classes
import Gui.FileManager;
import board.Board;
import board.Tile;
import board.BoardUtils;
import board.Move;
import pieces.Piece;
import player.MoveTransition;
import player.Player;

//imports from swing, awt, etc.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;



public class GameDisplay {
    //private final variables
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final TakenPiecesPanel takenPiecesPanel;
    private final MoveLog moveLog;
    
    //private variables
    private Board chessBoard;
    private Tile sourceTile;
    private Tile destinationTile;
    private Piece humanMovedPiece;
    
    //default color variables
    private final Color lightTileColor = Color.decode("#FFFFFF");
    private final Color darkTileColor = Color.decode("#000000");
    
    //dimension variables
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(1500, 1500);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 400);
    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);
    
    //Taken pieces panel variables
    private static final Color PANEL_COLOR = Color.decode("000000");
    private static final EtchedBorder PANEL_BORDER = new EtchedBorder(EtchedBorder.RAISED);
    private static final Dimension TAKEN_PIECES_DIMENSION = new Dimension(40, 80);
    
    //default file path
    private static String defaultPieceIconPath = "icons/pieces/B&W/";
    
    //created instance of FileManager() for reading files
    FileManager file = new FileManager();
    
    /**
     * This method is the default constructor for the GameDisplay class.
     * @param None
     * @return None
     */
    GameDisplay(){
        //creation of JFrame & adding of all it's elements
        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.chessBoard = Board.createStandardBoard();
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setJMenuBar(createMenuBar());
        this.takenPiecesPanel = new TakenPiecesPanel();
        this.gameFrame.add(this.takenPiecesPanel, BorderLayout.WEST);
        this.moveLog = new MoveLog();
        this.gameFrame.setVisible(false);
    }
    
    /**
     * This method is used to return the game display to the MenuSystem class.
     * @param None
     * @return JFrame This JFrame is the window for the chess game.
     */
    public JFrame getGameDisplay(){
        return(this.gameFrame);
    }
    
    /**
     * This method is used to get the piece color choice & set the file path accordingly.
     * @param None
     * @return String This will return the file path as a String
     */
    private String getPieceColor(){
       //strings for read options
       String baw = "blackwhite";
       String tab = "tanbrown";
       
       // if/if else/else statements to check for file read
       if(file.pieceColorRead().equals(baw)){
           return("icons/pieces/B&W/");
       }
       else if(file.pieceColorRead().equals(tab)){
           return("icons/pieces/T&B/");
       }
       else{ //should not get here if things work correctly
           return(defaultPieceIconPath);
       }
    }
    
     /**
     * This method is used to return the Color for the light color tiles on the chess board.
     * @param None
     * @return Color This will return an awt Color variable, used to color the chessboard.
     */
    private Color getLightColor(){
        //strings for file check
        String baw = "blackwhite";
        String tab = "tanbrown";
        String rab = "redblack";
        
        //checks for which color for light squares
        if(file.boardColorRead().equals(baw)){
            return(Color.decode("#FFFFFF"));
        }
        else if(file.boardColorRead().equals(tab)){
            return(Color.decode("#D6CBC1"));
        }
        else if(file.boardColorRead().equals(rab)){
            return(Color.decode("#A82A1E"));
        }
        else{ //if functioning correctly, should not get here
            return(lightTileColor);
        }
    }
    
    /**
     * This method is used to return the Color for the dark color tiles on the chess board.
     * @param None
     * @return Color This will return an awt Color variable, used to color the chessboard.
     */
    private Color getDarkColor(){
        //strings for file check
        String baw = "blackwhite";
        String tab = "tanbrown";
        String rab = "redblack";
        
        //checks for which color for dark squares
        if(file.boardColorRead().equals(baw)){
            return(Color.decode("#000000"));
        }
        else if(file.boardColorRead().equals(tab)){
            return(Color.decode("#4F3D2F"));
        }
        else if(file.boardColorRead().equals(rab)){
            return(Color.decode("#000000"));
        }
        else{ //if functioning correctly, should not get here
            return(darkTileColor);
        }
    }
    
    /**
     * This method is used to create and return the JMenuBar used to exit the chess game.
     * @param None
     * @return JMenuBar A menu bar, containing one menu with one item; used to exit game.
     */
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu exitMenu = new JMenu("Exit");
        JMenuItem exit = new JMenuItem("Exit Game");
        //when clicked, exits program
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitMenu.add(exit);
        menuBar.add(exitMenu);
        return(menuBar);
    }


    static class MoveLog {
    private final List<Move> moves;
     
    /**
     * This method is the default constructor.
     * @param None
     * @return None
     */
     MoveLog(){
         this.moves = new ArrayList<>();
     }
     
     /**
     * This method is used to get our moves from the move log, used to move pieces to graveyard.
     * @param None
     * @return List Returns the move log as a list.
     */
     public List<Move> getMoves(){
         return this.moves;
     }
     
     /**
     * This method is used to add a move to the move log.
     * @param move This method takes a Move class object, move.
     * @return None
     */
     public void addMove(final Move move){
         this.moves.add(move);
     }
    }
    
    private class BoardPanel extends JPanel{
    final List<TilePanel> boardTiles;
    
    /**
     * This method is the default constructor.
     * @param None
     * @return None
     */
    BoardPanel(){
        super(new GridLayout(8,8));
        this.boardTiles = new ArrayList<>();
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            final TilePanel tilePanel = new TilePanel(this, i);
            this.boardTiles.add(tilePanel);
            add(tilePanel);
        }
        setPreferredSize(BOARD_PANEL_DIMENSION);
        validate();
    }
    
    /**
     * This method is used to draw the chess board by adding the tiles.
     * @param board This method takes a Board class object, board.
     * @return None.
     */
    public void drawBoard(final Board board){
        removeAll();
        for(final TilePanel tilePanel : boardTiles){
            tilePanel.drawTile(board);
            add(tilePanel);
        }
        validate();
        repaint();
    }
    
}

private class TilePanel extends JPanel{
    private final int tileID;
    
    /**
     * This method is the parameterized constructor.
     * @param boardPanel Takes the BoardPanel object used to create the chess board.
     * @param tileID Takes the id number for the tile.
     * @return None
     */
    TilePanel(final BoardPanel boardPanel, final int tileID){
        super(new GridBagLayout());
        this.tileID = tileID;
        setPreferredSize(TILE_PANEL_DIMENSION);
        assignTileColor();
        assignTilePieceIcon(chessBoard);
        
        //if sqaure is left clicked, will select square; if it is right clicked it will deselect square.
        addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(final MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    sourceTile = null;
                    destinationTile = null;
                    humanMovedPiece = null;
                    }
                else if(SwingUtilities.isLeftMouseButton(e)){
                    if(sourceTile == null){
                        sourceTile = chessBoard.getTile(tileID);
                        humanMovedPiece = sourceTile.getPiece();
                        if(humanMovedPiece == null){
                            sourceTile = null;
                        }
                    }
                    else{
                        destinationTile = chessBoard.getTile(tileID);
                        final Move move = Move.MoveFactory.createMove(chessBoard, sourceTile.getTileCoordinate(), destinationTile.getTileCoordinate());
                        final MoveTransition transition = chessBoard.currentPlayer().makeMove(move);
                        if(transition.getMoveStatus().isDone()){
                            chessBoard = transition.getTransitionBoard();
                            moveLog.addMove(move);
                        }
                        sourceTile = null;
                        destinationTile = null;
                        humanMovedPiece = null;
                    }
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                            boardPanel.drawBoard(chessBoard);
                        }
                        
                    });
                }
            }
            
            //Unused mouse events
            @Override
            public void mousePressed(final MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        
        validate();
    }
    
    /**
     * This method is draws the tile, assigning it's color and piece.
     * @param board This method takes a board object.
     * @return None
     */
    public void drawTile(final Board board){
        assignTileColor();
        assignTilePieceIcon(board);
        validate();
        repaint();
    }
    
    /**
     * This method assigns the appropriate chess piece icon.
     * @param board This method takes a board object.
     * @return None
     */
    private void assignTilePieceIcon(final Board board){
        this.removeAll();
        if(board.getTile(this.tileID).isTileOccupied()){
            try {
                final BufferedImage image = 
                        ImageIO.read(new File(getPieceColor() + board.getTile(this.tileID).getPiece().getPieceAlliance().toString().substring(0, 1) 
                                + board.getTile(this.tileID).getPiece().toString() + ".png"));
                add(new JLabel(new ImageIcon(image)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * This method is used to highlight a square where a legal move can be played.
     * @param board This method takes a board object.
     * @return None
     */
    private void highlightLegals(final Board board){
        if(true){
            for(final Move move : pieceLegalMoves(board)){
                if(move.getDestinationCoordinate() == this.tileID){
                    try{
                        add(new JLabel(new ImageIcon(ImageIO.read(new File("icons/misc/green_dot.png")))));
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    private Collection<Move> pieceLegalMoves(final Board board){
        if(humanMovedPiece != null && humanMovedPiece.getPieceAlliance() == board.currentPlayer().getAlliance()) {
            return(humanMovedPiece.calculateLegalMoves(board));
        }
        return(Collections.EMPTY_LIST);
    }
    
    
    /**
     * This method assigns the color of the each individual tile.
     * @param None
     * @return None
     */
    private void assignTileColor(){
        if(BoardUtils.FIRST_ROW[this.tileID] || BoardUtils.THIRD_ROW[this.tileID] ||
                BoardUtils.FIFTH_ROW[this.tileID] || BoardUtils.SEVENTH_ROW[this.tileID]){
            setBackground(this.tileID % 2 == 0 ? getLightColor() : getDarkColor());
        }
        else if(BoardUtils.SECOND_ROW[this.tileID] || BoardUtils.FOURTH_ROW[this.tileID] ||
                BoardUtils.SIXTH_ROW[this.tileID] || BoardUtils.EIGHTH_ROW[this.tileID]){
            setBackground(this.tileID % 2 != 0 ? getLightColor() : getDarkColor());
        }
    }
}


    public class TakenPiecesPanel extends JPanel{
    private final JPanel northPanel;
    private final JPanel southPanel;
    
    /**
     * This method is the default constructor.
     * @param None
     * @return None
     */
        TakenPiecesPanel(){
        super(new BorderLayout());
        this.setBorder(PANEL_BORDER);
        this.northPanel = new JPanel(new GridLayout(8, 2));
        this.southPanel = new JPanel(new GridLayout(8, 2));
        this.northPanel.setBackground(PANEL_COLOR);
        this.southPanel.setBackground(PANEL_COLOR);
        add(this.northPanel, BorderLayout.NORTH);
        add(this.southPanel, BorderLayout.SOUTH);
        setPreferredSize(TAKEN_PIECES_DIMENSION);
    }
    
    /**
     * This method checks for if a piece is taken and puts it in the "graveyard" if needed.
     * @param movelog This function takes the move log to check for taken pieces.
     * @return Color This will return an awt Color variable, used to color the chessboard.
     */
        public void graveyardGUI(final MoveLog movelog){
        this.southPanel.removeAll();
        this.northPanel.removeAll();
        
        final List<Piece> whiteTakenPieces = new ArrayList<>();
        final List<Piece> blackTakenPieces = new ArrayList<>();
        
        //adds taken pieces to array list
        for(final Move move : movelog.getMoves()){
            if(move.isAttack()){
                final Piece takenPiece = move.getAttackedPiece();
                if(takenPiece.getPieceAlliance().isWhite()){
                    whiteTakenPieces.add(takenPiece);
                }
                else if(takenPiece.getPieceAlliance().isBlack()){
                    blackTakenPieces.add(takenPiece);
                }
            }
        }
        
        //sorts the white pieces by their "value", grouping like pieces together
        Collections.sort(whiteTakenPieces, new Comparator<Piece>(){
            @Override
            public int compare(Piece o1, Piece o2) {
                return Integer.compare(o1.getPieceValue(), o2.getPieceValue());
            }
        });
        
        //sorts the black pieces by their "value", grouping like pieces together
        Collections.sort(blackTakenPieces, new Comparator<Piece>(){
            @Override
            public int compare(Piece o1, Piece o2) {
                return Integer.compare(o1.getPieceValue(), o2.getPieceValue());
            }
        });
        
        //puts the image of the taken white piece in the "graveyard"
        for(final Piece takenPiece : whiteTakenPieces){
            try{
                final BufferedImage image = ImageIO.read(new File(getPieceColor() + takenPiece.getPieceAlliance().toString().substring(0, 1)
                + "" + takenPiece.toString()));
                final ImageIcon icon = new ImageIcon(image);
                final JLabel imageLabel = new JLabel();
                this.southPanel.add(imageLabel);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        //puts the image of the taken black piece in the "graveyard"
        for(final Piece takenPiece : blackTakenPieces){
            try{
                final BufferedImage image = ImageIO.read(new File(getPieceColor() + takenPiece.getPieceAlliance().toString().substring(0, 1)
                + "" + takenPiece.toString()));
                final ImageIcon icon = new ImageIcon(image);
                final JLabel imageLabel = new JLabel();
                this.southPanel.add(imageLabel);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        validate();
        }
    }
}
