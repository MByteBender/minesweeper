package com.example.minesweeper;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {


    App app = new App();
    final int x;
    final int y;

    final boolean hasBomb;

    private boolean isOpen = false;

    private final Rectangle border = new Rectangle(app.getTILE_SIZE() - 2, app.getTILE_SIZE() - 2);

    final Text text = new Text();


    // constructor of the tile object with x  y postion and a boolean to see if the tile has a bomb
    public Tile(int x, int y, boolean hasBomb) {

        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;

        border.setStroke(Color.LIGHTGRAY);

        text.setFont(Font.font(18));
        text.setText(hasBomb ? "X" : "");
        text.setVisible(false);

        getChildren().addAll(border, text);

        setTranslateX(x * app.getTILE_SIZE());
        setTranslateY(y * app.getTILE_SIZE());


        // checks if the mouse is right or left clicked
        border.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                open();
            }
            // set fill of the tile to red to mark it when mouse is right clicked
            if (event.getButton() == MouseButton.SECONDARY) {
                border.setFill(Color.RED);
            }
            // if the tile is right clicked again it changes the state back
            if (event.getButton() == MouseButton.SECONDARY) {
                if (event.getClickCount() == 2) {
                    border.setFill(Color.BLACK);
                }
            }
        });
        setOnMouseClicked(e -> {
            // plays a click sound when the mouse is clicked
            Sound.mouseClickSound();
        });
    }


    /** reveals a tile*/
    public void open() {

        // if tile is already opened nothing should happen
        if (isOpen)
            return;

        // if tile has a bomb game is Over and set scene to gameOver
        if (hasBomb) {
            System.out.println("Game Over");
            Sound.bombSound();
            App.scene.setRoot(app.gameOver());
            return;
        }


        isOpen = true;
        text.setVisible(true);
        App.score++;
        border.setFill(null);

        // if reveald field is an empty one it opens all surounden empty fields
        if (text.getText().isEmpty()) {
            System.out.println("Tile X-Tiles: "+app.getX_TILES());
            System.out.println("getNeigbhors: "+app.getNeighbors(this));
            app.getNeighbors(this).forEach(Tile::open);
        }

        if (text.getText().isEmpty()) {
            app.getNeighbors(this).forEach(Tile::open);
        }

        int openTiles = 0;
        for (int y = 0; y < app.getY_TILES(); y++) {
            for (int x = 0; x < app.getX_TILES(); x++) {
                Tile tile = app.getGrid(x, y);

                if (tile.isOpen) {
                    openTiles++;}
            }
        }

        // if all tiles which are not a bomb are opend set the scene to the game won screen
        if ((openTiles + app.getBombCounter()) == (app.getY_TILES()*app.getX_TILES())) {
            App.scene.setRoot(app.gameWon());
        }
    }
}









