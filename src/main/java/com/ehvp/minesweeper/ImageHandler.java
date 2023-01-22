package com.ehvp.minesweeper;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.nio.file.Paths;

public class ImageHandler {



    public static Background backgroundImage(){
        String path = "src/main/resources/background.png";
        Image backgroundPng = new Image(Paths.get(path).toUri().toString());
        BackgroundImage backgroundImage = new BackgroundImage(backgroundPng, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(100,100,true,true,true,true));
        Background background = new Background(backgroundImage);

        return background;
    }


    public static ImageView gameOverImage(){
        String path = "src/main/resources/gameOver.gif";
        Image gameOverGif = new Image(Paths.get(path).toUri().toString());
        ImageView gameOverGifView = new ImageView(gameOverGif);

        return gameOverGifView;
    }

    public static ImageView gameWonImage(){
        String path = "src/main/resources/gameOver.gif";
        Image gameOverGif = new Image(Paths.get(path).toUri().toString());
        ImageView gameImageGifView = new ImageView(gameOverGif);

        return gameImageGifView;
    }

}
