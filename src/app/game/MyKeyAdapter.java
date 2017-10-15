package app.game;

import app.util.Direction;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter {
    // управление для змеи

    private Snake snake;

    // нужно для того чтобы нельзя было послать несколько
    // комманд на одной позиции
    private Point headPosition;
    private char previousKey;

    public MyKeyAdapter(Snake snake){
        headPosition = snake.body.peekFirst().getLocation();
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (headPosition != snake.body.peekFirst().getLocation() && e.getKeyChar() != previousKey) {
            headPosition = snake.body.peekFirst().getLocation();
            previousKey = e.getKeyChar();
            switch (e.getKeyChar()) {
                case 'w':
                    snake.setDirection(Direction.UP);
                    break;
                case 's':
                    snake.setDirection(Direction.DOWN);
                    break;
                case 'a':
                    snake.setDirection(Direction.LEFT);
                    break;
                case 'd':
                    snake.setDirection(Direction.RIGHT);
                    break;
                case 'e':
                    snake.grow(5);
                    break;
                default:
                    break;
            }
        }
    }
}
