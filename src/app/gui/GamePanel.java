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
    private ArrayList<KeyListener>  snakeSteering = new ArrayList<>();
    private State state;
    private MenuPanel menu;
    private Timer timer;

    public GamePanel() {
        state = State.MENU;
        board = new Board();
        menu = new MenuPanel();
        addMouseListener(new MouseInput(this));
        setBackground(Color.GRAY);
        setFocusable(true);
        setPreferredSize(new Dimension(GameConsts.PANEL_WIDTH, GameConsts.PANEL_HEIGHT));
        timer = new Timer(GameConsts.PAINT_DELAY, this);
        timer.start();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (state != State.MENU) {
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
                KeyListener anyKeyListener = new PressAnyKeyAdapter(this);
                snakeSteering.add(anyKeyListener);
                addKeyListener(anyKeyListener);
            }
        }
        else {
            menu.render(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        board.updateBoard();
        repaint();
    }

    void newOnePlayerGame(){
        board = Level.levelWithOnePlayer();
        ArrayList<KeyListener> listeners = new ArrayList<>();
        listeners.add(new SnakeKeyAdapter(board.getSnake(0), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT));
        updateKeyListener(listeners);
        setBoard(board);
        state = State.ONE_PLAYER;
    }

    void newTwoPlayersGame(){
        board = Level.levelWithTwoPlayers();
        ArrayList<KeyListener> listeners = new ArrayList<>();
        listeners.add(new SnakeKeyAdapter(board.getSnake(1), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT));
        listeners.add(new SnakeKeyAdapter(board.getSnake(0), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D));
        updateKeyListener(listeners);
        setBoard(board);
        state = State.TWO_PLAYERS;
    }

    void setState(State state) {
        this.state = state;
    }

    void restartTimer(){timer.restart();}

    void setBoard(Board board){this.board = board;}

    void updateKeyListener(ArrayList<KeyListener> newKeyListener){
        for (KeyListener kl: snakeSteering)
            removeKeyListener(kl);
        snakeSteering = newKeyListener;
        for (KeyListener kl: snakeSteering)
            addKeyListener(kl);
    }
}
