import java.io.IOException;
import java.util.Scanner;
import java.awt.Point;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // еда не запилена, управление wasd и enter
        Scanner scan = new Scanner(System.in);
        Snake snake = new Snake();

        while (true) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // отчистка экрана, в IDEA не рабоатает, только в консоли
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (snake.body.contains(new Point(j,i)))
                        System.out.print("@");
                    else System.out.print(".");
                    System.out.print(" ");
                }
                System.out.println();
            }

            String command = scan.nextLine();
            switch (command.length() > 0 ? command.charAt(0): ' '){
                case 'w': snake.Move(0, -1);
                    break;
                case 's': snake.Move(0, 1);
                    break;
                case 'a': snake.Move(-1, 0);
                    break;
                case 'd': snake.Move(1, 0);
                    break;
                default: break;
            }
        }
    }
}

