package app.gui;

import app.game.Snake;
import app.util.Direction;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeKeyAdapter extends KeyAdapter {
    private Snake snake;
    private final int up;
    private final int down;
    private final int left;
    private final int right;


    SnakeKeyAdapter(Snake snake, int up, int down, int left, int right){
        this.snake = snake;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }


    @Override
    public void keyPressed(KeyEvent e) {
            int pressedKey = e.getKeyCode();
            if (pressedKey == up) snake.setAction(() -> snake.setDirection(Direction.UP));
                else if (pressedKey == down) snake.setAction(() -> snake.setDirection(Direction.DOWN));
                else if (pressedKey == left) snake.setAction(() -> snake.setDirection(Direction.LEFT));
                else if (pressedKey == right) snake.setAction(() -> snake.setDirection(Direction.RIGHT));
                else if (pressedKey ==  KeyEvent.VK_E) snake.setAction(() -> snake.grow(6));
    }
}
