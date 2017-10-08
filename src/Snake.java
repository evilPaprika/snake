import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    public LinkedList<Point> body;
    public Point speed;

    Snake(){
        body = new LinkedList<Point>();
        speed = new Point(0,1);
        body.addFirst(new Point(5,3));
        body.addFirst(new Point(5,4));
        body.addFirst(new Point(5,5));
    }


    public void MoveBy(int dx, int dy){
        Point newHead = new Point(body.peekFirst());
        newHead.x += dx;
        newHead.y += dy;
        if (body.contains(newHead)) return;
        body.removeLast();
        body.addFirst(newHead);
    }


    public void UpdatePosition(){
        MoveBy(speed.x,speed.y);
    }


    public void Grow(){
        body.addLast(new Point(body.peekLast()));
    }
}
