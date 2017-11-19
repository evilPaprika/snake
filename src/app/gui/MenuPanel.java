package app.gui;

import app.util.GameConsts;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        setBackground(Color.GRAY);
        setFocusable(true);
        setPreferredSize(new Dimension(GameConsts.PANEL_WIDTH, GameConsts.PANEL_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initializeMenu();
    }

    private void initializeMenu(){
        Font fnt1 = new Font("arial", Font.BOLD, 70);
        JLabel snake = createLabel("Sssssnake", fnt1, Color.GREEN, false);

        Font fnt2 = new Font("arial", Font.BOLD, 50);
        JLabel one_player = createLabel("One player", fnt2, Color.WHITE, true);
        JLabel two_players = createLabel("Two players", fnt2, Color.WHITE, true);
        JLabel exit = createLabel("Exit", fnt2, Color.WHITE, true);

        add(snake);
        add(Box.createRigidArea(new Dimension(0,45)));
        add(one_player);
        add(Box.createRigidArea(new Dimension(0,30)));
        add(two_players);
        add(Box.createRigidArea(new Dimension(0,30)));
        add(exit);
    }

    private JLabel createLabel(String text, Font font, Color color, boolean active){
        JLabel label = new JLabel(text);
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        label.setFont(font);
        label.setForeground(color);
        if (active)
            label.addMouseListener(new MouseInput(label));
        return label;
    }
}
