package app.gui;

import app.Main;
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
    private MenuPanel menuPanel;

    MouseInput(MenuPanel gp){
        menuPanel = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.print(e);
        int mx = e.getX();
        int my = e.getY();

        //One Player Button
        if (mx >= GameConsts.PANEL_WIDTH / 2 - 200 && mx <= GameConsts.PANEL_WIDTH / 2 + 160
                && my >= 150 && my <= 220){
            Main.ChangePanel(new GamePanel(State.ONE_PLAYER));
        }

        //Two players button
        if (mx >= GameConsts.PANEL_WIDTH / 2 - 200 && mx <= GameConsts.PANEL_WIDTH / 2 + 160
                && my >= 275 && my <= 345){
            Main.ChangePanel(new GamePanel(State.TWO_PLAYERS));
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
