package app.gui;

import app.game.Snake;
import app.util.Direction;

import app.util.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeKeyAdapter extends KeyAdapter {
    // управление для змеи

    private Snake snake;

    // нужно для того чтобы нельзя было послать несколько
    // комманд на одной позиции
    private Point previosHeadPosition;
    private int previousKey;
    private final int up;
    private final int down;
    private final int left;
    private final int right;


    public SnakeKeyAdapter(Snake snake, int up, int down, int left, int right){
        previosHeadPosition = snake.getParts().peekFirst().getLocation();
        this.snake = snake;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (previosHeadPosition != snake.getParts().peekFirst().getLocation() && e.getKeyCode() != previousKey) {
            previosHeadPosition = snake.getParts().peekFirst().getLocation();
            previousKey = e.getKeyCode();
            if (previousKey == up) snake.setDirection(Direction.UP);
            else if (previousKey == down) snake.setDirection(Direction.DOWN);
            else if (previousKey == left) snake.setDirection(Direction.LEFT);
            else if (previousKey == right) snake.setDirection(Direction.RIGHT);
            else if (previousKey ==  KeyEvent.VK_E) snake.grow(6);
            /*
            switch (e.getKeyCode()) {
                case up:
                    snake.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_S:
                    snake.setDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_A:
                    snake.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_D:
                    snake.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_E:
                    snake.grow(6);
                    break;
                default:
                    break;
            } */
        }
    }
}