package com.bigz.app.game;

import com.bigz.app.util.Direction;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.LinkedList;


public class SnakeSegment implements SnakeInteraction {
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

    public Snake getParent() {
        return parent;
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

    @Override
    public void collideWithSnake(Snake snake) {
        LinkedList<SnakeSegment> body = parent.getParts();
        if (!this.isHead())
            body.removeIf(x -> body.indexOf(x) >= body.indexOf(this));
        else if (snake.getParts().peekFirst().getLocation().equals(this.location) || parent.equals(snake))
            parent.kill();
    }
}
