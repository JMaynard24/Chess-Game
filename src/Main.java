
import Gui.MenuSystem;
import chessgamemaster.Board.Board;
import javafx.application.Application;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aceth
 */
public class Main {
    public static void main(String[] args) {
	// write your code here

        Application.launch(MenuSystem.class, args);
        Board board = Board.createStandardBoard();

        System.out.println(board);
    }
}
