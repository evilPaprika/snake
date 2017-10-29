package app.util;

public enum Direction {
    UP(new Point(0, -1)),
    DOWN(new Point(0, 1)),
    LEFT(new Point(-1, 0)),
    RIGHT(new Point(1, 0));

    private final int x;
    private final int y;

    public int getX() { return x; }
    public int getY() { return y; }

    public boolean isOpposite(Direction other) {
        return this.x + other.x == 0 && this.y + other.y == 0;
    }
    public Point toPoint(){return new Point(this.x, this.y);}

    Direction(Point direction) {
        x = (int) direction.x;
        y = (int) direction.y;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
