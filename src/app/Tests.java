package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import app.game.*;
import app.util.Direction;
import app.util.GameConsts;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Tests {
    Board testBoard(Snake snake){
        ArrayList<GameObject> objects = new ArrayList<>();
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            objects.add(new Wall(0, i));
        return new Board(objects, snake);
    }

    Board testBoardWithApple(Apple apple, Snake snake){
        ArrayList<GameObject> objects = new ArrayList<>();
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            objects.add(new Wall(0, i));
        objects.add(apple);
        return new Board(objects, snake);
    }

    @Test
    public void appleCreation() {
        Apple apple = new Apple(2,2);
        assertEquals(apple.getLocation(), new Point(2,2));
        assertEquals(apple.getColor(), Color.RED);
    }

    @Test
    public void WallCreation(){
        Wall wall = new Wall(2,2);
        assertEquals(wall.getLocation(), new Point(2,2));
        assertEquals(wall.getColor(), Color.BLACK);
    }

    @Test
    public void snakeCreation(){
        Snake snake = new Snake(Direction.LEFT, new Point(3,3), 2);
        assertEquals(snake.body.peekFirst().getLocation(), new Point(3,3));
        assertEquals(snake.body.peekFirst().getColor(), Color.GREEN);
    }

    @Test
    public void snakeMoves() {
        Board board = testBoard(new Snake());
        Point initialLocation = board.snake.body.peekFirst().getLocation();
        board.updateBoard();
        assertEquals(board.snake.body.peekFirst().getLocation(), new Point (initialLocation.x, initialLocation.y + 1));
    }

    @Test
    public void snakeMovesDownAndRight(){
        Board board = testBoard(new Snake());
        Point initialLocation = board.snake.body.peekFirst().getLocation();
        board.updateBoard();
        board.snake.setDirection(Direction.RIGHT);
        board.updateBoard();
        assertEquals(board.snake.body.peekFirst().getLocation(), new Point (initialLocation.x + 1, initialLocation.y + 1));
    }

    @Test
    public void allSnakeSegmentsMove(){
        Board board = testBoard(new Snake());
        for (int i = 0; i<4; i++) board.updateBoard();
        ArrayList<SnakeSegment> snakeBody = new ArrayList<SnakeSegment>(board.snake.body);
        board.updateBoard();
        for (int i = 0; i < board.snake.body.size(); i++) {
            assertEquals(snakeBody.get(i).getLocation().x, board.snake.body.get(i).getLocation().x);
            assertEquals(snakeBody.get(i).getLocation().y + 1, board.snake.body.get(i).getLocation().y);
        }
    }

    @Test
    public void snakeEatsApple(){
        Apple apple = new Apple(6, 5);
        Board board = testBoardWithApple(apple, new Snake(Direction.RIGHT, new Point(5,5), 0));
        board.updateBoard();
        board.updateBoard();
        assertEquals(board.snake.body.size(), 2);
        assertEquals(apple.isDead, true);
        assertEquals(board.snake.score, 10);
    }

    @Test
    public void appleDisappearsAfterBeingEaten() {
        Apple apple = new Apple(6, 5);
        Board board = testBoardWithApple(apple, new Snake(Direction.RIGHT, new Point(5,5), 0));
        board.updateBoard();
        board.updateBoard();
        assertEquals(board.gameObjects.contains(apple), false);
    }

    @Test
    public void snakeCollidesWithWall(){
        Board board = testBoard(new Snake(Direction.LEFT, new Point(1, 1), 3));
        board.updateBoard();
        assertEquals(board.snake.isDead, true);
        assertEquals(board.gameIsOver, true);
    }

    @Test
    public void snakeEatsItself(){
        Board board = testBoard(new Snake(Direction.DOWN, new Point(3, 3), 10));
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