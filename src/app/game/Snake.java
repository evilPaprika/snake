package app.game;

import app.util.Direction;
import app.util.UtilFunctions;

import java.awt.*;
import java.util.LinkedList;


public class Snake implements CompoundObject {
    private LinkedList<SnakeSegment> body = new LinkedList<>();
    private LinkedList<Runnable> actions = new LinkedList<Runnable>();
    private boolean isDead;
    private Direction direction;
    private int score;
    private int toGrow;
    private Color color;


    public Snake() {
        this(Direction.DOWN);
    }

    private Snake(Direction initialDirection) {
        this(initialDirection, new Point(5, 5), 3, Color.GREEN);
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

    public void setDirection(Direction newDirection) {
        if (!newDirection.isOpposite(direction))
            direction = newDirection;
    }

    void updatePosition() {
        if (!actions.isEmpty()) actions.removeLast().run();
        moveBy(direction.getX(), direction.getY());
    }

    public void addAction(Runnable foo){
        if (actions.isEmpty() || actions.peekFirst().equals(foo))
            actions.addFirst(foo);
    }

    void kill() { this.isDead = true;}

    public int getLength() { return body.size(); }

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

    void addScore(int toAdd) {score += toAdd;}

    @Override
    public LinkedList<SnakeSegment> getParts() {
        return body;
    }

    @Override
    public void collideWith(GameObject g) {
        if (g instanceof SnakeInteraction)
            ((SnakeInteraction) g).collideWithSnake(this);
    }
}
