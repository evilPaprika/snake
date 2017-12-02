package app.util;

import app.game.Snake;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * Created by User on 03.12.2017.
 */
public class SnakeEventHandler {

    public static EventHandler<KeyEvent> getSnakeEventHandler(Snake snake, KeyCode up, KeyCode down, KeyCode left, KeyCode right) {

        return (event) -> {
            if (event.getCode() == up) snake.setAction(() -> snake.setDirection(Direction.UP));

            if (event.getCode() == down) snake.setAction(() -> snake.setDirection(Direction.DOWN));

            if (event.getCode() == left) snake.setAction(() -> snake.setDirection(Direction.LEFT));

            if (event.getCode() == right) snake.setAction(() -> snake.setDirection(Direction.RIGHT));
        };
    }

    }


