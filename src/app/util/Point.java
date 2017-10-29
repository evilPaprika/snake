package app.util;

import static app.util.GameConsts.EPSILON;

public class Point {
    public double x;
    public double y;

    public Point() {
        this(0, 0);
    }
    public Point(Point p) { this(p.x, p.y); }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }


    public Point getLocation() {
        return new Point(x, y);
    }

    public void setLocation(Point p) { setLocation(p.x, p.y); }
    public void setLocation(int x, int y) {
        move(x, y);
    }
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public Point add(Point point){
        return (new Point(this.getX() + point.getX(), this.getY() + point.getY()));
    }

    public Point mul(double scalar){
        return (new Point(this.x * scalar, this.y * scalar));
    }

    public double distance(Point point){
        return (Math.pow(this.x - point.getX(), 2) + Math.pow(this.y - point.getY(), 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;
        //return (this.x-point.x)*(this.x-point.x) + (this.y-point.y)*(this.y-point.y) < 0.4;
        return Math.abs(this.x - point.x) <= EPSILON && Math.abs(this.y - point.y) <= EPSILON;

        //if (Float.compare(point.x, x) != 0) return false;
        //return Float.compare(point.y, y) == 0;

    }


    public Point ceil(){
        return new Point( Math.signum(this.x) * Math.ceil(Math.abs(this.x) - EPSILON),Math.signum(this.y) * Math.ceil(Math.abs(this.y) - EPSILON));
    }

    public Point floor(){
        return new Point( Math.signum(this.x) * Math.floor(Math.abs(this.x) + EPSILON),Math.signum(this.y) * Math.floor(Math.abs(this.y) + EPSILON));
    }


    public String toString() {
        return "Point [x=" + x + ",y=" + y + "] ";
    }
}
