import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener {
    // TODO:  разделить отрисовку и логику игры
    Snake snake;
    Food food;
    private Timer timer;

    Game() {
        setBackground(Color.GRAY);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyChar()){
                    case 'w': snake.direction = new Point(0, -1);
                        break;
                    case 's': snake.direction = new Point(0, 1);
                        break;
                    case 'a': snake.direction = new Point(-1, 0);
                        break;
                    case 'd': snake.direction = new Point(1, 0);
                        break;
                    case 'e': snake.grow();
                        break;
                    default: break;
                }
                repaint();
            }
        });
        setPreferredSize(new Dimension(GameConsts.WIDTH * 10, GameConsts.HEIGHT * 10));
        snake = new Snake();
        food = new Food();
        timer = new Timer(65, this);
        timer.start();
    }


    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        snake.updatePosition();
        if (food.isEaten(snake)) {
            food = new Food();
            snake.grow();
        }
        for (int i = 0; i < GameConsts.HEIGHT; i++) {
            for (int j = 0; j < GameConsts.WIDTH; j++) {
                if (snake.body.contains(new Point(j,i))) {
                    g.setColor(Color.GREEN);
                    g.fillRect(j*10+1, i*10-1, 10-2, 10-2);
                }
                else if (food.location.x == j && food.location.y == i){
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
