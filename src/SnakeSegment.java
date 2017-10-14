import java.awt.*;

public class SnakeSegment implements GameObject{
    public Point location;

    SnakeSegment(Point location){ this.location = location; }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }
}
