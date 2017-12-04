package app.game;

import app.util.GameConsts;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.Queue;


public class Board {
    private ArrayList<Snake> snakes = new ArrayList<>();
    private boolean gameIsOver = false;
    private final Random random = new Random();
    private ArrayList<SimpleObject> stationaryGameObjects = new ArrayList<>();
    private ArrayList<SimpleObject> gameObjects;

    public Board() {
        snakes.add(new Snake());
        for (int i = 0; i < GameConsts.HEIGHT; i++) {
            stationaryGameObjects.add(new Wall(0, i));
            stationaryGameObjects.add(new Wall(GameConsts.WIDTH-1, i));
        }
        updateBoard();
    }
    public Board(List<SimpleObject> listOfObjects, List<Snake> snakes) {
        this.snakes = new ArrayList<>(snakes);
        stationaryGameObjects = new ArrayList<>(listOfObjects);
        updateBoard();
    }

    public void updateBoard(){
        gameObjects = new ArrayList<>(stationaryGameObjects);
        for (Snake snake: snakes){
            snake.updatePosition();
            gameObjects.addAll(snake.getParts());
        }
        checkCollisions();
        stationaryGameObjects.removeIf(GameObject::isDead);
        if (stationaryGameObjects.stream().noneMatch(obj -> obj instanceof Apple)) createNewFood();
        if (snakes.stream().anyMatch(snake -> snake.getScore() > 50)) gameIsOver = true;
        System.out.println(snakes.size());
        if (snakes.size() < 2) {
            if (snakes.stream().anyMatch(Snake::isDead)) gameIsOver = true;
        } else
            snakes.stream().filter(Snake::isDead).forEach(Snake::respawn);
    }

    private void createNewFood(){
        int x = random.nextInt(GameConsts.WIDTH);
        int y = random.nextInt(GameConsts.HEIGHT);
        if (gameObjects.stream()
                .noneMatch(gameObject -> gameObject.getLocation().equals(new Point(x, y))))
            stationaryGameObjects.add(new Apple(x, y));
        else
            createNewFood();
    }

    private void checkCollisions(){
        for (int i = 0; i < gameObjects.size() - 1; i++) {
            for (int j = i+1; j < gameObjects.size(); j++) {
                SimpleObject firstObject = gameObjects.get(i);
                SimpleObject secondObject = gameObjects.get(j);
                if(firstObject.getLocation().equals(secondObject.getLocation())) {
                    firstObject.collideWith(secondObject);
                    secondObject.collideWith(firstObject);
                }
            }
        }
    }

    public boolean gameIsOver() {return gameIsOver;}

    public Snake getSnake(int i) {return snakes.get(i);}

    public ArrayList<SimpleObject> getGameObjects() {return gameObjects;}
}
