package app.gui;

import app.game.Board;
import app.game.SimpleObject;
import app.util.GameConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;


public class Game1PlayerPanel extends JPanel implements ActionListener {
    private Board board;
    private KeyListener snakeSteering;

    public Game1PlayerPanel() {
        board = new Board();
        snakeSteering = new SnakeKeyAdapter(board.getSnake());
        addKeyListener(snakeSteering);
        setBackground(Color.GRAY);
        setFocusable(true);
        setPreferredSize(new Dimension(GameConsts.WIDTH * GameConsts.CELL_SIZE, GameConsts.HEIGHT * GameConsts.CELL_SIZE));
        Timer timer = new Timer(GameConsts.PAINT_DELAY, this);
        timer.start();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (SimpleObject e: board.getGameObjects()) {
            g.setColor( e.getColor());
            g.fillRect((int)e.getLocation().x*GameConsts.CELL_SIZE+1, (int)e.getLocation().y*GameConsts.CELL_SIZE-1, GameConsts.CELL_SIZE - 2, GameConsts.CELL_SIZE - 2);
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
