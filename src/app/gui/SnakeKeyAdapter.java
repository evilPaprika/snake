package app.gui;

import app.game.Snake;
import app.util.Direction;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeKeyAdapter extends KeyAdapter {
    // управление для змеи

    private Snake snake;

    // нужно для того чтобы нельзя было послать несколько
    // комманд на одной позиции
    private Point previosHeadPosition;
    private int previousKey;

    public SnakeKeyAdapter(Snake snake){
        previosHeadPosition = snake.getParts().peekFirst().getLocation();
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (previosHeadPosition != snake.getParts().peekFirst().getLocation() && e.getKeyCode() != previousKey) {
            previosHeadPosition = snake.getParts().peekFirst().getLocation();
            previousKey = e.getKeyCode();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
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
            }
        }
    }
}
