package com.example.minesweeper;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Sound {

    private static MediaPlayer MediaPlayer;

    public static void backgroundMusic(){
        String s = "backgroundMusic.mp3";
        Media h = new Media (Paths.get(s).toUri().toString()) ;
        MediaPlayer = new MediaPlayer (h);
        MediaPlayer.setVolume(0.05);
        MediaPlayer.setCycleCount(((int) Double.POSITIVE_INFINITY)); //infinite Background music loop
        MediaPlayer.play ();

    }

    public static void mouseClickSound(){
        String s = "mouseClick.wav";
        Media h = new Media (Paths.get(s).toUri().toString()) ;
        MediaPlayer = new MediaPlayer (h);
        MediaPlayer.play ();
    }

    public static void bombSound(){
        String s = "bombSound.wav";
        Media h = new Media (Paths.get(s).toUri().toString()) ;
        MediaPlayer = new MediaPlayer(h);
        MediaPlayer.play ();
    }
}
