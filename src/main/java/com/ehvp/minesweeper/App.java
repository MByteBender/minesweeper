package com.ehvp.minesweeper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.nio.file.Paths;
import java.util.*;

public class App extends Application {


    private static final int TILE_SIZE = 40;
    private static  int W = 1000;
    private static  int H = 800;

    private static  int X_TILES = W / TILE_SIZE;
    private static  int Y_TILES = H / TILE_SIZE;

    private int highscore = 0;
    private  int score = 0;

    private final Tile[][] grid = new Tile[X_TILES][Y_TILES];

    private Scene scene;

    private Stage primaryStage;

    private int bombCounter;

    private String mode;



    // creates the start Menu with an easy medium hard mode and highscore room selection
    private Parent startMenu(){

        Button rulesButton = new Button("Rules");
        rulesButton.setPrefWidth(80);

        Button easyButton = new Button("Easy");
        easyButton.setPrefWidth(80);

        Button mediumButton = new Button("Medium");
        mediumButton.setPrefWidth(80);

        Button hardButton = new Button("Hard");
        hardButton.setPrefWidth(80);

        Button scoreButton = new Button("Highscore");
        scoreButton.setPrefWidth(80);

        Label startLabel = new Label("MINESWEEPER");
        startLabel.setTextFill(Color.WHITE);
        startLabel.setFont(Font.font(null, FontWeight.BOLD, 40));

        Label chooseLabel = new Label("Choose Level: ");
        chooseLabel.setFont(Font.font(20));
        chooseLabel.setTextFill(Color.WHITE);
        VBox vBox = new VBox(20, startLabel, chooseLabel, easyButton, mediumButton, hardButton, scoreButton, rulesButton);
        vBox.setAlignment(Pos. CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        String path = "src/main/resources/background.png";
        Image backgroundPng = new Image(Paths.get(path).toUri().toString());
        BackgroundImage backgroundImage = new BackgroundImage(backgroundPng,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(100,100,true,true,true,true));
        Background background = new Background(backgroundImage);

        vBox.setBackground(background);

        // sets the grid to 15 x 10 tiles when clicking on the easy-button
        easyButton.setOnAction(e -> {
            mode = "easy";
            W = 600;
            H = 400;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
            primaryStage.setScene(scene);
        });


        // sets the grid to 20 x 15 tiles when clicking on the medium-button
        mediumButton.setOnAction(e -> {
            mode = "medium";
            W = 800;
            H = 600;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
            primaryStage.setScene(scene);
        });


        // sets the grid to 25 x 20 tiles when clicking on the hard-button
        hardButton.setOnAction(e -> {
            mode = "hard";
            W = 1000;
            H = 800;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
            primaryStage.setScene(scene);
        });


        // changes scene to the highscore room
        scoreButton.setOnAction(e -> {
            scene = new Scene(scoreRoom());
            primaryStage.setScene(scene);
        });

        rulesButton.setOnAction(e -> {
            scene = new Scene(rulesRoom());
            primaryStage.setScene(scene);
        });


        return vBox;
    }


    // creates the scoreroom where the highscore is shown
    private Parent scoreRoom(){

        Button backButton = new Button("Back");
        backButton.setPrefWidth(80);


        Label highscoreLabel = new Label("Highscore");
        highscoreLabel.setTextFill(Color.WHITE);
        highscoreLabel.setFont(Font.font(null, FontWeight.BOLD, 40));

        Label highScore = new Label(FileHandler.readFile());
        highScore.setTextFill(Color.WHITE);
        highScore.setFont(Font.font(null, FontWeight.BOLD, 40));

        BorderPane scorePane = new BorderPane();
        scorePane.setPrefSize(600,400);

        HBox top = new HBox(100, highscoreLabel);
        top.setAlignment(Pos. TOP_CENTER);

        HBox center = new HBox(100, highScore);
        center.setAlignment(Pos. CENTER);

        scorePane.setTop(top);
        scorePane.setCenter(center);
        scorePane.setBottom(backButton);


        scorePane.setPadding(new Insets(10));

        // sets background Image
        String path = "src/main/resources/background.png";
        Image backgroundPng = new Image(Paths.get(path).toUri().toString());
        BackgroundImage backgroundImage = new BackgroundImage(backgroundPng,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(100,100,true,true,true,true));
        Background background = new Background(backgroundImage);
        scorePane.setBackground(background);


        backButton.setOnAction(e -> scene.setRoot(startMenu()));


        return scorePane;

    }


    private Parent rulesRoom() {

        Button returnButton = new Button("Back");
        returnButton.setPrefWidth(80);

        BorderPane rB = new BorderPane();
        rB.setPrefSize(800, 500);

        Label rules = new Label ("""
                Choose between three different difficulties:

                → Easy - 10 x 15 Raster & 15% Bombs

                → Medium - 15 x 15 Raster & 18% Bombs

                → Hard - 20 x 25 Raster & 20% Bombs


                Explaination Minesweeper:

                The game is usually over when a mine is revealed.

                The game continues when an empty square is revealed.

                If a number is displayed when a square is uncovered, it represents the number of mines,
                that are hidden in the adjacent 8 fields.

                Based on this information, it can be deduced under which of the adjacent fields mines are located
                and on which fields can be clicked without danger.
                """);
        rules.setFont(Font.font(null, FontWeight.BOLD, 15));
        rules.setTextFill(Color.WHITE);

        Label rulesHeader = new Label("Rules");
        rulesHeader.setTextFill(Color.BLUEVIOLET);
        rulesHeader.setFont(Font.font(null, FontWeight.BOLD, 30));

        HBox top = new HBox(100, rulesHeader);
        top.setAlignment(Pos. TOP_CENTER);

        HBox center = new HBox(100, rules);
        center.setAlignment(Pos. CENTER);



        rB.setPadding(new Insets(10));

        rB.setTop(top);
        rB.setCenter(rules);
        rB.setBottom(returnButton);



        returnButton.setOnAction(e -> scene.setRoot(startMenu()));

        // sets background Image
        String pathBackground = "src/main/resources/background.png";
        Image backgroundPng = new Image(Paths.get(pathBackground).toUri().toString());
        BackgroundImage backgroundImage = new BackgroundImage(backgroundPng,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(100,100,true,true,true,true));
        Background background = new Background(backgroundImage);

        rB.setBackground(background);

        Label l1 = new Label("Rules");
        l1.setTextFill(Color.BLUEVIOLET);
        l1.setFont(Font.font(null, FontWeight.BOLD, 50));

        return rB;
    }
    /** creates the game over screen*/
    private Parent gameOver(){

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);

        // places a game over gif
        String path = "src/main/resources/gameOver.gif";
        Image gameOverGif = new Image(Paths.get(path).toUri().toString());
        ImageView img2 = new ImageView(gameOverGif);

        Label highScoreLabel = new Label("Score: " + score * 10);

        String pathBackground = "src/main/resources/background.png";
        Image backgroundPng = new Image(Paths.get(pathBackground).toUri().toString());
        BackgroundImage backgroundImage = new BackgroundImage(backgroundPng,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(100,100,true,true,true,true));
        Background background = new Background(backgroundImage);

        vBox.setBackground(background);


        // checks if the actual score is higher than the highscore if yes it saves the score as the highscore
        score = score * 10;
        if (score > highscore){
            highscore = score * 10;
            highscore /= 10;
        }

        /* checks if the actual highscore is bigger than the highscore
        written in the highscore file if yes it writes it into the highscore file */
        try{
            int scoreOutFile = Integer.parseInt(FileHandler.readFile()); //converts the highscore String to a int
            if (highscore > scoreOutFile){
                FileHandler.writeToFile(highscore);
            }
        } catch (NumberFormatException ex){
            ex.printStackTrace();
        }

        score = 0;
        bombCounter = 0;
        highScoreLabel.setTextFill(Color.BLUEVIOLET);
        highScoreLabel.setFont(Font.font(null, FontWeight.BOLD, 20));

        Label l1 = new Label("Game Over!!!");
        l1.setTextFill(Color.BLUEVIOLET);
        l1.setFont(Font.font(null, FontWeight.BOLD, 50));

        Button restart = new Button("Try Again");
        restart.setPrefWidth(150);

        vBox.getChildren().addAll(highScoreLabel,l1, restart, img2);
        restart.setOnAction(e -> scene.setRoot(startMenu()));


        return vBox;
    }


    /** creates the game won screen*/
    private Parent gameWon(){

        SoundHandler.gameWon();

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        // places a winning gif
        String path = "src/main/resources/gameWon.gif";
        Image gameWonGif = new Image(Paths.get(path).toUri().toString());
        ImageView img3 = new ImageView(gameWonGif);

        String pathBackground = "src/main/resources/background.png";
        Image backgroundPng = new Image(Paths.get(pathBackground).toUri().toString());
        BackgroundImage backgroundImage = new BackgroundImage(backgroundPng,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(100,100,true,true,true,true));
        Background background = new Background(backgroundImage);

        vBox.setBackground(background);

        Label highScoreLabel = new Label("Score: " + score * 10);

        // checks if the actual score is higher than the highscore if yes it saves the score as the highscore
        score = score * 10;
        if (score > highscore){
            highscore = score * 10;
            highscore /= 10;
        }

        /* checks if the actual highscore is bigger than the highscore
        written in the highscore file if yes it writes it into the highscore file */
        try{
            int scoreOutFile = Integer.parseInt(FileHandler.readFile());
            if (highscore > scoreOutFile){
                FileHandler.writeToFile(highscore);
            }
        } catch (NumberFormatException ex){
            ex.printStackTrace();
        }

        score = 0;
        bombCounter = 0;
        highScoreLabel.setTextFill(Color.BLUEVIOLET);
        highScoreLabel.setFont(Font.font(null, FontWeight.BOLD, 20));

        Label l1 = new Label("Game WON!!!");
        l1.setTextFill(Color.BLUEVIOLET);
        l1.setFont(Font.font(null, FontWeight.BOLD, 50));

        Button restart = new Button("Try Again");
        restart.setPrefWidth(150);

        vBox.getChildren().addAll(highScoreLabel,l1, restart, img3);
        restart.setOnAction(e -> scene.setRoot(startMenu())); //sets the scene to the start Menu

        FileHandler.createFile();

        return vBox;
    }


    /** creates the game itself*/
    private Parent createContent() {

        Pane root = new Pane();


        root.setPrefSize(W, H);

        // creates the tiles and checks if easy mode is chosen
        if (Objects.equals(mode, "easy")) {
            for (int y = 0; y < Y_TILES; y++) {
                for (int x = 0; x < X_TILES; x++) {
                    Tile tile = new Tile(x, y, Math.random() < 0.15); //with 15% probability the field is a bomb
                    if (tile.hasBomb) {
                        bombCounter++;
                    }
                    grid[x][y] = tile; // places a tile at the current x/y position
                    root.getChildren().add(tile);
                }
            }

            // creates the tiles and checks if medium mode is chosen
        } else if (Objects.equals(mode, "medium")) {
            for (int y = 0; y < Y_TILES; y++) {
                for (int x = 0; x < X_TILES; x++) {
                    Tile tile = new Tile(x, y, Math.random() < 0.18); //with 18% probability the field is a bomb
                    if (tile.hasBomb) {
                        bombCounter++;
                    }
                    grid[x][y] = tile; // places a tile at the current x/y position
                    root.getChildren().add(tile);
                }
            }

            // creates the tiles and checks if hard mode is chosen
        } else if (Objects.equals(mode, "hard")) {
            for (int y = 0; y < Y_TILES; y++) {
                for (int x = 0; x < X_TILES; x++) {
                    Tile tile = new Tile(x, y, Math.random() < 0.20); //with 20% probability the field is a bomb
                    if (tile.hasBomb) {
                        bombCounter++;
                    }
                    grid[x][y] = tile; // places a tile at the current x/y position
                    root.getChildren().add(tile);
                }
            }
        }

        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = grid[x][y];

                if (tile.hasBomb)
                    continue;

                // calculates the number of bombs form the neighbors, using long, because stream count will return long
                long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();

                // sets the number of surrounding bombs into the tile
                if (bombs > 0)
                    tile.text.setText(String.valueOf(bombs));
            }
        }

        return root;
    }



    private List<Tile> getNeighbors(Tile tile) {

        List<Tile> neighbors = new ArrayList<>();

        // creates an array with the positon of the 8 surounden fields
        int[] points = new int[] {
                -1, -1,
                -1, 0,
                -1, 1,
                0, -1,
                0, 1,
                1, -1,
                1, 0,
                1, 1
        };


        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int newX = tile.x + dx; // neighbors x coordinate
            int newY = tile.y + dy; // neighbors y coordinate

            // checks if new X & Y confidante is valid in Grid 2d Array
            if (newX >= 0 && newX < X_TILES
                    && newY >= 0 && newY < Y_TILES) {
                neighbors.add(grid[newX][newY]);
            }
        }

        return neighbors;
    }


    private class Tile extends StackPane {

        private final int x, y;

        private final boolean hasBomb;

        private boolean isOpen = false;

        private final Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);

        private final Text text = new Text();


        // constructor of the tile object with x  y postion and a boolean to see if the tile has a bomb
        public Tile(int x, int y, boolean hasBomb) {

            this.x = x;
            this.y = y;
            this.hasBomb = hasBomb;

            border.setStroke(Color.LIGHTGRAY);

            text.setFont(Font.font(18));
            text.setText(hasBomb ? "X" : ""); // if hasBomb is true setText to X if not set empty
            text.setVisible(false);

            getChildren().addAll(border, text);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);


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
                SoundHandler.mouseClickSound();
            });
        }


        /** reveals a tile*/
        public void open() {

            // if tile is already opened nothing should happen
            if (isOpen)
                return;

            // if tile has a bomb game is Over and set scene to gameOver
            if (hasBomb) {
                SoundHandler.bombSound();
                scene.setRoot(gameOver());
                return;
            }


            isOpen = true;
            text.setVisible(true);
            score++;
            border.setFill(null);

            // if revealed field is an empty one it opens all surrounding empty fields
            if (text.getText().isEmpty()) {
                getNeighbors(this).forEach(Tile::open);
            }

            // equal to above method
//            for (int i = 0; i < getNeighbors(this).size(); i++){
//                getNeighbors(this).get(i).open();
//            }

            // for game won counts open Tiles
            int openTiles = 0;
            for (int y = 0; y < Y_TILES; y++) {
                for (int x = 0; x < X_TILES; x++) {
                    Tile tile = grid[x][y];

                    if (tile.isOpen) {
                        openTiles++;}
                }
            }

            // if all tiles which are not a bomb are opened set the scene to the game won screen
            if ((openTiles + bombCounter) == (Y_TILES*X_TILES)) {
                scene.setRoot(gameWon());
            }
        }
    }


    @Override
    public void start(Stage primaryStage) {

        FileHandler.createFile();
        SoundHandler.backgroundMusic();

        primaryStage.setResizable(false);

        Button rulesButton = new Button("Rules");
        rulesButton.setPrefWidth(80);

        Button scoreButton = new Button("Highscore");
        scoreButton.setPrefWidth(80);

        Button easyButton = new Button("Easy");
        easyButton.setPrefWidth(80);

        Button mediumButton = new Button("Medium");
        mediumButton.setPrefWidth(80);

        Button hardButton = new Button("Hard");
        hardButton.setPrefWidth(80);

        Label startLabel = new Label("MINESWEEPER");
        startLabel.setTextFill(Color.WHITE);
        startLabel.setFont(Font.font(null, FontWeight.BOLD, 40));

        Label chooseLabel = new Label("Choose Level: ");
        chooseLabel.setTextFill(Color.WHITE);
        chooseLabel.setFont(Font.font(20));

        VBox vBox = new VBox(20, startLabel, chooseLabel, easyButton, mediumButton, hardButton, scoreButton, rulesButton);
        vBox.setAlignment(Pos. CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));



        // sets background picture
        String path = "src/main/resources/background.png";
        Image backgroundPng = new Image(Paths.get(path).toUri().toString());
        BackgroundImage backgroundImage = new BackgroundImage(backgroundPng,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(100,100,true,true,true,true));
        Background background = new Background(backgroundImage);

        vBox.setBackground(background);



        Scene startScene = new Scene(vBox, 600, 400);
        this.primaryStage = primaryStage;

        // sets the grid to 15 x 10 tiles when clicking on the easy-button
        easyButton.setOnAction(e -> {
            mode = "easy";
            W = 600;
            H = 400;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
            primaryStage.setScene(scene);
        });

        // sets the grid to 20 x 15 tiles when clicking on the easy-button
        mediumButton.setOnAction(e -> {
            mode = "medium";
            W = 800;
            H = 600;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
            primaryStage.setScene(scene);
        });

        // sets the grid to 25 x 20 tiles when clicking on the easy-button
        hardButton.setOnAction(e -> {
            mode = "hard";
            W = 1000;
            H = 800;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
            primaryStage.setScene(scene);
        });

        // changes the scene to the scoreroom to see the highscore
        scoreButton.setOnAction(e -> {
            scene = new Scene(scoreRoom());
            primaryStage.setScene(scene);
        });

        // changes scene to the rulesroom to read the game rules
        rulesButton.setOnAction(e -> {
            scene = new Scene(rulesRoom());
            primaryStage.setScene(scene);

        });

        primaryStage.setScene(startScene);
        primaryStage.setTitle("Minesweeper!");
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
