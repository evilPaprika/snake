package app.gui;

import app.util.GameConsts;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private Rectangle onePlayerButton = new Rectangle(GameConsts.PANEL_WIDTH / 2 - 200, 150, 360, 70);
    private Rectangle twoPlayersButton = new Rectangle(GameConsts.PANEL_WIDTH / 2 - 200, 275, 360, 70);
    private Rectangle exitButton = new Rectangle(GameConsts.PANEL_WIDTH / 2 - 200, 400, 360, 70);

    public MenuPanel() {
        addMouseListener(new MouseInput(this));
        setBackground(Color.GRAY);
        setFocusable(true);
        setPreferredSize(new Dimension(GameConsts.PANEL_WIDTH, GameConsts.PANEL_HEIGHT));
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Font fnt1 = new Font("arial", Font.BOLD, 70);
        g.setFont(fnt1);
        g.setColor(Color.GREEN);
        g.drawString("Sssssnake", GameConsts.PANEL_WIDTH / 2 - 200, 100);

        g.setColor(Color.DARK_GRAY);
        g2d.fillRect(onePlayerButton.x, onePlayerButton.y, onePlayerButton.width,onePlayerButton.height);
        g2d.fillRect(twoPlayersButton.x, twoPlayersButton.y, twoPlayersButton.width,twoPlayersButton.height);
        g2d.fillRect(exitButton.x, exitButton.y, exitButton.width,exitButton.height);

        Font fnt2 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt2);
        g.setColor(Color.white);
        g.drawString("One player", onePlayerButton.x + 50, onePlayerButton.y + 50);
        g2d.draw(onePlayerButton);
        g.drawString("Two players", twoPlayersButton.x + 40, twoPlayersButton.y + 50);
        g2d.draw(twoPlayersButton);
        g.drawString("Exit", exitButton.x + 125, exitButton.y + 50);
        g2d.draw(exitButton);
    }
}
