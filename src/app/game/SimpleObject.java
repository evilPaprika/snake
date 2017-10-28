package app.game;

import app.util.Point;
import java.awt.Color;

public interface SimpleObject extends GameObject {
    Color getColor();
    Point getLocation();
}