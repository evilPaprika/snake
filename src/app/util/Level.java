package app.util;

import app.game.Board;
import app.game.SimpleObject;
import app.game.Snake;
import app.game.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Level {
    private static Random rnd = new Random();

    public static Board levelWithOnePlayer(){
        ArrayList<Snake> snakes = new ArrayList<>();
        ArrayList<SimpleObject> objects = generateMap();
        snakes.add(new Snake());
        return new Board(objects, snakes);
    }

    public static Board levelWithTwoPlayers(){
        ArrayList<Snake> snakes = new ArrayList<>();
        ArrayList<SimpleObject> objects = generateMap();
        snakes.add(new Snake());
        snakes.add(new Snake(Direction.UP, new Point(GameConsts.WIDTH - 5, GameConsts.HEIGHT - 5), 3, Color.BLUE));
        return new Board(objects, snakes);
    }

    private static ArrayList<SimpleObject> map1(){
        ArrayList<SimpleObject> objects = new ArrayList<>();
        for (int i = 0; i < GameConsts.HEIGHT; i++) {
            objects.add(new Wall(0, i));
            objects.add(new Wall(GameConsts.WIDTH-1, i));
            if (i >= 11 && i < 29)
            {
                objects.add(new Wall(11, i));
                objects.add(new Wall(GameConsts.WIDTH - 12, i));
            }
        }
        for (int j = 6; j < GameConsts.WIDTH - 6; j++){
            objects.add(new Wall(j, 6));
            objects.add(new Wall(j, GameConsts.HEIGHT - 7));
        }
        return objects;
    }

    private static ArrayList<SimpleObject> map2(){
        ArrayList<SimpleObject> objects = new ArrayList<>();
        for (int i = 0; i < GameConsts.WIDTH; i++)
            objects.add(new Wall(i, GameConsts.HEIGHT / 2 - 1));
        for (int i = 0; i < GameConsts.HEIGHT; i++)
            objects.add(new Wall(GameConsts.WIDTH / 2 - 1, i));
        return objects;
    }

    private static ArrayList<SimpleObject> map3() {
        ArrayList<SimpleObject> objects = new ArrayList<>();
        for (int i = 0; i <= GameConsts.WIDTH; i++) {
            if (i <= GameConsts.WIDTH / 2 - 4 || i > GameConsts.WIDTH / 2 + 3){
                objects.add(new Wall(i, 0));
                objects.add(new Wall(i, GameConsts.HEIGHT - 1));
            }
            if (i>= 15 && i < GameConsts.WIDTH - 15){
                objects.add(new Wall(i, 15));
                if ((i <= GameConsts.WIDTH / 2 - 3 || i > GameConsts.WIDTH / 2 + 2))
                    objects.add(new Wall(i, GameConsts.HEIGHT - 16));
            }
        }
        for (int i = 0; i <= GameConsts.HEIGHT; i++) {
                objects.add(new Wall(0, i));
                objects.add(new Wall(GameConsts.WIDTH - 1, i));
            if (i>= 15 && i < GameConsts.HEIGHT - 15){
                objects.add(new Wall(15, i));
                objects.add(new Wall(GameConsts.WIDTH-16, i));
            }
        }
        return objects;
    }

    private static ArrayList<SimpleObject> generateMap(){
        ArrayList<ArrayList<SimpleObject>> maps = new ArrayList<>();
        maps.add(map1());
        maps.add(map2());
        maps.add(map3());
        return maps.get(rnd.nextInt(maps.size()));
    }
}
