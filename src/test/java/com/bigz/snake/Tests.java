package com.bigz.snake;

import com.bigz.app.game.*;
import com.bigz.app.util.Direction;
import com.bigz.app.util.GameConsts;
import javafx.scene.paint.Color;
import org.junit.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;


public class Tests {
    private ArrayList<Snake> snakes = new ArrayList<>();
    private Board board;
    private Snake snake1;
    private Snake snake2;
    private Point expected_location;
    private Point actual_location;

    public Board testBoard(Snake snake){
        snakes.clear();
        snakes.add(snake);
        ArrayList<SimpleObject> objects = new ArrayList<>();
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            objects.add(new Wall(0, i));
        return new Board(objects, snakes);
    }

    public Board testBoardWithApple(Apple apple, Snake snake){
        snakes.clear();
        snakes.add(snake);
        ArrayList<SimpleObject> objects = new ArrayList<>();
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            objects.add(new Wall(0, i));
        objects.add(apple);
        return new Board(objects, snakes);
    }

    public Board testBoardWithTwoSnakes(Snake snake1, Snake snake2){
        snakes.clear();
        snakes.add(snake1);
        snakes.add(snake2);
        ArrayList<SimpleObject> objects = new ArrayList<>();
        return new Board(objects, snakes);
    }

    @Test
    public void appleCreation() {
        Apple apple = new Apple(2,2);
        assertEquals(apple.getLocation(), new Point(2,2));
        assertEquals(apple.getColor(), Color.RED);
    }

    @Test
    public void wallCreation(){
        Wall wall = new Wall(2,2);
        assertEquals(wall.getLocation(), new Point(2,2));
        assertEquals(wall.getColor(), Color.BLACK);
    }

    @Test
    public void snakeCreation(){
        Snake snake = new Snake(Direction.LEFT, new Point(3,3), 2, Color.GREEN);
        assertEquals(snake.getParts().peekFirst().getLocation(), new Point(3,3));
        assertEquals(snake.getParts().peekFirst().getColor(), Color.GREEN);
    }

    @Test
    public void snakeMoves() {
        snake1 = new Snake();
        board = testBoard(snake1);
        Point initialLocation = snake1.getParts().peekFirst().getLocation();
        board.updateBoard();
        expected_location = new Point (initialLocation.x, initialLocation.y + 1);
        actual_location = snake1.getParts().peekFirst().getLocation();
        assertEquals(expected_location, actual_location);
    }

    @Test
    public void snakeMovesDownAndRight(){
        snake1 = new Snake();
        board = testBoard(snake1);
        Point initialLocation = snake1.getParts().peekFirst().getLocation();
        board.updateBoard();
        snake1.setDirection(Direction.RIGHT);
        board.updateBoard();
        expected_location = new Point (initialLocation.x + 1, initialLocation.y + 1);
        actual_location = snake1.getParts().peekFirst().getLocation();
        assertEquals(expected_location, actual_location);
    }

    @Test
    public void allSnakeSegmentsMove(){
        snake1 = new Snake();
        board = testBoard(snake1);
        for (int i = 0; i<4; i++) board.updateBoard();
        LinkedList<Point> snakeBody = new LinkedList<>();
        for (SnakeSegment segment: snake1.getParts())
            snakeBody.add(segment.getLocation());
        board.updateBoard();
        for (int i = 0; i < board.getSnake(0).getLength(); i++) {
            expected_location = new Point(snakeBody.get(i).x, snakeBody.get(i).y + 1);
            actual_location = snake1.getParts().get(i).getLocation();
            assertEquals(expected_location, actual_location);
        }
    }

    @Test
    public void headTurnsRightTailDoesnt(){
        snake1 = new Snake();
        board = testBoard(snake1);
        for (int i = 0; i<4; i++) board.updateBoard();
        LinkedList<Point> snakeBody = new LinkedList<>();
        for (SnakeSegment segment: snake1.getParts())
            snakeBody.add(segment.getLocation());
        snake1.setDirection(Direction.RIGHT);
        board.updateBoard();
        expected_location = new Point(snakeBody.peekFirst().x + 1, snakeBody.peekFirst().getLocation().y);
        actual_location = snake1.getParts().peekFirst().getLocation();
        assertEquals(expected_location, actual_location);
        for (int i = 1; i < board.getSnake(0).getLength(); i++) {
            expected_location = new Point(snakeBody.get(i).x, snakeBody.get(i).y + 1);
            actual_location = snake1.getParts().get(i).getLocation();
            assertEquals(expected_location, actual_location);
        }
    }

    @Test
    public void snakeEatsApple(){
        Apple apple = new Apple(6, 5);
        snake1 = new Snake(Direction.RIGHT, new Point(5,5), 0, Color.GREEN);
        board = testBoardWithApple(apple, snake1);
        board.updateBoard();
        board.updateBoard();
        int length = snake1.getLength();
        assertEquals(length, 2);
        assertTrue(apple.isDead());
        assertEquals(snake1.getScore(), apple.getNutritionalValue() * (length * length)/90);
    }

    @Test
    public void appleDisappearsAfterBeingEaten() {
        Apple apple = new Apple(6, 5);
        snake1 = new Snake(Direction.RIGHT, new Point(5,5), 0, Color.GREEN);
        board = testBoardWithApple(apple, snake1);
        board.updateBoard();
        board.updateBoard();
        assertFalse(board.getGameObjects().contains(apple));
    }

    @Test
    public void snakeCollidesWithWall(){
        snake1 = new Snake(Direction.LEFT, new Point(1, 1), 3, Color.GREEN);
        board = testBoard(snake1);
        board.updateBoard();
        assertTrue(snake1.isDead());
        assertTrue(board.gameIsOver());
    }

    @Test
    public void snakeEatsItself(){
        snake1 = new Snake(Direction.DOWN, new Point(3, 3), 10, Color.GREEN);
        board = testBoard(snake1);
        board.updateBoard();
        Direction[] directions = {Direction.LEFT, Direction.UP, Direction.RIGHT};
        for(Direction direction: directions){
            snake1.setDirection(direction);
            board.updateBoard();
        }
        assertTrue(snake1.isDead());
        assertTrue(board.gameIsOver());
    }

    @Test
    public void snakeMovesOutOfUpperBorder(){
        snake1 = new Snake(Direction.UP, new Point(3, 1), 1, Color.GREEN);
        board = testBoard(snake1);
        board.updateBoard();
        assertEquals(snake1.getParts().peekFirst().getLocation(), new Point(3, GameConsts.HEIGHT - 1));
        assertEquals(snake1.getParts().get(1).getLocation(), new Point (3, 0));
    }

    @Test
    public void snakeMovesOutOfLeftBorder(){
        snake1 = new Snake(Direction.LEFT, new Point(1, 1), 1, Color.GREEN);
        board = testBoard(snake1);
        board.updateBoard();
        assertEquals(snake1.getParts().peekFirst().getLocation(), new Point(GameConsts.WIDTH - 1, 1));
    }

    @Test
    public void snakesHeadStepsAfterTail(){
        snake1 = new Snake(Direction.LEFT, new Point(5, 5), 3, Color.GREEN);
        board = testBoard(snake1);
        Direction[] directions = {Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.UP};
        for(Direction direction: directions){
            snake1.setDirection(direction);
            board.updateBoard();
        }

        assertFalse(snake1.isDead());
        assertFalse(board.gameIsOver());
    }

    @Test
    public void snakesHeadsCollide(){
        snake1 = new Snake(Direction.DOWN, new Point(5,5), 3, Color.GREEN);
        snake2 = new Snake(Direction.LEFT, new Point(6, 6), 3, Color.BLUE);
        board = testBoardWithTwoSnakes(snake1, snake2);
        board.updateBoard();
        assertEquals(new Point(5,5), snake1.getParts().getFirst().getLocation());
        assertEquals(new Point(6,6), snake2.getParts().getFirst().getLocation());
        assertTrue(!board.gameIsOver());
//        assertTrue(snake1.isDead());
//        assertTrue(snake2.isDead());
//        assertTrue(board.gameIsOver());
    }

    @Test
    public void snakeEatsTailOfAnotherSnake(){
        snake1 = new Snake(Direction.DOWN, new Point(5,5), 5, Color.GREEN);
        snake2 = new Snake(Direction.LEFT, new Point(10, 9), 3, Color.BLUE);
        board = testBoardWithTwoSnakes(snake1, snake2);
        for (int i = 0; i < 5; i++) {
            board.updateBoard();
        }
        assertEquals(snake1.getLength(), 1);
        assertFalse(board.gameIsOver());
    }
}