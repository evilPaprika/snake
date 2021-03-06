package com.bigz.app.game;

import javafx.scene.paint.*;
import javafx.scene.paint.Color;

import java.awt.*;

public class Wall implements SimpleObject {
    private Point location;

    public Wall(int x, int y){
        location = new Point(x, y);
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void collideWith(GameObject g) {
        if (g instanceof SnakeSegment)
            ((SnakeSegment) g).getParent().kill();
    }
}
