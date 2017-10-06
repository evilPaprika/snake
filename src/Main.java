import java.io.IOException;
import java.util.Scanner;
import java.awt.Point;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // еда не запилена, управление wasd и enter
        Scanner scan = new Scanner(System.in);
        LinkedList<Point> snakeBody = new LinkedList<Point>();

        snakeBody.addFirst(new Point(5,5));
        snakeBody.addFirst(new Point(5,4));
        snakeBody.addFirst(new Point(5,3));

        while (true) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // отчистка экрана, в IDEA не рабоатает, только в консоли
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (snakeBody.contains(new Point(j,i)))
                        System.out.print("@");
                    else System.out.print(".");
                    System.out.print(" ");
                }
                System.out.println();
            }
            Point newHead = new Point(snakeBody.peekFirst());
            snakeBody.removeLast();
            String command = scan.nextLine();
            switch (command.length() > 0 ? command.charAt(0): ' '){
                case 'w': newHead.y-=1;
                    break;
                case 's': newHead.y+=1;
                    break;
                case 'a': newHead.x-=1;
                    break;
                case 'd': newHead.x+=1;
                    break;
                default: break;
            }
            snakeBody.addFirst(newHead);
        }
    }
}

