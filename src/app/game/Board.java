package app.game;

import app.util.GameConsts;
import app.util.Point;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private Snake snake;
    private boolean gameIsOver = false;

    private final Random random = new Random();
    private ArrayList<SimpleObject> stationaryGameObjects = new ArrayList<>();
    private ArrayList<SimpleObject> gameObjects;

    public Board() {
        snake = new Snake();
        for (int i = 0; i < GameConsts.HEIGHT; i++) {
            stationaryGameObjects.add(new Wall(0, i));
            stationaryGameObjects.add(new Wall(GameConsts.WIDTH-1, i));
        }
        updateBoard();
    }
    public Board(ArrayList<SimpleObject> listOfObjects, Snake snake) {
        this.snake = snake;
        stationaryGameObjects = listOfObjects;
        updateBoard();
    }

    public void updateBoard(){
        snake.updatePosition();
        gameObjects = new ArrayList<>(stationaryGameObjects);
        gameObjects.addAll(snake.getParts());
        checkCollisions();
        stationaryGameObjects.removeIf(GameObject::isDead);
        if (stationaryGameObjects.stream().noneMatch(obj -> obj instanceof Apple)) createNewFood();
        if (snake.isDead()) gameIsOver = true;
    }

    private void createNewFood(){
        int x = random.nextInt(GameConsts.WIDTH);
        int y = random.nextInt(GameConsts.HEIGHT);
        if (gameObjects.stream()
                .noneMatch(gameObject -> gameObject.getLocation().equals(new Point(x, y))))
            stationaryGameObjects.add(new Apple(x, y));
    }

    private void checkCollisions(){
        for (int i = 0; i < gameObjects.size() - 1; i++) {
            for (int j = i+1; j < gameObjects.size(); j++) {
                if(gameObjects.get(i).getLocation().equals(gameObjects.get(j).getLocation())) {
                    gameObjects.get(i).collideWith(gameObjects.get(j));
                    gameObjects.get(j).collideWith(gameObjects.get(i));
                }
            }
        }
    }

    public boolean isGameOver() {return gameIsOver;}

    public Snake getSnake() {return snake;}

    public ArrayList<SimpleObject> getGameObjects() {return gameObjects;}
}
