package app.game;

import java.awt.*;

public class SnakeSegment implements GameObject {
    private Point location;

    SnakeSegment(Point location){ this.location = location; }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void actionWhenColided(GameObject g) {

    }
}
