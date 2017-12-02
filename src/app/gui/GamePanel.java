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

public class GamePanel {

    private BorderPane root;
    private Pane mainPane, topPane;
    private Scene scene;
    private Board board = new Board();
    private State state;
    private AnimationTimer timer;

    public GamePanel(Scene scene, State state) {
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

                board.updateBoard();

                if(board.gameIsOver()){
                    timer.stop();
                    gameOverRender();
                    scene.setOnKeyPressed(event -> {
                        switch (event.getCode()){
                            case SPACE:
                                scene.setRoot(MenuPanel.asRoot());
                                break;
                            case R:
                                setGameState(state);
                                timer.start();
                        }
                    });
                }
                else render();
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
        setLabel(gameScoreOne,2,GameConsts.PANEL_WIDTH/2,10);
        topPane.getChildren().add(gameScoreOne);
        if (state == State.TWO_PLAYERS){
            gameScoreOne.setTranslateX(GameConsts.PANEL_WIDTH/2 - 100);
            Label gameScoreTwo = new Label(String.valueOf(board.getSnake(1).getScore()));
            setLabel(gameScoreTwo,2,GameConsts.PANEL_WIDTH/2 + 100, 10);
            topPane.getChildren().add(gameScoreTwo);
        }

    }

    private void gameOverRender(){
        Label gameOver = new Label("Game Over");
        gameOver.setTextFill(Color.RED);
        setLabel(gameOver,8,GameConsts.PANEL_WIDTH/2,GameConsts.PANEL_HEIGHT/2 - 4*GameConsts.WIDTH);

        Label exitToMenu = new Label("Press SPACE to exit in menu");
        exitToMenu.setTextFill(Color.AQUA);
        exitToMenu.setAlignment(Pos.CENTER);
        setLabel(exitToMenu,3,GameConsts.PANEL_WIDTH/2 - 50,GameConsts.PANEL_HEIGHT/2);

        Label restartGame = new Label("Press R to restart game");
        restartGame.setTextFill(Color.AQUA);
        restartGame.setAlignment(Pos.CENTER);
        setLabel(restartGame,3,GameConsts.PANEL_WIDTH/2 - 50,GameConsts.PANEL_HEIGHT/2 + 50);
        mainPane.getChildren().addAll(gameOver,exitToMenu, restartGame);
    }

    private void setLabel(Label label,Integer scale, Integer posX, Integer posY){
        label.setScaleX(scale);
        label.setScaleY(scale);
        label.setTranslateX(posX);
        label.setTranslateY(posY);
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

    private void newOnePlayerGame() {
        board = Level.levelWithOnePlayer();

        scene.addEventHandler(KeyEvent.KEY_PRESSED,
                SnakeEventHandler.getSnakeEventHandler(board.getSnake(0), KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT));
    }

    private void newTwoPlayersGame(){
        board = Level.levelWithTwoPlayers();

        scene.addEventHandler(KeyEvent.KEY_PRESSED,
                SnakeEventHandler.getSnakeEventHandler(board.getSnake(1), KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT));

        scene.addEventHandler(KeyEvent.KEY_PRESSED,
                SnakeEventHandler.getSnakeEventHandler(board.getSnake(0), KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D));

    }

    public BorderPane asRoot(){
        return root;
    }
}
