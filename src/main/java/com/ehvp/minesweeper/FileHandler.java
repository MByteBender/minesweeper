package com.ehvp.minesweeper;

import java.io.*;
import java.util.Scanner;

/**
 * handles the highscore writting/ reading/ creating
 */

public class FileHandler {


    /**
     * creates a highscore-file in the resources directory and handels errors
     * checks also if file already exists
     */
    public static void createFile() {
        try {
            File myObj = new File("src/main/resources/highscore.dat");
            if (myObj.createNewFile()) {
                writeToFile(0);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * writes the highscore in the file and converts the score to a String to write it correctly to the file
     */
    public static void writeToFile(int score) {
        Integer scoreWrapped = score;
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/highscore.dat");
            myWriter.write(scoreWrapped.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * reads the score out of the highscore.dat file and handels an exception when the file is not existend
     */
    public static String readFile() {
        String data = "";
        try {
            File myObj = new File("src/main/resources/highscore.dat");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "";
        }

    }
}
