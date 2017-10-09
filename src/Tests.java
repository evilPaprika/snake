import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class Tests {
    @Test
    public void snakeMovement() {
        Snake snake = new Snake();
        Point initialPosition = snake.body.peekFirst();
        snake.updatePosition();
        assertEquals(initialPosition.x, snake.body.peekFirst().x);
        assertEquals(initialPosition.y + 1, snake.body.peekFirst().y);
    }


    @Test
    public void snakeGrows(){
        Snake snake = new Snake();
        int initialLength = snake.body.size();
        snake.grow();
        assertEquals(snake.body.size() - initialLength,1);
    }


    @Test
    public void snakeEats(){
        Snake snake = new Snake();
        Food food = new Food();
        Point snakeLocation = snake.body.peekFirst();
        food.location = new Point (snakeLocation.x, snakeLocation.y + 1);
        snake.updatePosition();
        assertEquals(food.isEaten(snake), true);
    }
}