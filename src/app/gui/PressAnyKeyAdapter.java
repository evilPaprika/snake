package app.gui;

import app.game.Board;
import app.util.State;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PressAnyKeyAdapter extends KeyAdapter {
    private GamePanel panel;

    PressAnyKeyAdapter(GamePanel gamePanel){panel = gamePanel;}

    @Override
    public void keyPressed(KeyEvent e){
        panel.setState(State.MENU);
        panel.restartTimer();
    }
}
