package app.game;

import app.util.GameConsts;
import app.util.UtilFunctions;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    public Snake snake;
    public boolean gameIsOver = false;

    private Random random = new Random();
    private ArrayList<GameObject> stationaryGameObjects = new ArrayList<>();
    public ArrayList<GameObject> gameObjects;

    public Board() {
        snake = new Snake();
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            stationaryGameObjects.add(new Wall(0, i));
        updateBoard();
    }
    public Board(ArrayList<GameObject> listOfObjects, Snake snake) {
        this.snake = snake;
        stationaryGameObjects = listOfObjects;
        updateBoard();
    }

    public void updateBoard(){
        snake.updatePosition();
        gameObjects = new ArrayList<>(stationaryGameObjects);
        gameObjects.addAll(snake.body);
        CheckCollisions();
        stationaryGameObjects.removeIf(GameObject::isDead);
        if (!UtilFunctions.containsInstanceOf(stationaryGameObjects, Apple.class)) stationaryGameObjects.add(createNewFood());
        if (snake.isDead) gameIsOver = true;
    }

    private Apple createNewFood(){
        int x = random.nextInt(GameConsts.WIDTH);
        int y = random.nextInt(GameConsts.HEIGHT);
        return new Apple(x, y);
    }

    private void CheckCollisions(){
        for (int i = 0; i < gameObjects.size() - 1; i++) {
            for (int j = i+1; j < gameObjects.size(); j++) {
                if(gameObjects.get(i).getLocation().equals(gameObjects.get(j).getLocation())) {
                    gameObjects.get(i).actionWhenCollidedWith(gameObjects.get(j));
                    gameObjects.get(j).actionWhenCollidedWith(gameObjects.get(i));
                }
            }
        }
    }
}
