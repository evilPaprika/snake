package app.game;

import app.util.Direction;
import app.util.Point;

public class DirectionMarker {
    private Direction direction;
    private Point location;

    public DirectionMarker(Direction direction, Point location) {
        this.direction = direction;
        this.location = location;
    }

    public Direction getDirection() { return direction; }
    public Point getLocation() { return location; }

    public String toString() {
        return location.toString() + " " + direction.toString();
    }
}
