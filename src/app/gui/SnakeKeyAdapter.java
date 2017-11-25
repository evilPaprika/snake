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
            if (pressedKey == up) snake.addAction(() -> snake.setDirection(Direction.UP));
                else if (pressedKey == down) snake.addAction(() -> snake.setDirection(Direction.DOWN));
                else if (pressedKey == left) snake.addAction(() -> snake.setDirection(Direction.LEFT));
                else if (pressedKey == right) snake.addAction(() -> snake.setDirection(Direction.RIGHT));
                else if (pressedKey ==  KeyEvent.VK_E) snake.addAction(() -> snake.grow(6));
    }
}
