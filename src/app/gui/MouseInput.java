package app.gui;

import app.Main;
import app.util.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseInput extends MouseAdapter {
    private JLabel label;
    private Runnable action;

    MouseInput(JLabel label, Runnable action){
        this.action = action;
        this.label = label;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        action.run();
    }

    @Override
    public void mouseEntered(MouseEvent e) { label.setForeground(Color.decode("#CFFF93")); }

    @Override
    public void mouseExited(MouseEvent e) {
        label.setForeground(Color.WHITE);
    }
}
