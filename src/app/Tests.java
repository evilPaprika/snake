package app;

import app.game.*;
import app.util.Direction;
import app.util.GameConsts;
import app.util.Point;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;


class Tests {
    private Board testBoard(Snake snake){
        ArrayList<Snake> snakes = new ArrayList<>();
        snakes.add(snake);
        ArrayList<SimpleObject> objects = new ArrayList<>();
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            objects.add(new Wall(0, i));
        return new Board(objects, snakes);
    }

    private Board testBoardWithApple(Apple apple, Snake snake){
        ArrayList<Snake> snakes = new ArrayList<>();
        snakes.add(snake);
        ArrayList<SimpleObject> objects = new ArrayList<>();
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            objects.add(new Wall(0, i));
        objects.add(apple);
        return new Board(objects, snakes);
    }

    @Test
    void appleCreation() {
        Apple apple = new Apple(2,2);
        assertEquals(apple.getLocation(), new Point(2,2));
        assertEquals(apple.getColor(), Color.RED);
    }

    @Test
    void wallCreation(){
        Wall wall = new Wall(2,2);
        assertEquals(wall.getLocation(), new Point(2,2));
        assertEquals(wall.getColor(), Color.BLACK);
    }

    @Test
    void snakeCreation(){
        Snake snake = new Snake(Direction.LEFT, new Point(3,3), 2);
        assertEquals(snake.getParts().peekFirst().getLocation(), new Point(3,3));
        assertEquals(snake.getParts().peekFirst().getColor(), Color.GREEN);
    }

    @Test
    void snakeMoves() {
        Board board = testBoard(new Snake());
        Point initialLocation = board.getSnake(0).getParts().peekFirst().getLocation();
        board.updateBoard();
        assertEquals(board.getSnake(0).getParts().peekFirst().getLocation(), new Point (initialLocation.x, initialLocation.y + 1));
    }

    @Test
    void snakeMovesDownAndRight(){
        Board board = testBoard(new Snake());
        Point initialLocation = board.getSnake(0).getParts().peekFirst().getLocation();
        board.updateBoard();
        board.getSnake(0).setDirection(Direction.RIGHT);
        board.updateBoard();
        assertEquals(board.getSnake(0).getParts().peekFirst().getLocation(), new Point (initialLocation.x + 1, initialLocation.y + 1));
    }

    @Test
    void allSnakeSegmentsMove(){
        Board board = testBoard(new Snake());
        for (int i = 0; i<4; i++) board.updateBoard();
        LinkedList<SnakeSegment> snakeBody = new LinkedList<>(board.getSnake(0).getParts());
        board.updateBoard();
        for (int i = 0; i < board.getSnake(0).getParts().size(); i++) {
            assertEquals(snakeBody.get(i).getLocation().x, board.getSnake(0).getParts().get(i).getLocation().x);
            assertEquals(snakeBody.get(i).getLocation().y + 1, board.getSnake(0).getParts().get(i).getLocation().y);
        }
    }

    @Test
    void headTurnsRightTailDoesnt(){
        Board board = testBoard(new Snake());
        for (int i = 0; i<4; i++) board.updateBoard();
        LinkedList<SnakeSegment> snakeBody = new LinkedList<>(board.getSnake(0).getParts());
        board.getSnake(0).setDirection(Direction.RIGHT);
        board.updateBoard();
        assertEquals(board.getSnake(0).getParts().peekFirst().getLocation().x, snakeBody.peekFirst().getLocation().x + 1);
        assertEquals(board.getSnake(0).getParts().peekFirst().getLocation().y, snakeBody.peekFirst().getLocation().y);
        for (int i = 1; i < board.getSnake(0).getParts().size(); i++) {
            assertEquals(snakeBody.get(i).getLocation().x, board.getSnake(0).getParts().get(i).getLocation().x);
            assertEquals(snakeBody.get(i).getLocation().y + 1, board.getSnake(0).getParts().get(i).getLocation().y);
        }
    }

    @Test
    void snakeEatsApple(){
        Apple apple = new Apple(6, 5);
        Board board = testBoardWithApple(apple, new Snake(Direction.RIGHT, new Point(5,5), 0));
        board.updateBoard();
        board.updateBoard();
        assertEquals(board.getSnake(0).getParts().size(), 2);
        assertTrue(apple.isDead());
        assertEquals(board.getSnake(0).getScore(), 10);
    }

    @Test
    void appleDisappearsAfterBeingEaten() {
        Apple apple = new Apple(6, 5);
        Board board = testBoardWithApple(apple, new Snake(Direction.RIGHT, new Point(5,5), 0));
        board.updateBoard();
        board.updateBoard();
        assertFalse(board.getGameObjects().contains(apple));
    }

    @Test
    void snakeCollidesWithWall(){
        Board board = testBoard(new Snake(Direction.LEFT, new Point(1, 1), 3));
        board.updateBoard();
        assertTrue(board.getSnake(0).isDead());
        assertTrue(board.isGameOver());
    }

    @Test
    void snakeEatsItself(){
        Board board = testBoard(new Snake(Direction.DOWN, new Point(3, 3), 10));
        board.updateBoard();
        Direction[] directions = {Direction.LEFT, Direction.UP, Direction.RIGHT};
        for(Direction direction: directions){
            board.getSnake(0).setDirection(direction);
            board.updateBoard();
        }
        assertTrue(board.getSnake(0).isDead());
        assertTrue(board.isGameOver());
    }

    @Test
    void snakeMovesOutOfUpperBorder(){
        Board board = testBoard(new Snake(Direction.UP, new Point(3, 1), 1));
        board.updateBoard();
        assertEquals(board.getSnake(0).getParts().peekFirst().getLocation(), new Point(3, GameConsts.HEIGHT - 1));
        assertEquals(board.getSnake(0).getParts().get(1).getLocation(), new Point (3, 0));
    }

    @Test
    void snakeMovesOutOfLeftBorder(){
        Board board = testBoard(new Snake(Direction.LEFT, new Point(1, 1), 1));
        board.updateBoard();
        assertEquals(board.getSnake(0).getParts().peekFirst().getLocation(), new Point(GameConsts.WIDTH - 1, 1));
        assertEquals(board.getSnake(0).getParts().get(1).getLocation(), new Point (0, 1));
    }

    @Test
    void snakesHeadStepsAfterTail(){
        Board board = testBoard(new Snake(Direction.LEFT, new Point(5, 5), 3));
        Direction[] directions = {Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.UP};
        for(Direction direction: directions){
            board.getSnake(0).setDirection(direction);
            board.updateBoard();
        }
        assertFalse(board.getSnake(0).isDead());
        assertFalse(board.isGameOver());
    }
}