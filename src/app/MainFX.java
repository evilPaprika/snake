package app;


import app.gui.MenuPanelFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        MenuPanelFX menuPanelFX = new MenuPanelFX();
        primaryStage.setTitle("Snake");

        primaryStage.setScene(menuPanelFX.asScene());

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
