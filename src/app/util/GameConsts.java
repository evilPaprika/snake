package app.util;

import app.game.Board;
import app.game.SimpleObject;
import app.game.Snake;
import app.game.Wall;

import java.util.ArrayList;

public class GameConsts {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static final int CELL_SIZE = 16;
    public static final int PANEL_WIDTH = WIDTH * CELL_SIZE;
    public static final int PANEL_HEIGHT = HEIGHT * CELL_SIZE;
    public static final int PAINT_DELAY = 100;

    public static Board levelWithOnePlayer(){
        ArrayList<Snake> snakes = new ArrayList<>();
        ArrayList<SimpleObject> objects = new ArrayList<>();
        snakes.add(new Snake());
        for (int i = 0; i < GameConsts.HEIGHT; i++) {
            objects.add(new Wall(0, i));
            objects.add(new Wall(GameConsts.WIDTH-1, i));
        }
        return new Board(objects, snakes);
    }

    public static Board levelWithTwoPlayers(){
        ArrayList<Snake> snakes = new ArrayList<>();
        ArrayList<SimpleObject> objects = new ArrayList<>();
        snakes.add(new Snake());
        snakes.add(new Snake(Direction.UP, new Point(45, 45), 3));
        for (int i = 0; i < GameConsts.HEIGHT; i++) {
            objects.add(new Wall(0, i));
            objects.add(new Wall(GameConsts.WIDTH-1, i));
        }
        return new Board(objects, snakes);
    }
}
