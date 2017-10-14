package app.game;

import app.util.Direction;
import app.util.GameConsts;

import java.awt.*;
import java.util.LinkedList;

public class Snake implements GameCompoundObject{
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
        SnakeSegment nextHeadPosition = new SnakeSegment(new Point(body.peekFirst().getLocation().x + dx,
                                                                   body.peekFirst().getLocation().y + dy));
        if (body.size() > 1 && body.get(1).equals(nextHeadPosition)) {
            direction = direction.opposite();
            return;
        }
        if (nextHeadPosition.getLocation().x <0 || nextHeadPosition.getLocation().x >= GameConsts.WIDTH)
            nextHeadPosition.getLocation().x = mod(nextHeadPosition.getLocation().x, GameConsts.WIDTH);
        if (nextHeadPosition.getLocation().y <0 || nextHeadPosition.getLocation().y >= GameConsts.HEIGHT)
            nextHeadPosition.getLocation().y = mod(nextHeadPosition.getLocation().y, GameConsts.HEIGHT);
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

    @Override
    public LinkedList<SnakeSegment> getChildren() {
        return  body;
    }

    @Override
    public void actionWhenColided(GameObject g) {

    }
}
