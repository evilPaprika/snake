package com.bigz.app.gui;

import com.bigz.app.util.*;
import com.bigz.app.util.BD.DBHandler;
import com.bigz.app.util.BD.Statistic;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MenuPanel {
    private static BorderPane root;
    private static Pane mainPane, scorePane;
    private MenuBox menuBox;
    private Scene scene;
    private static ImageView img;
    private static Rectangle bg;

    public MenuPanel() {

        root = new BorderPane();
        scene = new Scene(root, GameConsts.PANEL_WIDTH, GameConsts.PANEL_HEIGHT + GameConsts.HEIGHT);

        try {
            img = new ImageView(new Image(PropertiesHandler.getInstance().getProperty("img")));
        } catch (IOException e) {
            img = new ImageView(new Image("img/menu.jpg"));
            new NotificationMessage("Error", "Установлено изображение по умолчанию").run();
        }
        img.setFitHeight(GameConsts.PANEL_HEIGHT + GameConsts.HEIGHT);
        img.setFitWidth(GameConsts.PANEL_WIDTH);

        bg = new Rectangle(GameConsts.PANEL_WIDTH,GameConsts.PANEL_HEIGHT + GameConsts.HEIGHT,Color.LIGHTBLUE);

        try {
            if (PropertiesHandler.getInstance().getProperty("opacity") == null)
                bg.setOpacity(0.4);
            else
                bg.setOpacity(Double.valueOf(PropertiesHandler.getInstance().getProperty("opacity")));
        } catch (IOException e) {
            new NotificationMessage("Error", String.valueOf(e)).run();
        }

        mainPane = new Pane();
        root.setCenter(mainPane);

        MenuItem singleGame = new MenuItem("ОДИН ИГРОК");
        MenuItem twoPlayerGame = new MenuItem("ДВА ИГРОКА");
        MenuItem settings = new MenuItem("НАСТРОЙКИ");
        MenuItem score = new MenuItem("ТОП-10");
        MenuItem exitGame = new MenuItem("ВЫХОД");
        SubMenu mainMenu = new SubMenu(200,
                singleGame,twoPlayerGame,settings,score, exitGame);

        MenuItem snakeSpeed = new MenuItem("Изменить скорость змейки");
        MenuItem fon = new MenuItem("Изменить фон");
        MenuItem fonOpacity = new MenuItem("Затемнение фона");
        MenuItem backToMenu = new MenuItem("Назад");
        SubMenu settingsMenu = new SubMenu(200,
                snakeSpeed,fon,fonOpacity,backToMenu);

        MenuItem oneX = new MenuItem("1x");
        MenuItem twoX = new MenuItem("2x");
        MenuItem threeX = new MenuItem("3x");
        MenuItem backToSettings = new MenuItem("Назад");
        SubMenu speedSettings = new SubMenu(200,
                oneX, twoX, threeX, backToSettings);

        MenuItem opZero = new MenuItem("0");
        MenuItem opOne = new MenuItem("0.2");
        MenuItem opTwo = new MenuItem("0.4");
        MenuItem opThree = new MenuItem("0.6");
        MenuItem opAll = new MenuItem("1");
        MenuItem backToSettingsFromOp = new MenuItem("Назад");
        SubMenu opacitySettings = new SubMenu(200,
                opZero, opOne, opTwo, opThree, opAll, backToSettingsFromOp);



        MenuItem backToSettingsFromBoard = new MenuItem("Назад");
        score.setOnMouseClicked(event -> menuBox.setSubMenu(new SubMenu(makeScore(),backToSettingsFromBoard)));

        backToSettingsFromBoard.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));


        fon.setOnMouseClicked(event -> {
            scene.setRoot(new BackgroundPanel(scene).asRoot());
        });

        opZero.setOnMouseClicked(event -> {
            setProperty("opacity", "0");
            menuBox.setBg(0);
        });

        opOne.setOnMouseClicked(event -> {
            setProperty("opacity", "0.2");
            menuBox.setBg(0.2);
        });

        opTwo.setOnMouseClicked(event -> {
            setProperty("opacity", "0.4");
            menuBox.setBg(0.4);
        });

        opThree.setOnMouseClicked(event -> {
            setProperty("opacity", "0.6");
            menuBox.setBg(0.6);
        });

        opAll.setOnMouseClicked(event -> {
            setProperty("opacity", "1");
            menuBox.setBg(1);
        });

        fonOpacity.setOnMouseClicked(event -> {
            menuBox.setSubMenu(opacitySettings);
        });

        backToSettingsFromOp.setOnMouseClicked(event -> {
            menuBox.setSubMenu(settingsMenu);
        });

        oneX.setOnMouseClicked(event -> {
            setProperty("speed", String.valueOf(GameConsts.PAINT_DELAY));
            menuBox.setSubMenu(settingsMenu);
        });

        twoX.setOnMouseClicked(event -> {
            setProperty("speed", String.valueOf(GameConsts.PAINT_DELAY/2));
            menuBox.setSubMenu(settingsMenu);
        });

        threeX.setOnMouseClicked(event -> {
            setProperty("speed", String.valueOf(GameConsts.PAINT_DELAY/3));
            menuBox.setSubMenu(settingsMenu);
        });

        backToSettings.setOnMouseClicked(event -> {
            menuBox.setSubMenu(settingsMenu);
        });

        snakeSpeed.setOnMouseClicked(event -> {
            menuBox.setSubMenu(speedSettings);
        });

        settings.setOnMouseClicked(event -> {
            menuBox.setSubMenu(settingsMenu);
        });

        backToMenu.setOnMouseClicked(event -> {
            menuBox.setSubMenu(mainMenu);
        });

        singleGame.setOnMouseClicked(event -> {
            GamePanel panel = new GamePanel(scene, State.ONE_PLAYER);
            scene.setRoot(panel.asRoot());

        });

        twoPlayerGame.setOnMouseClicked(event -> {
            GamePanel panel = new GamePanel(scene, State.TWO_PLAYERS);
            scene.setRoot(panel.asRoot());
        });

        exitGame.setOnMouseClicked(event-> System.exit(0));

        menuBox = new MenuBox(mainMenu);
        mainPane.getChildren().addAll(img,menuBox);
        run();
    }

    private void setProperty(String opacity, String var) {
        try {
            PropertiesHandler.getInstance().setProperty(opacity, var);
        } catch (IOException e) {
            new NotificationMessage("Error", String.valueOf(e)).run();
        }
    }

    public static BorderPane asRoot(){
        return root;
    }

    public static void updateImg(){
        try {
            img = new ImageView(new Image(PropertiesHandler.getInstance().getProperty("img")));
        } catch (IOException e) {
            img = new ImageView(new Image("img/menu.jpg"));
            new NotificationMessage("Error", "Установлено изображение по умолчанию").run();
        }

        mainPane.getChildren().remove(img);


    }

    public Scene asScene(){ return scene;}

    public void run(){

        FadeTransition ft = new FadeTransition(Duration.seconds(1),menuBox);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private static class MenuItem extends StackPane {
        public  MenuItem(String name){
            Rectangle bg = new Rectangle(400,20,Color.WHITE);
            bg.setOpacity(0.5);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial", FontWeight.BOLD,14));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg,text);
            FillTransition st = new FillTransition(Duration.seconds(0.5),bg);
            setOnMouseEntered(event -> {
                st.setFromValue(Color.DARKGRAY);
                st.setToValue(Color.DARKGOLDENROD);
                st.setCycleCount(Animation.INDEFINITE);
                st.setAutoReverse(true);
                st.play();
            });
            setOnMouseExited(event -> {
                st.stop();
                bg.setFill(Color.WHITE);
            });
        }
    }

    private List<MenuItem> makeScore(){
        int i=1;
        List<MenuItem> list=new ArrayList<>();
        try {
            try {
                for(Statistic e : DBHandler.getInstance().getAllStatisticWithOrder()){
                    if(i>10) break;
                    list.add(new MenuItem(i+". "+ e.name +" : " + e.score));
                    i++;
                }
            } catch (IOException e) {
                new NotificationMessage("Error", String.valueOf(e)).run();
            }
        } catch (SQLException e) {
            new NotificationMessage("Error", String.valueOf(e)).run();
        }
        return list;

    }

    private static class MenuBox extends Pane{

        static SubMenu subMenu;
        public MenuBox(SubMenu subMenu){
            MenuBox.subMenu = subMenu;
            getChildren().addAll(bg, subMenu);
        }
        public void setSubMenu(SubMenu subMenu){
            getChildren().remove(MenuBox.subMenu);
            MenuBox.subMenu = subMenu;
            getChildren().add(MenuBox.subMenu);
        }
        public void setBg(double var){
            bg.setOpacity(var);
        }
    }

    private class SubMenu extends VBox {

        public SubMenu(List<MenuItem> list, MenuItem back){
            setSpacing(15);
            setTranslateY(150);
            setTranslateX(GameConsts.PANEL_WIDTH/2 - 4*GameConsts.WIDTH);
            for(MenuItem item : list){
                getChildren().addAll(item);
            }
            getChildren().add(back);
        }

        public SubMenu(int width, MenuItem...items){
            setSpacing(15);
            setTranslateY(150);
            setTranslateX(GameConsts.PANEL_WIDTH/2 - 4*GameConsts.WIDTH);
            for(MenuItem item : items){
                getChildren().addAll(item);
            }
        }
    }
}
