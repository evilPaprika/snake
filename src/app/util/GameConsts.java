package app.util;

import app.game.Board;
import app.game.SimpleObject;
import app.game.Snake;
import app.game.Wall;

import java.awt.*;
import java.util.ArrayList;

public class GameConsts {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 40;
    public static final int CELL_SIZE = 16;
    public static final int PANEL_WIDTH = WIDTH * CELL_SIZE;
    public static final int PANEL_HEIGHT = HEIGHT * CELL_SIZE;
    public static final int PAINT_DELAY = 70;
    public static final float EPSILON = 0.0001f;
}
