import java.awt.*;
import java.util.Random;

public class Board {
    Random random = new Random();
    Snake snake;
    Food food;
    boolean gameIsOver = false;
    GameObject[][] map = new GameObject[GameConsts.HEIGHT][GameConsts.WIDTH];

    Board() {
        snake = new Snake();
        food = new Food();
        createNewFood();
        map[food.location.x][food.location.y] = food;
        for (SnakeSegment segment: snake.body)
            map[segment.location.x][segment.location.y] = segment;
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            map[0][i] = new Wall();
    }

    public void updateBoard(){
        snake.updatePosition();
        Point snakeHead = snake.body.peekFirst().location;
        if (snakeHead.equals(food.location)) {
            createNewFood();
            snake.grow();
            snake.scores += 10;
        }
        if (map[snakeHead.x][snakeHead.y] instanceof Wall || map[snakeHead.x][snakeHead.y] instanceof SnakeSegment)
            gameIsOver = true;
        for (int i = 0; i<GameConsts.HEIGHT; i++ )
            for (int j = 0; j<GameConsts.WIDTH; j++) {
                if (food.location.equals(new Point(j, i)))
                    map [j][i] = food;
                else if (!(map[j][i] instanceof Wall))
                    map[j][i] = null;
            }
        for (SnakeSegment segment: snake.body)
            map[segment.location.x][segment.location.y] = segment;
    }

    public void createNewFood(){
        int x = random.nextInt(GameConsts.WIDTH);
        int y = random.nextInt(GameConsts.HEIGHT);
        food.location = new Point(x, y);
    }
}
