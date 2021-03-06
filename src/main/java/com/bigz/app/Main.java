package com.bigz.app;


import com.bigz.app.gui.MenuPanel;
import com.bigz.app.util.BD.DBHandler;
import com.bigz.app.util.BD.Statistic;
import com.bigz.app.util.Requester;
import javafx.application.Application;
import javafx.stage.Stage;

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
