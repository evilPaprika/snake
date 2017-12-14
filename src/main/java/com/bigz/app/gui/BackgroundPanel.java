package com.bigz.app.gui;


import com.bigz.app.util.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.controlsfx.dialog.ProgressDialog;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class BackgroundPanel {
    private static BorderPane root;
    private Pane mainPane;
    private Scene scene;
    private ImageView img;
    private String imgUrl;
    private List<ImageFromWeb> imageFromWebList;
    private GridPane gridPane;



    public BackgroundPanel(Scene scene) {
        root = new BorderPane();
        this.scene = scene;
        try {
            img = new ImageView(new Image(PropertiesHandler.getInstance().getProperty("img")));
        } catch (IOException e) {
            img = new ImageView(new Image("img/menu.jpg"));
            new NotificationMessage("Error", "Установлено изображение по умолчанию").run();
        }
        img.setFitHeight(GameConsts.PANEL_HEIGHT + GameConsts.HEIGHT);
        img.setFitWidth(GameConsts.PANEL_WIDTH);

        Requester requester = new Requester();
        try {
            imageFromWebList = requester.sendPost("Космос");
        } catch (Exception e) {
            new NotificationMessage("Error", "Ошибка доступа к сервису").run();
        }

        mainPane = new Pane();
        mainPane.getChildren().add(img);
        gridPane = new GridPane();
        root.setCenter(mainPane);
        render();
    }

    private void render(){

        Button back = new Button("Назад");
        back.setTranslateY(GameConsts.PANEL_HEIGHT-25);
        back.setTranslateX(GameConsts.PANEL_WIDTH /2 -25);

        back.setOnMouseClicked(event -> {
            try {
                PropertiesHandler.getInstance().setProperty("img",imgUrl);
            } catch (IOException e) {
                new NotificationMessage("Error", "Не удалось записать в файл").run();
            }
            scene.setRoot(MenuPanel.asRoot());
        });


        TextField textField = new TextField();
        Button button = new Button("Найти");

        button.setTranslateX(GameConsts.PANEL_HEIGHT/2 + 250);
        button.setTranslateY(20);

        textField.setTranslateX(GameConsts.PANEL_HEIGHT/2-75);
        textField.setTranslateY(20);
        textField.setMinWidth(300);
        mainPane.getChildren().addAll(textField, button,back);

        button.setOnMouseClicked(event -> {
            if (!textField.getCharacters().toString().isEmpty()){
                try {
                    imageFromWebList = new Requester().sendPost(textField.getCharacters().toString());
                    setComponent();
                } catch (Exception e) {
                    new NotificationMessage("Error", "Невозможно сделать запрос");
                }
            }
            else new NotificationMessage("","Введите текст").run();
        });

        mainPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!textField.getCharacters().toString().isEmpty()) {
                    try {
                        imageFromWebList = new Requester().sendPost(textField.getCharacters().toString());
                        setComponent();
                    } catch (Exception e) {
                        new NotificationMessage("Error", "Невозможно сделать запрос");
                    }
                } else new NotificationMessage("", "Введите текст").run();
            }
        });

        gridPane.setHgap(50);
        gridPane.setVgap(25);

        gridPane.setLayoutX(50);
        gridPane.setLayoutY(100);

        setComponent();

        mainPane.getChildren().add(gridPane);
    }

    private void setComponent(){

        gridPane.getChildren().clear();

        int size;
        if (imageFromWebList.size() > 20) size = 20;
        else size = imageFromWebList.size();

        for (int i = 0, j= 0; size > 0; i++){
            size--;
            ImageView img1 = new ImageView(new Image(imageFromWebList.get(size).getPreview()));
            img1.setFitHeight(100);
            img1.setFitWidth(100);

            if (i==5) {
                i = 0;
                j++;
            }

            gridPane.add(img1, i, j, 1, 1);

            int finalSize = size;
            img1.setOnMouseClicked(event -> {
                mainPane.getChildren().remove(img);
                imgUrl = imageFromWebList.get(finalSize).getOrigin();
                img = new ImageView(new Image(imgUrl));
                img.setFitHeight(GameConsts.PANEL_HEIGHT + GameConsts.HEIGHT);
                img.setFitWidth(GameConsts.PANEL_WIDTH);
                mainPane.getChildren().add(0,img);
            });

        }
    }

    public BorderPane asRoot(){
        return root;
    }
}
