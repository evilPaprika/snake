import java.awt.*;

public class Food implements GameObject {
    public Point location;

    @Override
    public Color getColor() {
        return Color.RED;
    }
}
