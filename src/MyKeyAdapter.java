import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter {
    Snake snake;

    MyKeyAdapter(Snake sanke){
        this.snake = sanke;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'w': snake.setDirection(Direction.UP);
                break;
            case 's': snake.setDirection(Direction.DOWN);
                break;
            case 'a': snake.setDirection(Direction.LEFT);
                break;
            case 'd': snake.setDirection(Direction.RIGHT);
                break;
            case 'e': snake.grow();
                break;
            default: break;
        }
    }
}
