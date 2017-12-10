package com.bigz.app.util;

import com.bigz.app.util.BD.DBHandler;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by User on 10.12.2017.
 */
public class PropertiesHandlerTest {

    @After
    public void after() throws IOException {
        PropertiesHandler.getInstance(true).setProperty("test","false");
    }

    @Test
    public void getInstance() throws Exception {
        PropertiesHandler p1 = PropertiesHandler.getInstance(true);
        assertNotNull(p1);
    }

    @Test
    public void getProperty() throws Exception {
        assertEquals(PropertiesHandler.getInstance(true).getProperty("speed"),"70");
        assertEquals(PropertiesHandler.getInstance(true).getProperty("opacity"),"0");
    }

    @Test
    public void setProperty() throws Exception {
        PropertiesHandler.getInstance(true).setProperty("test","true");
        assertEquals("true", PropertiesHandler.getInstance().getProperty("test"));
    }

}