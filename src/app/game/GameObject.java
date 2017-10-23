package app.game;

import java.awt.*;
import java.util.List;

public interface GameObject {
    boolean isDead();
    void collideWith(GameObject g);
}
