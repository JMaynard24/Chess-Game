/**
 * The MenuSystem class is the class which holds the functionality
 * and implementation for the main menu and settings menu in our
 * chess game.
 * @author Elizabeth Dooley
 */
package Gui;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class MenuSystem extends Application {
    //Buttons for mainMenu
    Button play = new Button("PLAY");
    Button settings = new Button("SETTINGS");
    Button howTo = new Button("HOW TO MOVE");
    Button exit = new Button("EXIT");

    //Buttons, combo boxes, and check box for settingsMenu
    ComboBox boardColor = new ComboBox();
    ComboBox piecesColor = new ComboBox();
    Button settingsSave = new Button("SAVE");
    Button back = new Button("BACK");

    Button htBack = new Button("BACK");

    FileManager file = new FileManager();
    GameDisplay display = new GameDisplay();

    /**
     * This method is used to create the pane for the main menu
     * and to set its appearance.
     *
     * @return BorderPane This returns the completed pane used to set the scene in the start method.
     * @paramNone
     */
    protected BorderPane mainMenu() {
        //main text settings
        Text mainText = new Text(350, 250, "2-D CHESS"); //main title
        mainText.setFill(Color.BLACK);
        mainText.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 100));

        //creates button pane and buttons
        VBox boxForButtons = new VBox(20);
        boxForButtons.getChildren().addAll(play, settings, howTo, exit);
        boxForButtons.setAlignment(Pos.CENTER); //puts buttons in center of pane

        //set button pane color
        boxForButtons.setStyle("-fx-background-color: #F8E0C8");

        //put button pane in center of screen
        BorderPane pane = new BorderPane();
        pane.setCenter(boxForButtons);

        //creates a pain for the main title
        Pane textPane = new Pane();
        textPane.getChildren().add(mainText);
        pane.setTop(textPane);
        textPane.setStyle("-fx-background-color: #D2B48C");

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

        howTo.setStyle("-fx-background-color: transparent");
        howTo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        howTo.setOnMouseEntered(e -> howTo.setStyle("-fx-text-fill: grey; -fx-background-color: transparent"));
        howTo.setOnMouseExited(e -> howTo.setStyle("-fx-background-color: transparent"));

        //exit button style
        exit.setStyle("-fx-background-color: transparent");
        exit.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        exit.setOnMouseEntered(e -> exit.setStyle("-fx-text-fill: grey; -fx-background-color: transparent"));
        exit.setOnMouseExited(e -> exit.setStyle("-fx-background-color: transparent"));

        return (pane);
    }

    /**
     * This is the start method, taken from the Application class; this is used
     * to create the window for our application and display the chosen scene. Also
     * included is button functionality, so that the scene can be switched back
     * and forth.
     *
     * @param primaryStage This is the stage/window for the application.
     * @return None
     */
    @Override //override the start method in Application
    public void start(Stage primaryStage) {
        //commands to open window and create first scene
        Scene sceneMenu = new Scene(mainMenu(), 500, 500);

        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(sceneMenu);
        primaryStage.setMaximized(true); // make the window maximized
        primaryStage.show();

        //main menu buttons functionality
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                display.getGameDisplay().setVisible(true);
            }
        });
        settings.setOnAction(e -> primaryStage.getScene().setRoot(settingsMenu())); //when clicked changes scene to settings menu
        howTo.setOnAction(e -> primaryStage.getScene().setRoot(howToWindow()));
        exit.setOnAction(e -> Platform.exit()); //exits application

        //settings buttons functionalitys
        settingsSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setPieceColor(piecesColor.getValue().toString());
                setBoardColor(boardColor.getValue().toString());
            }

        });
        back.setOnAction(e -> primaryStage.getScene().setRoot(mainMenu())); //when clicked returns to main menu

        htBack.setOnAction(e -> primaryStage.getScene().setRoot(mainMenu()));
    }

    /**
     * This method creates the BorderPane for the settings menu and sets its appearance.
     *
     * @return BorderPane This is the pane for the settings menu.
     * @paramNone
     */
    protected BorderPane settingsMenu() {
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
        boardColor.getItems().add("Tan/Brown");
        boardColor.getItems().add("Red/Black");

        //options for piece colors
        piecesColor.getItems().add("Black/White");
        piecesColor.getItems().add("Tan/Brown");

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
        settingsPane.setStyle("-fx-background-color: #F8E0C8");

        return (settingsPane);
    }

    /**
     * This method is used to tell player how to move pieces.
     *
     * @return BorderPane Pane to change to the How To menu
     */
    private BorderPane howToWindow() {
        BorderPane howToPane = new BorderPane(); //creates pane for window

        //back button style
        htBack.setStyle("-fx-background-color: transparent");
        htBack.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        htBack.setOnMouseEntered(e -> htBack.setStyle("-fx-text-fill: grey; -fx-background-color: transparent")); //when moused over, changes color
        htBack.setOnMouseExited(e -> htBack.setStyle("-fx-background-color: transparent")); //when not moused over, return to original settings

        //title text settings
        Text htText = new Text(50, 50, "How To:");
        htText.setFill(Color.BLACK);
        htText.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 50));

        //create box for text
        Rectangle rectangle = new Rectangle(1, 1, 400, 425);
        rectangle.setFill(Color.TAN);
        rectangle.setStroke(Color.BLACK);

        //creates main text and centers it
        Text text = new Text("\n\n\n\n\nTo select a piece click on it\nwith the left mouse button\nthen click on the desired location\nwith the left mouse"
                + " button.\n\nTo unselect a selected piece\nclick on it with the\nright mouse button.\n");
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Helvetica", FontWeight.NORMAL, 22.5));
        TextFlow flow = new TextFlow();
        flow.getChildren().add(text);
        flow.setTextAlignment(TextAlignment.CENTER);

        //stack pane for rectangle and text
        StackPane stack = new StackPane();
        stack.getChildren().addAll(rectangle, flow);

        //create box the move button bottom right
        HBox box = new HBox();
        box.getChildren().add(htBack);
        box.setAlignment(Pos.BOTTOM_RIGHT);

        //add all features to pane
        howToPane.setTop(htText);
        howToPane.setCenter(stack);
        howToPane.setBottom(box);
        howToPane.setStyle("-fx-background-color: #F8E0C8");

        return (howToPane);
    }

    /**
     * This method calls the file write and writes the chosen colors to piece file.
     *
     * @param pieceColor Passed in string which represents chosen selection for piece colors
     * @return None
     */
    private void setPieceColor(String pieceColor) {
        switch (pieceColor) {
            case "Black/White":
                file.pieceColorWrite("white\n", "black\n");
                break;
            case "Tan/Brown":
                file.pieceColorWrite("tan\n", "brown\n");
                break;
            default:
                file.pieceColorWrite("black\n", "white\n");
        }
    }

    /**
     * This method calls the file write and writes the chosen colors to board file.
     *
     * @param boardColor Passed in string which represents chosen selection for board colors
     * @return None
     */
    private void setBoardColor(String boardColor) {
        switch (boardColor) {
            case "Black/White":
                file.boardColorWrite("white\n", "black\n");
                break;
            case "Tan/Brown":
                file.boardColorWrite("tan\n", "brown\n");
                break;
            case "Red/Black":
                file.boardColorWrite("red\n", "black\n");
                break;
            default:
                file.boardColorWrite("black\n", "white\n");
        }
    }
}