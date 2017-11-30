package app.gui;

import app.Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PressAnyKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e){
        Main.startMainMenu();
    }
}
