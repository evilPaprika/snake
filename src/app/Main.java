package app;


import app.gui.MenuPanel;
import app.util.PropertiesHandler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        MenuPanel menuPanel = new MenuPanel();
        primaryStage.setTitle("Snake");

        primaryStage.setScene(menuPanel.asScene());

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
