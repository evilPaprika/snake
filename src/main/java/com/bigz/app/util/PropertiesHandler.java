package com.bigz.app.util;

import java.io.*;
import java.util.Properties;


public class PropertiesHandler {

    private static Properties properties = null;
    private static File file;
    private static String filePath = "src/application.properties";
    private static PropertiesHandler ourInstance = null;

    public static PropertiesHandler getInstance(boolean debug) throws IOException {

        if (ourInstance == null)
                if (debug) filePath = "src/test.properties";
            ourInstance = new PropertiesHandler();
        return ourInstance;
        }



    public static PropertiesHandler getInstance() throws IOException {

        if (ourInstance == null)
            ourInstance = new PropertiesHandler();
        return ourInstance;
    }

    private PropertiesHandler() throws IOException {
        properties = new Properties();
        file = new File(filePath);
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
