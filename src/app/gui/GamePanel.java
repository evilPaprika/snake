package app.gui;

import app.game.*;
import app.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private Board board;

    public GamePanel() {
        board = new Board();
        addKeyListener(new SnakeKeyAdapter(board.getSnake()));
        setBackground(Color.GRAY);
        setFocusable(true);
        setPreferredSize(new Dimension(GameConsts.WIDTH * 10, GameConsts.HEIGHT * 10));
        Timer timer = new Timer(GameConsts.PAINT_DELAY, this);
        timer.start();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < GameConsts.HEIGHT; i++) {
            for (int j = 0; j < GameConsts.WIDTH; j++) {
                for (GameObject e: board.getGameObjects()) {
                    if (e.getLocation().equals(new Point(j, i))){
                        g.setColor( e.getColor());
                        g.fillRect(j*10+1, i*10-1, 8, 8);
                    }
                }
            }
        }
        g.setColor(Color.YELLOW);
        g.drawString("score: " + board.getSnake().getScore(), 15, 15);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        board.updateBoard();
        if (board.isGameOver()) {
            board = new Board();
            addKeyListener(new SnakeKeyAdapter(board.getSnake()));
        }
        repaint();
    }
}
