package com.example.minesweeper;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;


public class Sound {

    private static MediaPlayer MediaPlayer;

    public static void backgroundMusic(){
        String path = "src/main/resources/background.mp3";
        Media media = new Media (Paths.get(path).toUri().toString()) ;
        MediaPlayer = new MediaPlayer (media);
        MediaPlayer.setVolume(0.05);
        MediaPlayer.setCycleCount(((int) Double.POSITIVE_INFINITY)); //infinite Background music loop
        MediaPlayer.play ();
    }


    public static void mouseClickSound(){
        String path = "src/main/resources/mouseClick.wav";
        Media media = new Media (Paths.get(path).toUri().toString()) ;
        MediaPlayer = new MediaPlayer (media);
        MediaPlayer.play ();
    }


    public static void bombSound(){
        String path = "src/main/resources/bombSound.wav";
        Media media = new Media (Paths.get(path).toUri().toString()) ;
        MediaPlayer = new MediaPlayer(media);
        MediaPlayer.setVolume(0.25);
        MediaPlayer.play ();
    }

    public static void gameWon(){
        String path = "src/main/resources/gameWon.wav";
        Media media = new Media (Paths.get(path).toUri().toString()) ;
        MediaPlayer = new MediaPlayer(media);
        MediaPlayer.setVolume(0.25);
        MediaPlayer.play ();
    }
}
