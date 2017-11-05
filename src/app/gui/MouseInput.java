package app.gui;

import app.game.Board;
import app.util.GameConsts;
import app.util.Level;
import app.util.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MouseInput implements MouseListener {
    private GamePanel gamePanel;

    MouseInput(GamePanel gp){
        gamePanel = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //One Player Button
        if (mx >= GameConsts.PANEL_WIDTH / 2 - 200 && mx <= GameConsts.PANEL_WIDTH / 2 + 160
                && my >= 150 && my <= 220){
            Board board = Level.levelWithOnePlayer();
            ArrayList<KeyListener> listeners = new ArrayList<>();
            listeners.add(new SnakeKeyAdapter(board.getSnake(0), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT));
            gamePanel.updateKeyListener(listeners);
            gamePanel.setBoard(board);
            gamePanel.setState(State.ONE_PLAYER);
        }

        //Two players button
        if (mx >= GameConsts.PANEL_WIDTH / 2 - 200 && mx <= GameConsts.PANEL_WIDTH / 2 + 160
                && my >= 275 && my <= 345){
            Board board = Level.levelWithTwoPlayers();
            ArrayList<KeyListener> listeners = new ArrayList<>();
            listeners.add(new SnakeKeyAdapter(board.getSnake(1), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT));
            listeners.add(new SnakeKeyAdapter(board.getSnake(0), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D));
            gamePanel.updateKeyListener(listeners);
            gamePanel.setBoard(board);
            gamePanel.setState(State.TWO_PLAYERS);
        }

        //Exit button
        if (mx >= GameConsts.PANEL_WIDTH / 2 - 200 && mx <= GameConsts.PANEL_WIDTH / 2 + 160
                && my >= 400 && my <= 470){
            System.exit(1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
