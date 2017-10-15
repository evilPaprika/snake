package app.game;

import java.awt.*;

public class Wall implements GameObject {
    Point location;

    Wall(int x, int y){
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
    public void actionWhenCollidedWith(GameObject g) {

    }
}
