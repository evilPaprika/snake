//package app.gui;
//
//import app.Main;
//import app.util.GameConsts;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public class MenuSettings extends JPanel {
//
//    public MenuSettings(){
//
//        try {
//            Image img = ImageIO.read(new File("img/menu.jpg"));
//            ImageIcon bg = new ImageIcon(img);
//            JLabel bgimg = new JLabel(bg);
//            PicPanel mainPanel = new PicPanel("img/menu.jpg");
//            mainPanel.setBounds(0,0,500,500);
//            add(mainPanel);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        setBackground(Color.GRAY);
//        setFocusable(true);
//        setPreferredSize(new Dimension(GameConsts.PANEL_WIDTH, GameConsts.PANEL_HEIGHT));
//
//
//        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        initializeMenu();
//    }
//
//    private void initializeMenu(){
//        Font fnt1 = new Font("arial", Font.BOLD, 70);
//        JLabel snake = createLabel("SNAKE", () -> {},fnt1, Color.GREEN, false);
//
//        Font fnt2 = new Font("arial", Font.BOLD, 50);
//        JLabel one_player = createLabel("One player", Main::startOnePlayerGame, fnt2, Color.WHITE, true);
//        JLabel two_players = createLabel("Two players", Main::startTwoPlayersGame,  fnt2, Color.WHITE, true);
//        JLabel exit = createLabel("Exit", () -> System.exit(0), fnt2, Color.WHITE, true);
//        add(Box.createRigidArea(new Dimension(0,70)));
//        add(snake);
//        add(Box.createRigidArea(new Dimension(0,80)));
//        add(one_player);
//        add(Box.createRigidArea(new Dimension(0,30)));
//        add(two_players);
//        add(Box.createRigidArea(new Dimension(0,30)));
//        add(exit);
//    }
//
//    private JLabel createLabel(String text, Runnable action, Font font, Color color, boolean active){
//        JLabel label = new JLabel(text);
//        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
//        label.setFont(font);
//        label.setForeground(color);
//        if (active)
//            label.addMouseListener(new MouseInput(label, action));
//        return label;
//    }
//
//    class PicPanel extends JPanel{
//
//        private BufferedImage image;
//        private int w,h;
//        public PicPanel(String fname){
//
//            //reads the image
//            try {
//                image = ImageIO.read(new File("img/menu.jpg"));
//                w = image.getWidth();
//                h = image.getHeight();
//
//            } catch (IOException ioe) {
//                System.out.println("Could not read in the pic");
//                //System.exit(0);
//            }
//
//        }
//
//        public Dimension getPreferredSize() {
//            return new Dimension(w,h);
//        }
//        //this will draw the image
//        public void paintComponent(Graphics g){
//            super.paintComponent(g);
//            g.drawImage(image,0,0,this);
//        }
//    }
//
//}
