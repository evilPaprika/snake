package app.gui;

import app.game.Board;
import app.game.SimpleObject;
import app.util.GameConsts;
import app.util.Level;
import app.util.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class GamePanel extends JPanel implements ActionListener {
    private Board board = new Board();
    private State state;
    private Timer timer;

    public GamePanel(State state) {
        this.state = state;
        if (state == State.ONE_PLAYER){
            newOnePlayerGame();
        }
        if (state == State.TWO_PLAYERS){
            newTwoPlayersGame();
        }
        setBackground(Color.GRAY);
        setFocusable(true);
        setPreferredSize(new Dimension(GameConsts.PANEL_WIDTH, GameConsts.PANEL_HEIGHT));
        timer = new Timer(GameConsts.PAINT_DELAY, this);
        timer.start();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (SimpleObject e : board.getGameObjects()) {
            g.setColor(e.getColor());
            g.fillRect(e.getLocation().x * GameConsts.CELL_SIZE + 1, e.getLocation().y * GameConsts.CELL_SIZE - 1, GameConsts.CELL_SIZE - 2, GameConsts.CELL_SIZE - 2);
        }
        g.setFont(new Font("arial", Font.BOLD, 30));
        g.setColor(board.getSnake(0).getColor());
        g.drawString(""+board.getSnake(0).getScore(), 300, 30);
        if (state == State.TWO_PLAYERS) {
            g.setColor(board.getSnake(1).getColor());
            g.drawString("" + board.getSnake(1).getScore(), GameConsts.PANEL_WIDTH - 300, 30);
        }
        if(board.gameIsOver()){
            g.setFont(new Font("arial", Font.BOLD, 70));
            g.setColor(Color.RED);
            g.drawString("Game over", 200, 300);
            g.setFont(new Font("arial", Font.BOLD, 30));
            g.setColor(Color.white);
            g.drawString("Press any key", 270, 350);
            timer.stop();
            addKeyListener(new PressAnyKeyAdapter());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        board.updateBoard();
        repaint();
    }

    private void newOnePlayerGame(){
        board = Level.levelWithOnePlayer();
        addKeyListener(new SnakeKeyAdapter(board.getSnake(0), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT));
    }

    private void newTwoPlayersGame(){
        board = Level.levelWithTwoPlayers();
        addKeyListener(new SnakeKeyAdapter(board.getSnake(1), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT));
        addKeyListener(new SnakeKeyAdapter(board.getSnake(0), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D));
    }

}
