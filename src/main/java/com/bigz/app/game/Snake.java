package com.bigz.app.game;

import com.bigz.app.util.Direction;
import com.bigz.app.util.UtilFunctions;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.LinkedList;
import java.util.Optional;


public class Snake implements CompoundObject {
    private LinkedList<SnakeSegment> body = new LinkedList<>();
    private Optional<Runnable> action = Optional.empty();
    private boolean isDead;
    private Direction direction;
    private int score;
    private int toGrow;
    private Point spawnPosinton;
    private Direction initialDirection;
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
        this.initialDirection = initialDirection;
        spawnPosinton = initialPosition;
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
        System.out.print(initialDirection);
        action.ifPresent(runnable -> {
            runnable.run();
            action = Optional.empty();
        });
        moveBy(direction.getX(), direction.getY());
    }

    public void setAction(Runnable foo){
        if (!action.isPresent())
            action = Optional.of(foo);
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

    void respawn(){
        toGrow = 2*body.size()/3;
        body.clear();
        isDead = false;
        body.addFirst(new SnakeSegment(spawnPosinton, initialDirection, this));
        direction = initialDirection;
    }

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
