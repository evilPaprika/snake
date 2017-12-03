package app.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by User on 03.12.2017.
 */
public class PropertiesHandler {

    private static Properties properties = null;
    private static File file;

    private static PropertiesHandler ourInstance = null;

    public static PropertiesHandler getInstance() throws IOException {
        if (ourInstance == null)
            ourInstance = new PropertiesHandler();
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
        //Сохраняем свойства в файл.
        properties.store(new FileOutputStream(file), null);
    }
}
