package app.game;

import java.awt.*;

public class Apple implements Food {
    private Point location;
    private boolean isDead;

    public Apple(int x, int y){
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
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void collideWith(GameObject g) {
        if (g instanceof SnakeSegment) {
            int length = ((SnakeSegment) g).getParent().getLength();
            ((SnakeSegment) g).getParent().addScore(getNutritionalValue() * (length * length)/90);
            ((SnakeSegment) g).getParent().grow(1);
        }
        isDead = true;
    }

    @Override
    public int getNutritionalValue() {
        return 10;
    }
}
