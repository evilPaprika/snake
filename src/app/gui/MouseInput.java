package app.gui;

import app.util.GameConsts;
import app.util.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    private Game1PlayerPanel gamePanel;

    public MouseInput(Game1PlayerPanel gp){
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
            gamePanel.state = State.ONE_PLAYER;
        }

        //Two players button

        if (mx >= GameConsts.PANEL_WIDTH / 2 - 200 && mx <= GameConsts.PANEL_WIDTH / 2 + 160
                && my >= 275 && my <= 345){
            gamePanel.state = State.TWO_PLAYERS;
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
