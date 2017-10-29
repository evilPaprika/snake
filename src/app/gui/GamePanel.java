package app.gui;

import app.game.Board;
import app.game.SimpleObject;
import app.util.GameConsts;
import app.util.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class GamePanel extends JPanel implements ActionListener {
    private Board board = new Board();
    private ArrayList<KeyListener>  snakeSteering = new ArrayList<>();
    private State state;
    private MenuPanel menu;

    public GamePanel() {
        state = State.MENU;
        board = new Board();
        menu = new MenuPanel();
        addMouseListener(new MouseInput(this));
        setBackground(Color.GRAY);
        setFocusable(true);
        setPreferredSize(new Dimension(GameConsts.PANEL_WIDTH, GameConsts.PANEL_HEIGHT));
        Timer timer = new Timer(GameConsts.PAINT_DELAY, this);
        timer.start();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (state != State.MENU) {
            for (SimpleObject e : board.getGameObjects()) {
                g.setColor(e.getColor());
                g.fillRect((int) (e.getLocation().x * GameConsts.CELL_SIZE + 1), (int) (e.getLocation().y * GameConsts.CELL_SIZE - 1), GameConsts.CELL_SIZE - 2, GameConsts.CELL_SIZE - 2);
            }
            g.setColor(Color.YELLOW);
            g.drawString("first player score: " + board.getSnake(0).getScore(), 20, 15);
            if (state == State.TWO_PLAYERS)
                g.drawString("second player score: " + board.getSnake(1).getScore(), GameConsts.PANEL_WIDTH - 200, 15);
        }
        else {
            menu.render(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        board.updateBoard();
        if (board.isGameOver()) {
            state = State.MENU;
        }
        repaint();
    }

    void setState(State state) {
        this.state = state;
    }

    void setBoard(Board board){this.board = board;}

    void updateKeyListener(ArrayList<KeyListener> newKeyListener){
        for (KeyListener kl: snakeSteering)
            removeKeyListener(kl);
        snakeSteering = newKeyListener;
        for (KeyListener kl: snakeSteering)
            addKeyListener(kl);
    }
}
