package com.ehvp.minesweeper;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * handles soundcontroll and plays specific sound for the game
 */
public class SoundHandler {

    private static MediaPlayer mediaPlayer;

    /**
     * creates backgroundmusic for the game and handles the volume
     */
    public static void backgroundMusic() {
        String path = "src/main/resources/background.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.05);
        mediaPlayer.setCycleCount(((int) Double.POSITIVE_INFINITY)); //infinite Background music loop
        mediaPlayer.play();
    }


    /**
     * creates a mousclicksound when you click a tile
     */
    public static void mouseClickSound() {
        String path = "src/main/resources/mouseClick.wav";
        Media media = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    /**
     * creates a bombsound when you hit a mine
     */
    public static void bombSound() {
        String path = "src/main/resources/bombSound.wav";
        Media media = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.25);
        mediaPlayer.play();
    }

    /**
     * creates a sound if you have won the game
     */
    public static void gameWon() {
        String path = "src/main/resources/gameWon.wav";
        Media media = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(((int) Double.POSITIVE_INFINITY));
        mediaPlayer.setVolume(0.25);
        mediaPlayer.play();
    }
}
