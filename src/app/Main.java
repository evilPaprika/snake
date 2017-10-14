package app;

import app.gui.GamePanel;

import javax.swing.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ZMEYAAA");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GamePanel());
            frame.pack();
            frame.setVisible(true);
        });
    }
}
