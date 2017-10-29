package app.util;

public class Point {
    public float x;
    public float y;

    public Point() {
        this(0, 0);
    }
    public Point(Point p) { this(p.x, p.y); }
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }


    public Point getLocation() {
        return new Point(x, y);
    }
    public void setLocation(Point p) {
        setLocation(p.x, p.y);
    }
    public void setLocation(int x, int y) {
        move(x, y);
    }
    public void setLocation(double x, double y) {
        this.x = (int) Math.floor(x+0.5);
        this.y = (int) Math.floor(y+0.5);
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;
        //return (int)this.x == (int)point.x && (int)this.y == (int)point.y;

        if (Float.compare(point.x, x) != 0) return false;
        return Float.compare(point.y, y) == 0;

    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }


    public String toString() {
        return getClass().getName() + " [x=" + x + ",y=" + y + "] ";
    }
}
