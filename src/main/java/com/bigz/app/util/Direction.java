package com.bigz.app.util;

import java.awt.*;

public enum Direction {
    UP(new Point(0, -1)),
    DOWN(new Point(0, 1)),
    LEFT(new Point(-1, 0)),
    RIGHT(new Point(1, 0));

    //private final Point point;
    private final int x;
    private final int y;

    public int getX() { return x; }
    public int getY() { return y; }
    //public Point toPoint() { return point; }

    public boolean isOpposite(Direction other) {
        return this.x + other.x == 0 && this.y + other.y == 0;
    }

     Direction(Point direction) {
        //this.point = direction;
        x = direction.x;
        y = direction.y;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
