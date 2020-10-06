/*
 * History:
        29-Sept-2020 - MenuSystem class created, work began on draft 1
        06-Oct-2020 - Draft 1 completed
 */
package chessgame;

//javafx imports for gui
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
/**
 *
 * @author eddpo
 */
public class MenuSystem extends Application {
    public Text mainText = new Text(350, 250, "2-D CHESS"); //main title
    
    protected BorderPane getPane(){
        //main text settings
        mainText.setFill(Color.BLACK);
        mainText.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 100));
        
        //creates button pane and buttons
        VBox paneForButtons = new VBox(20);
        Button play = new Button("PLAY");
        Button settings = new Button("SETTINGS");
        Button exit = new Button("EXIT");
        paneForButtons.getChildren().addAll(play, settings, exit);
        paneForButtons.setAlignment(Pos.CENTER); //puts button's in center
                                                    //of pane
        
        //set button pane colors
        paneForButtons.setStyle("-fx-border-color: grey");
        paneForButtons.setStyle("-fx-background-color: white");
        
        //put button pane in center of screen
        BorderPane pane = new BorderPane();
        pane.setCenter(paneForButtons);
        
        //creates a pain for the main title
        Pane textPane = new Pane();
        textPane.getChildren().add(mainText);
        pane.setTop(textPane);
        
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
        
        //button functionality; currently all buttons exit program
        play.setOnAction(e -> Platform.exit());
        settings.setOnAction(e -> Platform.exit());
        exit.setOnAction(e -> Platform.exit());
        
        return(pane);
    }
    
    @Override //override the start function in Application
    public void start(Stage primaryStage) {
        //commands to open window
        Scene scene = new Scene(getPane(), 500, 500);
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    public static void main(String[] args) { //main method required for running
    launch(args);                               //from netbeans
  }
}
