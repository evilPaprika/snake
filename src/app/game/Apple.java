package app.game;

import java.awt.*;

public class Apple implements GameObject {
    private Point location;
    public boolean isDead;

    Apple(int x, int y){
        location = new Point(x, y);
        isDead = false;
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void actionWhenColided(GameObject g) {
        isDead = true;
    }
}
