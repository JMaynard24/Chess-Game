package Gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    public static void boardColorWrite(String lightColor, String darkColor) {
        try{
            FileWriter myWriter = new FileWriter("boardcolor.txt");
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
            File read = new File("boardcolor.txt");
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
            FileWriter myWriter = new FileWriter("piececolor.txt");
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
            File read = new File("piececolor.txt");
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