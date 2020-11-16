/*
 * The FileManager class is the class that will handle
 * saving and loading the options selected by the user
 * by writing to and reading from files.
 * @author Kyra Wharton
 */
package chessgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    public static void boardColorWrite(String lightColor, String darkColor) {
        try{
            FileWriter myWriter = new FileWriter("src/chessgame/boardcolor.txt");
            myWriter.write(lightColor);
            myWriter.write(darkColor);
            myWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String boardColorRead(){
        String returnStr = "";
        try{
            File read = new File("src/chessgame/boardcolor.txt");
            Scanner myReader = new Scanner(read);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                returnStr = returnStr + data;
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return(returnStr);
    }
    
    public static void pieceColorWrite(String lightColor, String darkColor) {
        try{
            FileWriter myWriter = new FileWriter("src/chessgame/piececolor.txt");
            myWriter.write(lightColor);
            myWriter.write(darkColor);
            myWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String pieceColorRead(){
        String returnStr = "";
        try{
            File read = new File("src/chessgame/piececolor.txt");
            Scanner myReader = new Scanner(read);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                returnStr = returnStr + data;
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return(returnStr);
    }
}
