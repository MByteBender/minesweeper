package com.example.minesweeper;

import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class Tile extends StackPane {


    public Tile(){};

    App app = new App();

    private final Tile[][] grid = new Tile[app.getX_TILES()][app.getY_TILES()];
    final int x =0 ;
    final int y =0;

    final boolean hasBomb = false;

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


    public Parent createContent() {

        Pane root = new Pane();


        root.setPrefSize(app.W, app.H);

        // creates the tiles and checks if easy mode is chosen
        if (Objects.equals(app.mode, "easy")) {
            for (int y = 0; y < app.Y_TILES; y++) {
                for (int x = 0; x < app.X_TILES; x++) {
                    Tile tile = new Tile(x, y, Math.random() < 0.15); //with 15% probability the field is a bomb
                    if (tile.hasBomb) {
                        app.bombCounter++;
                    }
                    grid[x][y] = tile; // places a tile at the current x/y position
                    root.getChildren().add(tile);
                }
            }

            // creates the tiles and checks if medium mode is chosen
        } else if (Objects.equals(app.mode, "medium")) {
            for (int y = 0; y < app.Y_TILES; y++) {
                for (int x = 0; x < app.X_TILES; x++) {
                    Tile tile = new Tile(x, y, Math.random() < 0.18); //with 18% probability the field is a bomb
                    if (tile.hasBomb) {
                        app.bombCounter++;
                    }
                    grid[x][y] = tile; // places a tile at the current x/y position
                    root.getChildren().add(tile);
                }
            }

            // creates the tiles and checks if hard mode is chosen
        } else if (Objects.equals(app.mode, "hard")) {
            for (int y = 0; y < app.Y_TILES; y++) {
                for (int x = 0; x < app.X_TILES; x++) {
                    Tile tile = new Tile(x, y, Math.random() < 0.20); //with 20% probability the field is a bomb
                    if (tile.hasBomb) {
                        app.bombCounter++;
                    }
                    grid[x][y] = tile; // places a tile at the current x/y position
                    root.getChildren().add(tile);
                }
            }
        }

        for (int y = 0; y < app.Y_TILES; y++) {
            for (int x = 0; x < app.X_TILES; x++) {
                Tile tile = grid[x][y];

                if (tile.hasBomb)
                    continue;

                long bombs = app.getNeighbors(tile).stream().filter(t -> t.hasBomb).count();

                if (bombs > 0)
                    tile.text.setText(String.valueOf(bombs));
            }
        }

        return root;
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
//        for (int i = 0; i < app.getNeighbors(this).size(); i++){
//            app.getNeighbors(this).get(i).open();
//        }


        if (text.getText().isEmpty()) {
            System.out.println("Tile X-Tiles: "+app.getX_TILES());
            System.out.println("getNeigbhors: "+app.getNeighbors(this));
            app.getNeighbors(this).forEach(Tile::open);
        }

//        if (text.getText().isEmpty()) {
//            app.getNeighbors(this).forEach(Tile::open);
//        }

        int openTiles = 0;
        for (int y = 0; y < app.getY_TILES(); y++) {
            for (int x = 0; x < app.getX_TILES(); x++) {
                Tile tile = app.grid[x][y];

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









