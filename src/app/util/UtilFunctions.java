package app.util;


import java.awt.*;

public class UtilFunctions {
    public static int mod(int a, int b) { return (a%b + b)%b; }

    public static Point makePositionInBoundaries(Point position){
        if (position.x < 0 || position.x >= GameConsts.WIDTH)
            position.x = mod(position.x, GameConsts.WIDTH);
        if (position.y < 0 || position.y >= GameConsts.HEIGHT)
            position.y = mod(position.y, GameConsts.HEIGHT);
        return position;
    }
}
