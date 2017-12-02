package app.gui;


import app.game.Board;
import app.game.SimpleObject;
import app.game.Snake;
import app.util.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePanelFX {

    private BorderPane root;
    private Pane mainPane, topPane;
    private Scene scene;
    private Board board = new Board();
    private State state;
    private AnimationTimer timer;

    public GamePanelFX(Scene scene, State state) {
        this.state = state;
        root = new BorderPane();
        this.scene = scene;
        mainPane = new Pane();
        topPane = new Pane();
        root.setTop(topPane);
        root.setCenter(mainPane);

        topPane.setMinSize(GameConsts.PANEL_WIDTH, GameConsts.HEIGHT);

        mainPane.setStyle("-fx-background-color: #808080;");

        this.state = state;
        setGameState(state);

        timer = new Timer(GameConsts.PAINT_DELAY) {
            @Override
            public void handle() {
                render();
                board.updateBoard();
            }
        };
        timer.start();

    }

    private void render(){

        mainPane.getChildren().clear();
        for (SimpleObject e : board.getGameObjects()) {
            Rectangle rect = new Rectangle(GameConsts.CELL_SIZE - 2, GameConsts.CELL_SIZE - 2, e.getColor());
            rect.setTranslateX(e.getLocation().x * GameConsts.CELL_SIZE + 1);
            rect.setTranslateY(e.getLocation().y * GameConsts.CELL_SIZE - 1);
            mainPane.getChildren().add(rect);
        }

        topPane.getChildren().clear();
        Label gameScoreOne = new Label(String.valueOf(board.getSnake(0).getScore()));
        gameScoreOne.setScaleX(2);
        gameScoreOne.setScaleY(2);
        gameScoreOne.setTranslateX(GameConsts.PANEL_WIDTH/2);
        gameScoreOne.setTranslateY(10);
        topPane.getChildren().add(gameScoreOne);
        if (state == State.TWO_PLAYERS){
            gameScoreOne.setTranslateX(GameConsts.PANEL_WIDTH/2 - 100);
            Label gameScoreTwo = new Label(String.valueOf(board.getSnake(1).getScore()));
            gameScoreTwo.setScaleX(2);
            gameScoreTwo.setScaleY(2);
            gameScoreTwo.setTranslateX(GameConsts.PANEL_WIDTH/2 + 100);
            gameScoreTwo.setTranslateY(10);
            topPane.getChildren().add(gameScoreTwo);
        }


        if(board.gameIsOver()){

            timer.stop();
            gameOverRender();
            scene.setOnKeyPressed(event -> {
                switch (event.getCode()){
                    case SPACE:
                        scene.setRoot(MenuPanelFX.asRoot());
                        break;
                    case R:
                        setGameState(state);
                        timer.start();
                }
            });
        }

    }

    private void gameOverRender(){
        Label gameOver = new Label("Game Over");
        gameOver.setTextFill(Color.RED);
        gameOver.setScaleX(8);
        gameOver.setScaleY(8);
        gameOver.setTranslateX(GameConsts.PANEL_WIDTH/2);
        gameOver.setTranslateY(GameConsts.PANEL_HEIGHT/2 - 4*GameConsts.WIDTH);

        Label exitToMenu = new Label("Press SPACE to exit in menu");
        exitToMenu.setTextFill(Color.AQUA);
        exitToMenu.setScaleX(3);
        exitToMenu.setScaleY(3);
        exitToMenu.setAlignment(Pos.CENTER);
        exitToMenu.setTranslateX(GameConsts.PANEL_WIDTH/2 - 50);
        exitToMenu.setTranslateY(GameConsts.PANEL_HEIGHT/2);

        Label restartGame = new Label("Press R to restart game");
        restartGame.setTextFill(Color.AQUA);
        restartGame.setScaleX(3);
        restartGame.setScaleY(3);
        restartGame.setAlignment(Pos.CENTER);
        restartGame.setTranslateX(GameConsts.PANEL_WIDTH/2 - 50);
        restartGame.setTranslateY(GameConsts.PANEL_HEIGHT/2 + 50);


        mainPane.getChildren().addAll(gameOver,exitToMenu, restartGame);
    }

    private void setGameState(State state){
        switch (state){
            case ONE_PLAYER:
                newOnePlayerGame();
                break;
            case TWO_PLAYERS:
                newTwoPlayersGame();
                break;
        }
    }

    private void newOnePlayerGame(){
        board = Level.levelWithOnePlayer();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            Snake snake = board.getSnake(0);
            switch (event.getCode()) {
                case UP:
                    snake.setAction(() -> snake.setDirection(Direction.UP));
                    break;
                case DOWN:
                    snake.setAction(() -> snake.setDirection(Direction.DOWN));
                    break;
                case LEFT:
                    snake.setAction(() -> snake.setDirection(Direction.LEFT));
                    break;
                case RIGHT:
                    snake.setAction(() -> snake.setDirection(Direction.RIGHT));
            }
        });

    }

    private void newTwoPlayersGame(){
        board = Level.levelWithTwoPlayers();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            Snake snake = board.getSnake(1);
            switch (event.getCode()) {
                case UP:
                    snake.setAction(() -> snake.setDirection(Direction.UP));
                    break;
                case DOWN:
                    snake.setAction(() -> snake.setDirection(Direction.DOWN));
                    break;
                case LEFT:
                    snake.setAction(() -> snake.setDirection(Direction.LEFT));
                    break;
                case RIGHT:
                    snake.setAction(() -> snake.setDirection(Direction.RIGHT));
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED,event -> {
            Snake snake = board.getSnake(0);
            switch (event.getCode()) {
                case W:
                    snake.setAction(() -> snake.setDirection(Direction.UP));
                    break;
                case S:
                    snake.setAction(() -> snake.setDirection(Direction.DOWN));
                    break;
                case A:
                    snake.setAction(() -> snake.setDirection(Direction.LEFT));
                    break;
                case D:
                    snake.setAction(() -> snake.setDirection(Direction.RIGHT));
            }
        });

    }

    public BorderPane asRoot(){
        return root;
    }
}
