import java.io.IOException;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // еда не запилена, управление wasd, e - увеличть змею

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("ZMEYAAA");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new Game());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}

