package app.game;

import app.util.GameConsts;

import java.awt.*;
import java.util.Random;

public class Board {
    public Snake snake;
    Apple apple;
    public boolean gameIsOver = false;

    private Random random = new Random();
    public GameObject[][] map = new GameObject[GameConsts.HEIGHT][GameConsts.WIDTH];

    public Board() {
        snake = new Snake();
        apple = createNewFood();
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            map[0][i] = new Wall(0, i);
        updateBoard();
    }

    public void updateBoard(){
        snake.updatePosition();
        Point snakeHead = snake.body.peekFirst().getLocation();

        if (snakeHead.equals(apple.getLocation())) {
            apple = createNewFood();
            snake.grow();
            snake.score += 10;
        }

        if (map[snakeHead.x][snakeHead.y] instanceof Wall || map[snakeHead.x][snakeHead.y] instanceof SnakeSegment)
            gameIsOver = true;

        for (int i = 0; i<GameConsts.HEIGHT; i++ )
            for (int j = 0; j<GameConsts.WIDTH; j++) {
                if (apple.getLocation().equals(new Point(j, i)))
                    map [j][i] = apple;
                else if (!(map[j][i] instanceof Wall))
                    map[j][i] = null;
            }
        for (SnakeSegment segment: snake.body)
            map[segment.getLocation().x][segment.getLocation().y] = segment;
    }

    public Apple createNewFood(){
        int x = random.nextInt(GameConsts.WIDTH);
        int y = random.nextInt(GameConsts.HEIGHT);
        return new Apple(x, y);
    }
}
