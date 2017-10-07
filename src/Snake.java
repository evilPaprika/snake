import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    public LinkedList<Point> body;

    Snake(){
        body = new LinkedList<Point>();
        body.addFirst(new Point(5,5));
        body.addFirst(new Point(5,4));
        body.addFirst(new Point(5,3));
    }

    public void Move(int dx, int dy){
        Point newHead = new Point(body.peekFirst());
        newHead.x += dx;
        newHead.y += dy;
        if (body.contains(newHead)) return;
        body.removeLast();
        body.addFirst(newHead);
    }
}
