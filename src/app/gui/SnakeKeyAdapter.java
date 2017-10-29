package app.gui;

import app.game.Snake;
import app.util.Direction;

import app.util.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SnakeKeyAdapter extends KeyAdapter {
    // управление для змеи

    private Snake firstSnake;
    private Snake secondSnake;

    // нужно для того чтобы нельзя было послать несколько
    // комманд на одной позиции
    private Point firstSnakePreviousHeadPosition;
    private Point secondSnakePreviousHeadPosition;
    private int previousKey;

    SnakeKeyAdapter(ArrayList<Snake> snakes){
        firstSnake = snakes.get(0);
        firstSnakePreviousHeadPosition = firstSnake.getParts().peekFirst().getLocation();
        if (snakes.size() > 1) {
            secondSnake = snakes.get(1);
            secondSnakePreviousHeadPosition = secondSnake.getParts().peekFirst().getLocation();
        }
        }

    @Override
    public void keyPressed(KeyEvent e) {
        if (firstSnakePreviousHeadPosition != firstSnake.getParts().peekFirst().getLocation()
            && e.getKeyCode() != previousKey) {
            firstSnakePreviousHeadPosition = firstSnake.getParts().peekFirst().getLocation();
            previousKey = e.getKeyCode();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    firstSnake.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_S:
                    firstSnake.setDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_A:
                    firstSnake.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_D:
                    firstSnake.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_E:
                    firstSnake.grow(6);
                    break;
                case KeyEvent.VK_UP:
                    secondSnake.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_RIGHT:
                    secondSnake.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_LEFT:
                    secondSnake.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_DOWN:
                    secondSnake.setDirection(Direction.DOWN);
                    break;
                default:
                    break;
            }
        }
    }
}
