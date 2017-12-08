package com.bigz.app.game;

import javafx.scene.paint.Color;

import java.awt.*;


public interface SimpleObject extends GameObject {
    Color getColor();
    Point getLocation();
}