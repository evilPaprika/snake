package app.game;

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

    }
}
