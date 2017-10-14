package app.game;

import java.awt.*;

public interface GameObject {
    Color getColor();
    Point getLocation();
    void actionWhenColided(GameObject g);
}
