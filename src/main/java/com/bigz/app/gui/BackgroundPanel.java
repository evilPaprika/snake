package com.bigz.app.gui;


import com.bigz.app.util.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.controlsfx.dialog.ProgressDialog;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class BackgroundPanel {
    private static BorderPane root;
    private Pane mainPane;
    private Scene scene;
    private ImageView img;
    private List<ImageFromWeb> imageFromWebList;
    private GridPane gridPane;


    public BackgroundPanel(Scene scene) {

        root = new BorderPane();
        this.scene = scene;
        img = new ImageView(new Image("img/menu.jpg"));
        img.setFitHeight(GameConsts.PANEL_HEIGHT + GameConsts.HEIGHT);
        img.setFitWidth(GameConsts.PANEL_WIDTH);

        Requester requester = new Requester();
        try {
            imageFromWebList = requester.sendPost("xmas");
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

        gridPane.setHgap(100);
        gridPane.setVgap(25);

        gridPane.setLayoutX(50);
        gridPane.setLayoutY(100);

        setComponent();

        mainPane.getChildren().add(gridPane);
    }

    private void setComponent(){
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
                img = new ImageView(new Image(imageFromWebList.get(finalSize).getOrigin()));
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
