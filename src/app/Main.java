package app;

import app.gui.GamePanel;
import app.gui.MenuPanel;
import app.util.State;

import javax.swing.*;
import java.io.IOException;


public class Main {
    static JFrame frame;
    static JPanel panel;

    public static void main(String[] args) throws IOException, InterruptedException {
        javax.swing.SwingUtilities.invokeLater(() -> {
            frame = new JFrame("ZMEYAAA");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel = new MenuPanel();
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        });
    }

    public static void ChangePanel(JPanel newPanel){
        panel.setVisible(false);
        frame.remove(panel);
        frame.add(newPanel);
        frame.pack();
        panel = newPanel;
    }
}

