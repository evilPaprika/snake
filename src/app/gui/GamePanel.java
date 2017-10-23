package app.gui;

import app.game.*;
import app.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener {
    private Board board;
    private KeyListener snakeSteering;

    public GamePanel() {
        board = new Board();
        snakeSteering = new SnakeKeyAdapter(board.getSnake());
        addKeyListener(snakeSteering);
        setBackground(Color.GRAY);
        setFocusable(true);
        setPreferredSize(new Dimension(GameConsts.WIDTH * 10, GameConsts.HEIGHT * 10));
        Timer timer = new Timer(GameConsts.PAINT_DELAY, this);
        timer.start();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (SimpleObject e: board.getGameObjects()) {
            g.setColor( e.getColor());
            g.fillRect(e.getLocation().x*10+1, e.getLocation().y*10-1, 8, 8);
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
            removeKeyListener(snakeSteering);
            snakeSteering = new SnakeKeyAdapter(board.getSnake());
            addKeyListener(snakeSteering);
        }
        repaint();
    }
}
