package app.game;

import app.util.Direction;
import app.util.Point;

import java.util.*;

public class Snake implements CompoundObject {
    private LinkedList<SnakeSegment> body = new LinkedList<>();
    public ArrayList<DirectionMarker> markers = new ArrayList<>();
    private float speed;
    private boolean isDead;
    private int score;
    private int toGrow;


    public Snake() {
        this(Direction.DOWN);
    }

    public Snake(Direction initialDirection) {
        this(initialDirection, new Point(5, 5), 3, 1);
    }

    public Snake(float speed){
        this(Direction.DOWN, new Point(5, 5), 3, speed);
    }

    public Snake(Direction initialDirection, Point initialPosition, int tailLength) {
        toGrow = tailLength;
        this.speed = 1;
        body.addFirst(new SnakeSegment(initialPosition, initialDirection, this));
    }

    public Snake(Direction initialDirection, Point initialPosition, int tailLength, float speed) {
        toGrow = tailLength;
        this.speed = speed;
        body.addFirst(new SnakeSegment(initialPosition, initialDirection, this));
    }


    public void setDirection(Direction new_direction) {
        Direction old_direction = body.peekFirst().getDirection();
        if (!new_direction.isOpposite(old_direction)) {
            if (old_direction == Direction.DOWN || old_direction == Direction.RIGHT)
                markers.add(new DirectionMarker(new_direction, new Point(Math.ceil(body.peekFirst().getLocation().getX()), Math.ceil(body.peekFirst().getLocation().getY()))));
            else markers.add(new DirectionMarker(new_direction, new Point(Math.floor(body.peekFirst().getLocation().getX()), Math.floor(body.peekFirst().getLocation().getY()))));
        }
    }


    private void moveBy(float dx, float dy) {
        /*

        System.out.print(body.peekFirst().getLocation().getY()% 1 == 0);
        if (toGrow > 0 && body.peekFirst().getLocation().getX() % 1 == 0 && body.peekFirst().getLocation().getY()% 1 == 0) {
                body.addFirst(new SnakeSegment(new Point(body.peekFirst().getLocation().x + direction.getX(), body.peekFirst().getLocation().y + direction.getY()), this));
                toGrow--;

        } else {
            Point prevSegPosition = new Point(body.peekFirst().getLocation().x + direction.getX(), body.peekFirst().getLocation().y +  direction.getY());
            Point prevDelta = (new Point(direction.getX(), direction.getY()));
            for (SnakeSegment seg : body) {
                System.out.print(seg.getLocation());
                Point t = seg.getLocation();
                Point delta = new Point(prevSegPosition.getX() - seg.getLocation().getX(),  prevSegPosition.getY() - seg.getLocation().getY());
                System.out.print(delta);

                if (abs(delta.getX()) == abs(delta.getY())) {
                    System.out.print("test");
                    if (abs(prevDelta.getX()) > 0) {
                        System.out.print("getY");
                        seg.setLocation(new Point(seg.getLocation().getX(), seg.getLocation().getY() + (delta.getY()/abs(delta.getY())) * speed));
                    }
                    else {
                        System.out.print("getX");
                        seg.setLocation(new Point(seg.getLocation().getX() + (delta.getX()/abs(delta.getX())) * speed, seg.getLocation().getY()));
                    }
                }
                else if (abs(delta.getX()) < abs(delta.getY())) {
                    System.out.print(" + direction.getY() * speed = ");
                    System.out.print(direction.getY() * speed);
                    seg.setLocation(new Point(seg.getLocation().getX(), seg.getLocation().getY() + (delta.getY()/abs(delta.getY())) * speed));
                }
                else {
                    System.out.print(" + direction.getX() * speed = ");
                    System.out.print(direction.getX() * speed);
                    seg.setLocation(new Point(seg.getLocation().getX() + (delta.getX() / abs(delta.getX())) * speed, seg.getLocation().getY()));
                }
                prevSegPosition = t;
                prevDelta = delta;
                System.out.print("\n");
            }
            System.out.print("\n");
        }
        */
        /*
        Point nextHeadPosition = new Point(body.peekFirst().getLocation().x + dx,body.peekFirst().getLocation().y + dy);
        nextHeadPosition = UtilFunctions.makePositionInBoundaries(nextHeadPosition);
        if (toGrow > 0) {
            toGrow--;
        } else body.removeLast();
        body.addFirst(new SnakeSegment(nextHeadPosition, this));
        */
    }

    void updatePosition() {
        //moveBy(direction.getX()*speed, direction.getY()*speed);

        Point nextHeadPosition = new Point(body.peekFirst().getLocation().x,body.peekFirst().getLocation().y);
        if (toGrow > 0){
            body.addFirst(new SnakeSegment(new Point(body.peekFirst().getLocation().add(body.peekFirst().getDirection().toPoint())),
                    /*НЕБЕЗОПАСНО ПРИ ПРИРОСТЕ*/ body.peekFirst().getDirection(), this));
            toGrow--;
        }
        for (SnakeSegment seg : body) {
            Point old_location = seg.getLocation();
            Point new_location = old_location.add(seg.getDirection().toPoint().mul(speed));
            boolean flag = true;
            for (DirectionMarker mrk: markers) {
                if (old_location.distance(mrk.getLocation()) + mrk.getLocation().distance(new_location) == old_location.distance(new_location)){
                    Point delta = ((seg.getDirection().toPoint().mul(old_location.distance(mrk.getLocation()))).add(mrk.getDirection().toPoint()
                            .mul(mrk.getLocation().distance(new_location)))).mul(2); //?? почему работает если умножить на 2??
                    seg.setLocation( seg.getLocation().add(delta ));
                    seg.setDirection(mrk.getDirection());
                    flag = false;
                    break;
                }
            }
            if (flag) seg.setLocation(new_location);

            System.out.print(seg.getLocation());
            System.out.print("\n");
        }

        System.out.print("\n");
        System.out.print(markers.size());
        System.out.print("\n");
        for (DirectionMarker mrk: markers) {
            System.out.print(mrk);
            System.out.print("\n");
        }
        System.out.print("\n");


    }

    private void grow() {
        this.grow(1);
    }

    public void grow(int len) {
        toGrow += len;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    public int getScore() {
        return score;
    }

    @Override
    public LinkedList<SnakeSegment> getParts() {
        return body;
    }

    @Override
    public void collideWith(GameObject g) {
        if (g instanceof Food) {
            score += ((Food) g).getScoreToAdd();
            grow();
        } else isDead = true;
    }
}
