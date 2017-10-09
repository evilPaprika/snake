import java.awt.Point;
import  java.util.Random;

public class Food {
    public Point location;
    final Random random = new Random();


    Food(){
        int x = random.nextInt(Consts.width);
        int y = random.nextInt(Consts.height);
        this.location = new Point(x, y);
    }

    public boolean IsEaten(Snake snake) {
        return location.x == snake.body.peekFirst().x && location.y == snake.body.peekFirst().y;
    }
}
