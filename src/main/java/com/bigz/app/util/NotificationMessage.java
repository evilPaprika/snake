package com.bigz.app.util;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * Created by User on 10.12.2017.
 */
public class NotificationMessage {

    private Notifications notifications;

    public NotificationMessage(String title, String message) {
        notifications = Notifications.create()
                .title(title)
                .text(message)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
    }

    public void run(){
        notifications.showConfirm();
    }
}
