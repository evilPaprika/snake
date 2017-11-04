package app.game;

import app.util.Direction;
import app.util.Point;
import app.util.UtilFunctions;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static app.util.GameConsts.EPSILON;

public class Snake implements CompoundObject {
    private LinkedList<SnakeSegment> body = new LinkedList<>();
    public ArrayList<DirectionMarker> markers = new ArrayList<>();
    private boolean isDead;
    private Direction direction;
    private int score;
    private int toGrow;
    private Color color;


    public Snake() {
        this(Direction.DOWN);
    }

    public Snake(Direction initialDirection) {
        this(initialDirection, new Point(5, 5), 3, Color.GREEN);
    }

    public Snake(Direction initialDirection, Point initialPosition, int tailLength) {
        toGrow = tailLength;
        direction = initialDirection;
        this.color = Color.GREEN;
        body.addFirst(new SnakeSegment(initialPosition, initialDirection, this));
    }

    public Snake(Direction initialDirection, Point initialPosition, int tailLength,  Color color) {
        toGrow = tailLength;
        direction = initialDirection;
        body.addFirst(new SnakeSegment(initialPosition, initialDirection, this));
        this.color = color;
    }

    private void moveBy(int dx, int dy) {
        Point nextHeadPosition = new Point(body.peekFirst().getLocation().x + dx,
                body.peekFirst().getLocation().y + dy);
        nextHeadPosition = UtilFunctions.makePositionInBoundaries(nextHeadPosition);
        if (toGrow > 0) {
            toGrow--;
        } else body.removeLast();
        body.addFirst(new SnakeSegment(nextHeadPosition, direction,this));
    }

    public void setDirection(Direction new_direction) {
        if (!new_direction.isOpposite(direction))
            direction = new_direction;
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

    public Color getColor() {
        return color;
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
            score += ((Food) g).getScoreToAdd() * body.size();
            grow();
        } else if (body.peekFirst().isDead()) {
            isDead = true;
        } else {
            boolean flag = false;
            for (SnakeSegment segment: body){
                if (segment.isDead())
                    flag = true;
                if (flag)
                    segment.setIsDead(true);
            }
            body.removeIf(SnakeSegment::isDead);
        }
    }
}
