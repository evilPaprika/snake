package com.bigz.app.util;

import java.io.*;
import java.util.Properties;


public class PropertiesHandler {

    private static Properties properties = null;
    private static File file;

    private static PropertiesHandler ourInstance = null;

    public static PropertiesHandler getInstance(){
        if (ourInstance == null)
            try {
                ourInstance = new PropertiesHandler();
            } catch (IOException e) {
                new NotificationMessage("Error", String.valueOf(e)).run();
            }
        return ourInstance;
    }

    private PropertiesHandler() throws IOException {
        properties = new Properties();
        file = new File("src/application.properties");
        properties.load(new FileInputStream(file));
    }

    public String getProperty(String var){
        return properties.getProperty(var);
    }

    public void setProperty(String key, String var) throws IOException {
        properties.setProperty(key, var);
        properties.store(new FileOutputStream(file), null);

    }
}
