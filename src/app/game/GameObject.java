package app.game;

import java.awt.*;

public interface GameObject {
    Color getColor();
    Point getLocation();
    boolean isDead();
    void actionWhenColidedWith(GameObject g);
}
