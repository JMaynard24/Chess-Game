import Gui.MenuSystem;
import board.Board;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {


        //Application.launch(MenuSystem.class, args);
        Board board = Board.createStandardBoard();

        System.out.println(board);
    }
}
