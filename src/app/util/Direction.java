package app.util;

import java.awt.*;

public enum Direction {
    UP(new Point(0, -1)),
    DOWN(new Point(0, 1)),
    LEFT(new Point(-1, 0)),
    RIGHT(new Point(1, 0));

    //private final Point toPoint;
    public final int x;
    public final int y;

    public boolean isOpposite(Direction other) {
        return this.x + other.x == 0 && this.y + other.y == 0;
    }

    Direction(Point direction) {
        //this.toPoint = direction;
        x = direction.x;
        y = direction.y;
    }
}
