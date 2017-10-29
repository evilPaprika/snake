package app.gui;

import app.game.Board;
import app.util.GameConsts;
import app.util.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
            Board board = GameConsts.levelWithOnePlayer();
            gamePanel.updateKeyListener(new SnakeKeyAdapter(board.getSnakes()));
            gamePanel.setBoard(board);
            gamePanel.setState(State.ONE_PLAYER);
        }

        //Two players button
        if (mx >= GameConsts.PANEL_WIDTH / 2 - 200 && mx <= GameConsts.PANEL_WIDTH / 2 + 160
                && my >= 275 && my <= 345){
            Board board = GameConsts.levelWithTwoPlayers();
            gamePanel.updateKeyListener(new SnakeKeyAdapter(board.getSnakes()));
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
