package com.example.minesweeper;

import java.io.*;
import java.util.Scanner;

public class FileHandler {

    public static void CreateFile(){
        try {
            File myObj = new File("highscore.dat");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void WriteToFile(int score){
        Integer i = score;
        try {
            FileWriter myWriter = new FileWriter("highscore.dat");
            myWriter.write(i.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String ReadFile(){
        String data ="";
        try {
            File myObj = new File("highscore.dat");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);

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
