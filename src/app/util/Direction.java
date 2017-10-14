package app.util;

import java.awt.*;

public enum Direction {
    UP(new Point(0, -1)){
        public Direction opposite() { return DOWN; }
    },
    DOWN(new Point(0, 1)){
        public Direction opposite() { return UP; }
    },
    LEFT(new Point(-1, 0)){
        public Direction opposite() { return RIGHT; }
    },
    RIGHT(new Point(1, 0)){
        public Direction opposite() { return LEFT; }
    };

    //private final Point toPoint;
    public final int x;
    public final int y;

    public abstract Direction opposite();

    Direction(Point direction) {
        //this.toPoint = direction;
        x = direction.x;
        y = direction.y;
    }
}
