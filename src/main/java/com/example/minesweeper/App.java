package com.example.minesweeper;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
public class App extends Application {

    private static final int TILE_SIZE = 40;
    private static  int W = 1000;
    private static  int H = 800;

    private static  int X_TILES = W / TILE_SIZE;
    private static  int Y_TILES = H / TILE_SIZE;

    private static int score = 0;

    private final Tile[][] grid = new Tile[X_TILES][Y_TILES];
    private Scene scene;
    private Scene startScene;


    MediaPlayer mediaPlayer;
    public void music(){
        String s = "backgroundmusic.mp3";
        Media h = new Media (Paths.get(s).toUri().toString()) ;
        mediaPlayer = new MediaPlayer (h);
        mediaPlayer.play ();

    }
    private Parent startMenu(){
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
        chooseLabel.setFont(Font.font(20));
        VBox vBox = new VBox(20, startLabel, chooseLabel, easyButton, mediumButton, hardButton);
        vBox.setAlignment(Pos. CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        easyButton.setOnAction(e -> {
            W = 600;
            H = 400;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());

//            primaryStage.setScene(scene);
        });

        mediumButton.setOnAction(e -> {
            W = 800;
            H = 600;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
//            primaryStage.setScene(scene);
        });
//
        hardButton.setOnAction(e -> {
            W = 1000;
            H = 800;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
//            primaryStage.setScene(scene);
        });
//        startScene = new Scene(vBox, 600, 400);

        return vBox;
    }
    private Parent gameOver(){
        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setAlignment(Pos.CENTER);


        Label highscore = new Label("Score: " + score * 10);
        highscore.setTextFill(Color.RED);
        highscore.setFont(Font.font(null, FontWeight.BOLD, 20));

        Label l1 = new Label("Game Over!!!");
        l1.setTextFill(Color.RED);
        l1.setFont(Font.font(null, FontWeight.BOLD, 50));


        Button restart = new Button("Try Again");
        restart.setPrefWidth(150);

        vBox.getChildren().addAll(highscore,l1, restart);
        restart.setOnAction(e -> scene.setRoot(startMenu()));

        return vBox;
    }
    private Parent createContent() {

        Pane root = new Pane();

        root.setPrefSize(W, H);

        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = new Tile(x, y, Math.random() < 0.15);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = grid[x][y];

                if (tile.hasBomb)
                    continue;

                long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();

                if (bombs > 0)
                    tile.text.setText(String.valueOf(bombs));
            }
        }

        return root;
    }

    private List<Tile> getNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();

        // ttt
        // tXt
        // ttt

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

            int newX = tile.x + dx;
            int newY = tile.y + dy;

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

        public Tile(int x, int y, boolean hasBomb) {
            this.x = x;
            this.y = y;
            this.hasBomb = hasBomb;

            border.setStroke(Color.LIGHTGRAY);

            text.setFont(Font.font(18));
            text.setText(hasBomb ? "X" : "");
            text.setVisible(false);

            getChildren().addAll(border, text);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);

            setOnMouseClicked(e -> open());
        }

        public void open() {
            if (isOpen)
                return;

            if (hasBomb) {
                System.out.println("Game Over");
                scene.setRoot(gameOver());
//                scene.setRoot(createContent());
                return;
            }

            isOpen = true;
            text.setVisible(true);
            score ++;
            border.setFill(null);

            if (text.getText().isEmpty()) {
                getNeighbors(this).forEach(Tile::open);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {

        music();

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
        chooseLabel.setFont(Font.font(20));
        VBox vBox = new VBox(20, startLabel, chooseLabel, easyButton, mediumButton, hardButton);
        vBox.setAlignment(Pos. CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        startScene = new Scene(vBox, 600, 400);

        easyButton.setOnAction(e -> {
            W = 600;
            H = 400;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
            primaryStage.setScene(scene);
        });

        mediumButton.setOnAction(e -> {
            W = 800;
            H = 600;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
            primaryStage.setScene(scene);
        });
//
        hardButton.setOnAction(e -> {
            W = 1000;
            H = 800;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
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
