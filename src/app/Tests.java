package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import app.game.*;
import app.util.Direction;
import app.util.GameConsts;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

public class Tests {
    ArrayList<GameObject> testMap(){
        ArrayList<GameObject> objects = new ArrayList<>();
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            objects.add(new Wall(0, i));
        return objects;
    }

    ArrayList<GameObject> testMapWithApple(Apple apple){
        ArrayList<GameObject> objects = new ArrayList<>();
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            objects.add(new Wall(0, i));
        objects.add(apple);
        return objects;
    }

    @Test
    public void appleCreation() {
        Apple apple = new Apple(2,2);
        assertEquals(apple.getLocation(), new Point(2,2));
    }

    @Test
    public void WallCreation(){
        Wall wall = new Wall(2,2);
        assertEquals(wall.getLocation(), new Point(2,2));
    }

    @Test
    public void snakeMoves() {
        Board board = new Board(testMap());
        Point initialLocation = board.snake.body.peekFirst().getLocation();
        board.updateBoard();
        assertEquals(board.snake.body.peekFirst().getLocation(), new Point (initialLocation.x, initialLocation.y + 1));
    }

    @Test
    public void snakeEatsApple(){
        Apple apple = new Apple(6, 5);
        Board board = new Board(testMapWithApple(apple));
        board.snake = new Snake(Direction.RIGHT, new Point(5,5), 0);
        board.updateBoard();
        board.updateBoard();
        assertEquals(board.snake.body.size(), 2);
        assertEquals(apple.isDead, true);
        assertEquals(board.snake.score, 10);
    }

    @Test
    public void snakeCollidesWithWall(){
        Board board = new Board(testMap());
        board.snake = new Snake(Direction.LEFT, new Point(1, 1), 3);
        board.updateBoard();
        assertEquals(board.snake.isDead, true);
        assertEquals(board.gameIsOver, true);
    }

    @Test
    public void snakeEatsItself(){
        Board board = new Board(testMap());
        board.snake = new Snake(Direction.DOWN, new Point(3, 3), 10);
        board.updateBoard();
        Direction[] directions = {Direction.LEFT, Direction.UP, Direction.RIGHT};
        for(Direction direction: directions){
            board.snake.setDirection(direction);
            board.updateBoard();
        }
        assertEquals(board.snake.isDead, true);
        assertEquals(board.gameIsOver, true);
    }


}