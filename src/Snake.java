import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    public LinkedList<Point> body = new LinkedList<Point>();;
    public Point direction;
    private int toGrow;

    Snake(){
        this(new Point(0, 1));
    }

    Snake(Point initialDirection){
        this(initialDirection, new Point(5, 5), 3);
    }

    Snake(Point initialDirection, Point initialPosition, int tailLength){
        toGrow = tailLength;
        direction = initialDirection;
        body.addFirst(initialPosition);
    }


    private void moveBy(int dx, int dy){
        Point nextHeadPosition = new Point(body.peekFirst());
        nextHeadPosition.x += dx;
        nextHeadPosition.y += dy;
        if (body.size() > 1 && body.get(1).equals(nextHeadPosition)) {
            direction = new Point(-direction.x, -direction.y);
            return;
        }
        if (nextHeadPosition.x <0 || nextHeadPosition.x >= GameConsts.WIDTH)
            nextHeadPosition.x = mod(nextHeadPosition.x, GameConsts.WIDTH);
        if (nextHeadPosition.y <0 || nextHeadPosition.y >= GameConsts.HEIGHT)
            nextHeadPosition.y = mod(nextHeadPosition.y, GameConsts.HEIGHT);
        if (toGrow > 0){
            toGrow--;
        } else body.removeLast();
        body.addFirst(nextHeadPosition);
    }

    int mod(int a, int b) { return (a%b + b)%b; }

    public void updatePosition(){
        moveBy(direction.x, direction.y);
    }

    public void grow(){
        toGrow++;
        //body.addLast(new Point(body.peekLast()));
    }
}
