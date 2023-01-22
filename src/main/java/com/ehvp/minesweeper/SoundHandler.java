package com.ehvp.minesweeper;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * handles soundcontroll and plays specific sound for the game
 */
public class SoundHandler {

    private static int backgroundMusicCount = 0;

    private static int soundEffectCount = 0;
    private static MediaPlayer backgroundMediaPlayer;
    private static MediaPlayer mouseClickMediaPlayer;
    private static MediaPlayer bombSoundMediaPlayer;
    private static MediaPlayer gameWonMediaPlayer;


    /**
     * creates backgroundmusic for the game and handles the volume
     */
    public static void backgroundMusic() {
// checks if sound is already playing, so you can't start backgroundmusic twice
        if (backgroundMusicCount == 0){
            String path = "src/main/resources/background.mp3";
            Media media = new Media(Paths.get(path).toUri().toString());
            backgroundMediaPlayer = new MediaPlayer(media);
            backgroundMediaPlayer.setVolume(0.05);
            backgroundMediaPlayer.setCycleCount(((int) Double.POSITIVE_INFINITY)); //infinite Background music loop
            backgroundMediaPlayer.play();
            backgroundMusicCount++;
        }

    }
    /**
     * stops/turns off background music
     */
    public static void stopBackgroundMusic() {
        backgroundMediaPlayer.stop();
        if (backgroundMusicCount == 1) {
            backgroundMusicCount--;
        }
    }
    /**
     * creates a mousclicksound when you click a tile
     */
    public static void mouseClickSound() {

        if (soundEffectCount >= 0){
            String path = "src/main/resources/mouseClick.wav";
            Media media = new Media(Paths.get(path).toUri().toString());
            mouseClickMediaPlayer = new MediaPlayer(media);
            mouseClickMediaPlayer.play();
        }
    }
    /**
     * creates a bombsound when you hit a mine
     */
    public static void bombSound() {

        if (soundEffectCount >= 0 ){
            String path = "src/main/resources/bombSound.wav";
            Media media = new Media(Paths.get(path).toUri().toString());
            bombSoundMediaPlayer = new MediaPlayer(media);
            bombSoundMediaPlayer.setVolume(0.25);
            bombSoundMediaPlayer.play();
        }
    }
    /**
     * creates a sound if you have won the game
     */
    public static void gameWonSound() {

        if (soundEffectCount >= 0 ){
            String path = "src/main/resources/gameWon.wav";
            Media media = new Media(Paths.get(path).toUri().toString());
            gameWonMediaPlayer = new MediaPlayer(media);
            gameWonMediaPlayer.setCycleCount(((int) Double.POSITIVE_INFINITY));
            gameWonMediaPlayer.setVolume(0.25);
            gameWonMediaPlayer.play();
        }

    }
    /**
     * stops/turns off Game Won Sound
     */
    public static void stopGameWonSound() {
       gameWonMediaPlayer.stop();
    }

    public static void startSoundEffects(){
        soundEffectCount = 1;
    }
    public static void stopSoundEffects(){
        soundEffectCount = -1;
    }


}
