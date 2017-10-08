import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener {
    Snake snake;
    private Timer timer;
    Game() {
        setBackground(Color.GRAY);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
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
        setPreferredSize(new Dimension(500, 500));
        snake = new Snake();
        timer = new Timer(50, this);
        timer.start();

    }


    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        snake.UpdatePosition();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (snake.body.contains(new Point(j,i))) {
                    g.setColor(Color.GREEN);
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
