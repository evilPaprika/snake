package app.game;

import app.util.Direction;
import app.util.Point;
import java.awt.*;

import static app.util.UtilFunctions.makePositionInBoundaries;

public class SnakeSegment implements SimpleObject {
    private Point location;
    private Snake parent;
    private Direction direction;
    private boolean isDead;

    public void setIsDead(boolean dead){isDead = dead;}

    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }


    SnakeSegment(Point location, Direction direction, Snake parent){
        this.direction = direction;
        this.location = location;
        this.parent = parent;
        isDead= false;
    }

    @Override
    public Color getColor() { return parent.getColor(); }

    @Override
    public Point getLocation() { return location; }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void collideWith(GameObject g) {
        if((g instanceof SnakeSegment && g.equals(((SnakeSegment) g).parent.getParts().peekFirst())) ||
                (this.equals(parent.getParts().peekFirst()) && (!(g instanceof Food) && !(g instanceof  SnakeSegment) ||
                        (g instanceof SnakeSegment && this.parent.equals(((SnakeSegment) g).parent)))))
            isDead = true;
        parent.collideWith(g); }
}
