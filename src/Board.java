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
        for (Point segment: snake.body)
            map[segment.x][segment.y] = new SnakeSegment();
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            map[0][i] = new Wall();
    }

    public void updateBoard(){
        snake.updatePosition();
        Point snakeHead = snake.body.peekFirst();
        if (snakeHead.equals(food.location)) {
            createNewFood();
            snake.grow();
            snake.scores += 10;
        }
        if (map[snakeHead.x][snakeHead.y] instanceof Wall || map[snakeHead.x][snakeHead.y] instanceof SnakeSegment)
            isOver = true;
        for (int i = 0; i<GameConsts.HEIGHT; i++ )
            for (int j = 0; j<GameConsts.WIDTH; j++) {
                if (snake.body.contains(new Point(j, i)))
                    map[j][i] = new SnakeSegment();
                else if (food.location.equals(new Point(j, i)))
                    map [j][i] = food;
                else if (!(map[j][i] instanceof Wall))
                    map[j][i] = null;
            }
    }

    public void createNewFood(){
        int x = random.nextInt(GameConsts.WIDTH);
        int y = random.nextInt(GameConsts.HEIGHT);
        food.location = new Point(x, y);
    }
}
