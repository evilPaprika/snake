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
        stationaryGameObjects.add(createNewFood());
        updateBoard();
    }

    public void updateBoard(){
        snake.updatePosition();
        gameObjects = new ArrayList<GameObject>(stationaryGameObjects);
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
        for (GameObject e1: gameObjects) {
            for (GameObject e2: gameObjects) {
                if(e1 != e2 && e1.getLocation().equals(e2.getLocation())) {
                    e1.actionWhenColidedWith(e2);
                    e2.actionWhenColidedWith(e1);
                }
            }
        }
    }
}
