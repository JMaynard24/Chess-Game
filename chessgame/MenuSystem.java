/**
 * The MenuSystem class is the class which holds the functionality
 * and implementation for the main menu and settings menu in our
 * chess game.
 * @author Elizabeth Dooley
 */
/*
 * History:
        29-Sept-2020 - MenuSystem class created, work began on draft 1
        06-Oct-2020 - Draft 1 completed
            - Stage, scene, and buttons added
            - Exit button functionality added
        12-Oct-2020 - Draft 2 started
        19-Oct-2020 - Draft 2 completed
            - Settings scene added
            - Settings button functionality added
            - GUI change options added (unfunctional)
            - Minor design changes
        20-Oct-2020 - Draft 3 started
 */
package chessgame;

//javafx imports for gui
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;

public class MenuSystem extends Application {
    //Buttons for mainMenu
    Button play = new Button("PLAY");
    Button settings = new Button("SETTINGS");
    Button exit = new Button("EXIT");
    
    //Buttons, combo boxes, and check box for settingsMenu
    ComboBox boardColor = new ComboBox();
    ComboBox piecesColor = new ComboBox();
    Button settingsSave = new Button("SAVE");
    Button back = new Button("BACK");
    
    /**
     * This method is used to create the pane for the main menu
     * and to set its appearance.
     * @param None
     * @return BorderPane This returns the completed pane used to set the scene in the start method.
     */
    protected BorderPane mainMenu(){
        //main text settings
        Text mainText = new Text(350, 250, "2-D CHESS"); //main title
        mainText.setFill(Color.BLACK);
        mainText.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 100));
        
        //creates button pane and buttons
        VBox paneForButtons = new VBox(20);
        paneForButtons.getChildren().addAll(play, settings, exit);
        paneForButtons.setAlignment(Pos.CENTER); //puts buttons in center of pane
        
        //set button pane color
        paneForButtons.setStyle("-fx-background-color: white");
        
        //put button pane in center of screen
        BorderPane pane = new BorderPane();
        pane.setCenter(paneForButtons);
        
        //creates a pain for the main title
        Pane textPane = new Pane();
        textPane.getChildren().add(mainText);
        pane.setTop(textPane);
        textPane.setStyle("-fx-background-color: grey");
        
        //play button style
        play.setStyle("-fx-background-color: transparent");
        play.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        play.setOnMouseEntered(e -> play.setStyle("-fx-text-fill: grey; -fx-background-color: transparent"));
        play.setOnMouseExited(e -> play.setStyle("-fx-background-color: transparent"));
        
        //settings button style
        settings.setStyle("-fx-background-color: transparent");
        settings.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        settings.setOnMouseEntered(e -> settings.setStyle("-fx-text-fill: grey; -fx-background-color: transparent"));
        settings.setOnMouseExited(e -> settings.setStyle("-fx-background-color: transparent"));
        
        //exit button style
        exit.setStyle("-fx-background-color: transparent");
        exit.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        exit.setOnMouseEntered(e -> exit.setStyle("-fx-text-fill: grey; -fx-background-color: transparent"));
        exit.setOnMouseExited(e -> exit.setStyle("-fx-background-color: transparent"));
        
        return(pane);
    }
    /**
     * This is the start method, taken from the Application class; this is used
     * to create the window for our application and display the chosen scene. Also
     * included is button functionality, so that the scene can be switched back
     * and forth.
     * @param primaryStage This is the stage/window for the application.
     * @return None
     */
    @Override //override the start method in Application
    public void start(Stage primaryStage){
        //commands to open window and create first scene
        Scene sceneMenu = new Scene(mainMenu(), 500, 500);
        GameDisplay board = new GameDisplay(); //call to create the scene for the board
        
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(sceneMenu);
        primaryStage.setMaximized(true); // make the window maximized
        primaryStage.show();
        
        //main menu buttons functionality
        play.setOnAction(e -> primaryStage.getScene().setRoot(board.chessBoard()));
        settings.setOnAction(e -> primaryStage.getScene().setRoot(settingsMenu())); //when clicked changes scene to settings menu
        exit.setOnAction(e -> Platform.exit()); //exits applicztion
        
        //settings buttons functionalitys
        settingsSave.setOnAction(e -> primaryStage.getScene().setRoot(mainMenu()));
        back.setOnAction(e -> primaryStage.getScene().setRoot(mainMenu())); //when clicked returns to main menu
    }
    /**
     * This method creates the BorderPane for the settings menu and sets its appearance.
     * @param None
     * @return BorderPane This is the pane for the settings menu.
     */
    protected BorderPane settingsMenu(){
        //back button appearance
        back.setStyle("-fx-background-color: transparent");
        back.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        back.setOnMouseEntered(e -> back.setStyle("-fx-text-fill: grey; -fx-background-color: transparent")); //when moused over, changes color
        back.setOnMouseExited(e -> back.setStyle("-fx-background-color: transparent")); //when not moused over, return to original settings
        
        //save button appearance
        settingsSave.setStyle("-fx-background-color: transparent");
        settingsSave.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        settingsSave.setOnMouseEntered(e -> settingsSave.setStyle("-fx-text-fill: grey; -fx-background-color: transparent")); //when moused over, changes color
        settingsSave.setOnMouseExited(e -> settingsSave.setStyle("-fx-background-color: transparent")); //when not moused over, return to original settings
        
        //drop-down menus appearance
        boardColor.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-pref-width: 150");
        piecesColor.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-pref-width: 150");
        
        //Settings menu top text and appearance
        Text settingsText = new Text(50, 50, "SETTINGS");
        settingsText.setFill(Color.BLACK);
        settingsText.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 50));
        
        //create the pane the function will return
        BorderPane settingsPane = new BorderPane();
        
        //Labels for drop-down menus and thei appearance
        Label boardLbl = new Label("Board Colors: ");
        boardLbl.setFont(Font.font("Verdana", 20));
        Label piecesLbl = new Label("Piece Colors: ");
        piecesLbl.setFont(Font.font("Verdana", 20));
        Label darkLbl = new Label("Dark Mode: ");
        darkLbl.setFont(Font.font("Verdana", 20));
        
        //options for board color
        boardColor.getItems().add("Black/White");
        boardColor.getItems().add("Wooden");
        boardColor.getItems().add("Black/Red");
        boardColor.getItems().add("Green/White");
        
        //options for piece colors
        piecesColor.getItems().add("Black/White");
        piecesColor.getItems().add("Wooden");
        piecesColor.getItems().add("Black/Brown");
        piecesColor.getItems().add("Blue/White");
        
        //create a Hbox for the boardColor comboBox and it's label
        HBox boardSettings = new HBox(20);
        boardSettings.getChildren().addAll(boardLbl, boardColor);
        boardSettings.setAlignment(Pos.CENTER);
        
        //create a Hbox for the piecesColor comboBox and label
        HBox pieceSettings = new HBox(27);
        pieceSettings.getChildren().addAll(piecesLbl, piecesColor);
        pieceSettings.setAlignment(Pos.CENTER);
        
        //put all the Hboxes in a Vbox
        VBox settingsBox = new VBox(10);
        settingsBox.getChildren().addAll(boardSettings, pieceSettings);
        settingsBox.setAlignment(Pos.CENTER);
      
        //create a Hbox for the buttons
        HBox backBox = new HBox(10);
        backBox.getChildren().addAll(settingsSave, back);
        backBox.setAlignment(Pos.BOTTOM_RIGHT);
        
        //set all the boxes in the correct place
        settingsPane.setTop(settingsText);
        settingsPane.setCenter(settingsBox);
        settingsPane.setBottom(backBox);
    
        return(settingsPane);
    }
    /**
     * This is the standard main menu method, used by me to test the GUI.
     * @param args Not used
     */
    public static void main(String[] args) { //main method required for running
        launch(args);                               //from netbeans
  }
}
