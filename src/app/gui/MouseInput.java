package app.gui;

import app.Main;
import app.util.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseInput extends MouseAdapter {
    private JLabel label;

    MouseInput(JLabel label){
        this.label = label;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String command = label.getText();
        switch (command) {
            case "One player":
                Main.ChangePanel(new GamePanel(State.ONE_PLAYER));
                break;
            case "Two players":
                Main.ChangePanel(new GamePanel(State.TWO_PLAYERS));
                break;
            case "Exit":
                System.exit(1);
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { label.setForeground(Color.decode("#CFFF93")); }

    @Override
    public void mouseExited(MouseEvent e) {
        label.setForeground(Color.WHITE);
    }
}
