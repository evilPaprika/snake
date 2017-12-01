package app.gui;


import app.game.Board;
import app.game.SimpleObject;
import app.game.Snake;
import app.util.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Optional;

public class GamePanelFX {

    private BorderPane root;
    private Pane mainPane;
    private Scene scene;
    private Board board = new Board();
    private State state;
    private AnimationTimer timer;

    public GamePanelFX(Scene scene, State state) {
        root = new BorderPane();
        this.scene = scene;
        mainPane = new Pane();
        root.setCenter(mainPane);

        mainPane.setStyle("-fx-background-color: #808080;");

        this.state = state;
        if (state == State.ONE_PLAYER){
            newOnePlayerGame();
        }
        if (state == State.TWO_PLAYERS){
            newTwoPlayersGame();
        }

        timer = new Timer(GameConsts.PAINT_DELAY) {
            @Override
            public void handle() {
                drawComponent();
                board.updateBoard();

            }
        };
        timer.start();

    }

    private void drawComponent(){
        mainPane.getChildren().clear();
        for (SimpleObject e : board.getGameObjects()) {
            Rectangle rect = new Rectangle(GameConsts.CELL_SIZE - 2, GameConsts.CELL_SIZE - 2, e.getColor());
            rect.setTranslateX(e.getLocation().x * GameConsts.CELL_SIZE + 1);
            rect.setTranslateY(e.getLocation().y * GameConsts.CELL_SIZE - 1);
            mainPane.getChildren().add(rect);
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

        //addKeyListener(new SnakeKeyAdapter(board.getSnake(1), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT));
        //addKeyListener(new SnakeKeyAdapter(board.getSnake(0), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D));
    }

    public BorderPane asRoot(){
        return root;
    }
}
