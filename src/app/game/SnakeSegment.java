package app.game;

import app.util.Direction;
import app.util.Point;
import java.awt.*;

public class SnakeSegment implements SimpleObject {
    private Point location;
    private Snake parent;
    private Direction direction;

    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }


    SnakeSegment(Point location,Direction direction, Snake parent){
        this.direction = direction;
        this.location = location;
        this.parent = parent;
    }

    public void setLocation( Point new_location ) { location = new_location; }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public Point getLocation() { return location; }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void collideWith(GameObject g) {
        parent.collideWith(g);
    }
}
