package app.game;

import java.awt.*;

public class SnakeSegment implements GameObject {
    Point location;
    private Snake parent;

    SnakeSegment(Point location, Snake parent){
        this.location = location;
        this.parent = parent;
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void actionWhenCollidedWith(GameObject g) {
        parent.actionWhenCollided(g);
    }
}
