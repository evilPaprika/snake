package app;

import app.gui.Game1PlayerPanel;

import javax.swing.*;
import java.io.IOException;


class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ZMEYAAA");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Game1PlayerPanel());
            frame.pack();
            frame.setVisible(true);
        });
    }
}

