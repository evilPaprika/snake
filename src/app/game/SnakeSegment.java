package app.game;

import app.util.Direction;

import java.awt.*;


public class SnakeSegment implements SimpleObject {
    private Point location;
    private Snake parent;
    private Direction direction;

    SnakeSegment(Point location, Direction direction, Snake parent){
        this.direction = direction;
        this.location = location;
        this.parent = parent;
    }

    public boolean isHead(){
        return this.equals(this.parent.getParts().peekFirst());
    }

    @Override
    public Color getColor() { return parent.getColor(); }

    @Override
    public Point getLocation() { return location; }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void collideWith(GameObject g) {
        parent.collideWith(g); }
}
