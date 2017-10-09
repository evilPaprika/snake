import java.io.IOException;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ZMEYAAA");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Game());
            frame.pack();
            frame.setVisible(true);
        });
    }
}

