package com.example.minesweeper;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * handles soundcontroll and plays specific sound for the game
 */
public class Sound {

    private static MediaPlayer MediaPlayer;

    /** creates backgroundmusic for the game and handles the volume */
    public static void backgroundMusic(){
        String path = "src/main/resources/background.mp3";
        Media media = new Media (Paths.get(path).toUri().toString()) ;
        MediaPlayer = new MediaPlayer (media);
        MediaPlayer.setVolume(0.05);
        MediaPlayer.setCycleCount(((int) Double.POSITIVE_INFINITY)); //infinite Background music loop
        MediaPlayer.play ();
    }


    /** creates a mousclicksound when you click a tile */
    public static void mouseClickSound(){
        String path = "src/main/resources/mouseClick.wav";
        Media media = new Media (Paths.get(path).toUri().toString()) ;
        MediaPlayer = new MediaPlayer (media);
        MediaPlayer.play ();
    }

    /** creates a bombsound when you hit a mine */
    public static void bombSound(){
        String path = "src/main/resources/bombSound.wav";
        Media media = new Media (Paths.get(path).toUri().toString()) ;
        MediaPlayer = new MediaPlayer(media);
        MediaPlayer.setVolume(0.25);
        MediaPlayer.play ();
    }

    /** creates a sound if you have won the game */
    public static void gameWon(){
        String path = "src/main/resources/gameWon.wav";
        Media media = new Media (Paths.get(path).toUri().toString()) ;
        MediaPlayer = new MediaPlayer(media);
        MediaPlayer.setVolume(0.25);
        MediaPlayer.play ();
    }
}
