package app.gui;

import app.Main;
import app.util.State;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PressAnyKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e){
        Main.ChangePanel(new MenuPanel());
    }
}
