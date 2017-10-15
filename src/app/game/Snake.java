package app.game;

import app.util.Direction;
import app.util.GameConsts;
import app.util.UtilFunctions;

import java.awt.*;
import java.util.LinkedList;

public class Snake implements GameCompoundObject{
    public LinkedList<SnakeSegment> body = new LinkedList<>();;
    private Direction direction;
    public boolean isDead;
    public int score;
    private int toGrow;


    public Snake(){this(Direction.DOWN);}
    public Snake(Direction initialDirection){
        this(initialDirection, new Point(5, 5), 3);
    }
    public Snake(Direction initialDirection, Point initialPosition, int tailLength){
        toGrow = tailLength;
        direction = initialDirection;
        body.addFirst(new SnakeSegment(initialPosition, this));
    }


    public void setDirection(Direction new_direction){
        if (new_direction.opposite() != direction)
            direction = new_direction;
    }


    private void moveBy(int dx, int dy){
        Point nextHeadPosition = new Point(body.peekFirst().getLocation().x + dx,
                                           body.peekFirst().getLocation().y + dy);
        if (nextHeadPosition.x <0 || nextHeadPosition.x >= GameConsts.WIDTH)
            nextHeadPosition.x = UtilFunctions.mod(nextHeadPosition.x, GameConsts.WIDTH);
        if (nextHeadPosition.y <0 || nextHeadPosition.y >= GameConsts.HEIGHT)
            nextHeadPosition.y = UtilFunctions.mod(nextHeadPosition.y, GameConsts.HEIGHT);
        if (toGrow > 0){
            toGrow--;
        }
        else body.removeLast();
        body.addFirst(new SnakeSegment(nextHeadPosition, this));
    }

    void updatePosition(){
        moveBy(direction.x, direction.y);
    }

    void grow(){
        toGrow++;
    }
    void grow(int len){
        toGrow+=len;
    }

    @Override
    public LinkedList<SnakeSegment> getChildren() {
        return  body;
    }

    @Override
    public void actionWhenCollided(GameObject g) {
        if (g instanceof Apple){
            score+=10;
            grow();
        }
        else isDead = true;
    }
}
