package com.bigz.app.util;

import java.io.*;
import java.util.Properties;


public class PropertiesHandler {

    private static Properties properties = null;
    private static File file;

    private static PropertiesHandler ourInstance = null;

    public static PropertiesHandler getInstance(){
        if (ourInstance == null) {
            ourInstance = new PropertiesHandler();
        }
        return ourInstance;
    }

    private PropertiesHandler() {
        properties = new Properties();
        file = new File("src/application.properties");
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            new NotificationMessage("Error", String.valueOf(e)).run();
        }
    }

    public String getProperty(String var){
        return properties.getProperty(var);
    }

    public void setProperty(String key, String var) {
        properties.setProperty(key, var);
        try {
            properties.store(new FileOutputStream(file), null);
        } catch (IOException e) {
            new NotificationMessage("Error", String.valueOf(e)).run();
        }

    }
}
