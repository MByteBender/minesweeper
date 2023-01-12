package com.example.minesweeper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
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

    private int highscore = 0;
    private  int score = 0;


    private final Tile[][] grid = new Tile[X_TILES][Y_TILES];
    private Scene scene;
    private Scene startScene;

    private Stage primaryStage;


    private int bombCounter;

    private String mode; //1 - e, 2-d, 3-m



    private Parent startMenu(){

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
        VBox vBox = new VBox(20, startLabel, chooseLabel, easyButton, mediumButton, hardButton, scoreButton);
        vBox.setAlignment(Pos. CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));



        easyButton.setOnAction(e -> {
            System.out.println(scene);
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

        scoreButton.setOnAction(e -> {
            scene = new Scene(scoreRoom());
            primaryStage.setScene(scene);
        });

//        startScene = new Scene(vBox, 600, 400);

        return vBox;
    }

    private Parent scoreRoom(){
        Button returnButton = new Button("Back");
        returnButton.setPrefWidth(80);

        Label name1 = new Label("Username");
        Label score1 = new Label("Score");

        Label highScore = new Label(FileHandler.ReadFile());

        BorderPane bp = new BorderPane();
        bp.setPrefSize(600, 400);

        VBox vBox = new VBox(10, returnButton);
        vBox.setAlignment(Pos. BOTTOM_CENTER);

        HBox center = new HBox(100, highScore);
        center.setAlignment(Pos. CENTER);

        HBox hBox = new HBox(100, name1, score1);
        hBox.setAlignment(Pos. TOP_CENTER);

        highScore.setTextFill(Color.WHITE);
        highScore.setFont(Font.font(null, FontWeight.BOLD, 20));
        name1.setTextFill(Color.WHITE);
        name1.setFont(Font.font(null, FontWeight.BOLD, 20));
        score1.setTextFill(Color.WHITE);
        score1.setFont(Font.font(null, FontWeight.BOLD, 20));

        bp.setBottom(vBox);
        bp.setTop(hBox);
        bp.setCenter(center);
        bp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        bp.setPadding(new Insets(10));

        returnButton.setOnAction(e -> scene.setRoot(startMenu()));

        return bp;
    }
    private Parent gameOver(){

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setAlignment(Pos.CENTER);


        String s = "gameOver.gif";
        Image gameOverGif = new Image(Paths.get(s).toUri().toString());
        ImageView img2 = new ImageView(gameOverGif);

        Label highScoreLabel = new Label("Score: " + score * 10);

        //TODO: open highscore.xt, check highscore, highscore < score -> save data with new highscore


        score = score * 10;
        if (score > highscore){
            highscore = score * 10;
            highscore /= 10;
        }
        FileHandler.WriteToFile(highscore);


        score = 0;
        bombCounter = 0;
        highScoreLabel.setTextFill(Color.RED);
        highScoreLabel.setFont(Font.font(null, FontWeight.BOLD, 20));

        Label l1 = new Label("Game Over!!!");
        l1.setTextFill(Color.RED);
        l1.setFont(Font.font(null, FontWeight.BOLD, 50));




        Button restart = new Button("Try Again");
        restart.setPrefWidth(150);

        vBox.getChildren().addAll(highScoreLabel,l1, restart, img2);
        restart.setOnAction(e -> scene.setRoot(startMenu()));

        FileHandler.CreateFile();


        return vBox;
    }

    private Parent gameWon(){

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setAlignment(Pos.CENTER);

        String s = "gameWon.gif";
        Image gameWonGif = new Image(Paths.get(s).toUri().toString());
        ImageView img3 = new ImageView(gameWonGif);


        Label highScoreLabel = new Label("Score: " + score * 10);

        //TODO: open highscore.xt, check highscore, highscore < score -> save data with new highscore


        score = score * 10;
        if (score > highscore){
            highscore = score * 10;
            highscore /= 10;
        }
        FileHandler.WriteToFile(highscore);


        score = 0;
        bombCounter = 0;
        highScoreLabel.setTextFill(Color.RED);
        highScoreLabel.setFont(Font.font(null, FontWeight.BOLD, 20));

        Label l1 = new Label("Game WON!!!");
        l1.setTextFill(Color.RED);
        l1.setFont(Font.font(null, FontWeight.BOLD, 50));



        Button restart = new Button("Try Again");
        restart.setPrefWidth(150);

        vBox.getChildren().addAll(highScoreLabel,l1, restart, img3);
        restart.setOnAction(e -> scene.setRoot(startMenu()));

        FileHandler.CreateFile();


        return vBox;
    }
    private Parent createContent() {

        Pane root = new Pane();

        root.setPrefSize(W, H);

        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = new Tile(x, y, Math.random() < 0.15);
                if (tile.hasBomb) {
                    bombCounter++;
//                    System.out.println(x+"/"+y);
                }
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

            setOnMouseClicked(e -> {
                open();
                Sound.mouseClickSound();
            });
        }

        public void open() {
            if (isOpen)
                return;

            if (hasBomb) {
                System.out.println("Game Over");
                Sound.bombSound();
                scene.setRoot(gameOver());
//                scene.setRoot(createContent());
                return;
            }

            isOpen = true;
            text.setVisible(true);
            score++;
            border.setFill(null);

            if (text.getText().isEmpty()) {
                getNeighbors(this).forEach(Tile::open);
//               for (int i = 0; i < getNeighbors(this).size(); i++) {
//                    getNeighbors(this).get(i).open();
//                }
            }
            if (text.getText().isEmpty()) {
                getNeighbors(this).forEach(Tile::open);
            }
            // check if win
            int openTiles = 0;
            for (int y = 0; y < Y_TILES; y++) {
                for (int x = 0; x < X_TILES; x++) {
                    Tile tile = grid[x][y];

                    if (tile.isOpen) {
                        openTiles++;
                    }
                }
            }
            if ((openTiles + bombCounter) == (Y_TILES*X_TILES)) {
                scene.setRoot(gameWon());
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {


        FileHandler.CreateFile();
        Sound.backgroundMusic();

        primaryStage.setResizable(false);

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
        chooseLabel.setFont(Font.font(20));
        VBox vBox = new VBox(20, startLabel, chooseLabel, easyButton, mediumButton, hardButton, scoreButton);
        vBox.setAlignment(Pos. CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        startScene = new Scene(vBox, 600, 400);
        this.primaryStage = primaryStage;

        easyButton.setOnAction(e -> {
            mode = "easy";
            W = 600;
            H = 400;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
            primaryStage.setScene(scene);
        });

        mediumButton.setOnAction(e -> {
            mode = "medium";
            W = 800;
            H = 600;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
            primaryStage.setScene(scene);
        });
//
        hardButton.setOnAction(e -> {
            mode = "hard";
            W = 1000;
            H = 800;
            X_TILES = W / TILE_SIZE;
            Y_TILES = H / TILE_SIZE;
            scene = new Scene(createContent());
            primaryStage.setScene(scene);
        });

        scoreButton.setOnAction(e -> {
            scene = new Scene(scoreRoom());
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
