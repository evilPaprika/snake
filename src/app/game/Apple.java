package app.game;

import java.awt.*;

public class Apple implements GameObject {
    private Point location;

    Apple(int x, int y){
        location = new Point(x, y);
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

    }
}
