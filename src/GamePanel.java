import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    Board game;

    GamePanel() {
        game = new Board();
        setBackground(Color.GRAY);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyChar()){
                    case 'w': game.snake.direction = Direction.UP;
                        break;
                    case 's': game.snake.direction = Direction.DOWN;
                        break;
                    case 'a': game.snake.direction = Direction.LEFT;
                        break;
                    case 'd': game.snake.direction = Direction.RIGHT;
                        break;
                    case 'e': game.snake.grow();
                        break;
                    default: break;
                }
                repaint();
            }
        });
        setPreferredSize(new Dimension(GameConsts.WIDTH * 10, GameConsts.HEIGHT * 10));
        timer = new Timer(GameConsts.PAINT_DELAY, this);
        timer.start();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        game.snake.updatePosition();
        if (game.food.isEaten(game.snake)) {
            game.food = new Food();
            game.snake.grow();
        }
        for (int i = 0; i < GameConsts.HEIGHT; i++) {
            for (int j = 0; j < GameConsts.WIDTH; j++) {
                if (game.snake.body.contains(new Point(j,i))) {
                    g.setColor(Color.GREEN);
                    g.fillRect(j*10+1, i*10-1, 10-2, 10-2);
                }
                else if (game.food.location.x == j && game.food.location.y == i){
                    g.setColor(Color.RED);
                    g.fillRect(j*10+1, i*10-1, 10-2, 10-2);
                }
                else {
                    g.setColor(Color.GRAY);
                    g.fillRect(j*10+1, i*10-1, 10-2, 10-2);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
