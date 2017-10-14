package app.game_objects;

import app.util.Direction;
import app.util.GameConsts;

import java.awt.*;
import java.util.LinkedList;

public class Snake {
    public LinkedList<SnakeSegment> body = new LinkedList<>();;
    Direction direction;
    public int score;
    private int toGrow;

    public Snake(){
        this(Direction.DOWN);
    }

    public Snake(Direction initialDirection){
        this(initialDirection, new Point(5, 5), 3);
    }

    public Snake(Direction initialDirection, Point initialPosition, int tailLength){
        toGrow = tailLength;
        direction = initialDirection;
        body.addFirst(new SnakeSegment(initialPosition));
    }

    public void setDirection(Direction new_direction){
        if (new_direction.opposite() != direction)
            direction = new_direction;
    }


    private void moveBy(int dx, int dy){
        SnakeSegment nextHeadPosition = new SnakeSegment(new Point(body.peekFirst().location.x + dx,
                                                                   body.peekFirst().location.y + dy));
        if (body.size() > 1 && body.get(1).equals(nextHeadPosition)) {
            direction = direction.opposite();
            return;
        }
        if (nextHeadPosition.location.x <0 || nextHeadPosition.location.x >= GameConsts.WIDTH)
            nextHeadPosition.location.x = mod(nextHeadPosition.location.x, GameConsts.WIDTH);
        if (nextHeadPosition.location.y <0 || nextHeadPosition.location.y >= GameConsts.HEIGHT)
            nextHeadPosition.location.y = mod(nextHeadPosition.location.y, GameConsts.HEIGHT);
        if (toGrow > 0){
            toGrow--;
        } else body.removeLast();
        body.addFirst(nextHeadPosition);
    }

    private int mod(int a, int b) { return (a%b + b)%b; }

    public void updatePosition(){

        moveBy(direction.x, direction.y);
    }

    public void grow(){
        toGrow++;
    }
    public void grow(int len){
        toGrow+=len;
    }
}
