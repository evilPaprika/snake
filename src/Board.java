import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board {
    Snake snake;
    Food food;

    Board() {
        snake = new Snake();
        food = new Food();
    }
}
