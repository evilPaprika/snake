package app.game;

import app.util.Direction;
import app.util.UtilFunctions;
import app.util.Point;
import java.util.LinkedList;

public class Snake implements CompoundObject {
    private LinkedList<SnakeSegment> body = new LinkedList<>();
    private Direction direction;
    private double speed;
    private boolean isDead;
    private int score;
    private int toGrow;


    public Snake() {
        this(Direction.DOWN);
    }

    private Snake(Direction initialDirection) {
        this(initialDirection, new Point(5, 5), 3);
    }

    public Snake(Direction initialDirection, Point initialPosition, int tailLength) {
        toGrow = tailLength;
        direction = initialDirection;
        speed = 0.1;
        body.addFirst(new SnakeSegment(initialPosition, this));
    }


    public void setDirection(Direction new_direction) {
        if (!new_direction.isOpposite(direction))
            direction = new_direction;
    }


    private void moveBy(int dx, int dy) {




        Point nextHeadPosition = new Point(body.peekFirst().getLocation().x + dx,body.peekFirst().getLocation().y + dy);
        nextHeadPosition = UtilFunctions.makePositionInBoundaries(nextHeadPosition);
        if (toGrow > 0) {
            toGrow--;
        } else body.removeLast();
        body.addFirst(new SnakeSegment(nextHeadPosition, this));

    }

    void updatePosition() {
        moveBy(direction.getX(), direction.getY());
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
