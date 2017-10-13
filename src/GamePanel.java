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
                game.keyPressed = e.getKeyChar();
            }
        }
        );
        setPreferredSize(new Dimension(GameConsts.WIDTH * 10, GameConsts.HEIGHT * 10));
        timer = new Timer(GameConsts.PAINT_DELAY, this);
        timer.start();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        game.updateBoard();
        for (int i = 0; i < GameConsts.HEIGHT; i++) {
            for (int j = 0; j < GameConsts.WIDTH; j++) {
                if (game.map[j][i] == null){
                    g.setColor(Color.GRAY);
                    g.fillRect(j*10+1, i*10-1, 8, 8);
                }
                else{
                    g.setColor(game.map[j][i].getColor());
                    g.fillRect(j*10+1, i*10-1, 8, 8);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
