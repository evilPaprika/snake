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
                System.out.print("test");
                switch (e.getKeyChar()){
                    case 'w': snake.speed = new Point(0, -1);
                        break;
                    case 's': snake.speed = new Point(0, 1);
                        break;
                    case 'a': snake.speed = new Point(-1, 0);
                        break;
                    case 'd': snake.speed = new Point(1, 0);
                        break;
                    case 'e': snake.Grow();
                        break;
                    default: break;
                }
                repaint();
            }
        });
        setPreferredSize(new Dimension(Consts.width * 10, Consts.height * 10));
        snake = new Snake();
        food = new Food();
        timer = new Timer(60, this);
        timer.start();
    }


    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        snake.UpdatePosition();
        if (food.IsEaten(snake)) {
            food = new Food();
            snake.Grow();
        }
        for (int i = 0; i < Consts.height; i++) {
            for (int j = 0; j < Consts.width; j++) {
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
