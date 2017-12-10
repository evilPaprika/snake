package com.bigz.app.util.BD;

import com.bigz.app.util.PropertiesHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;


public class DBHandlerTest {

    private String path;

    public DBHandlerTest() throws IOException {
        path = PropertiesHandler.getInstance().getProperty("db");
    }

    @Before
    public void before() throws IOException {
        PropertiesHandler.getInstance().setProperty("db","jdbc:sqlite:D:/test.db");
    }

    @After
    public void after() throws IOException, SQLException {
        PropertiesHandler.getInstance().setProperty("db",path);
        DBHandler.getInstance().delete(DBHandler.getInstance().getAllStatistic());
    }

    @Test
    public void getInstance() throws Exception {
        DBHandler original = DBHandler.getInstance();
        assertNotNull(original);
    }

    @Test
    public void add() throws IOException, SQLException {
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));

        assertEquals(DBHandler.getInstance().getAllStatistic().size(),11);

    }

    @Test
    public void getAllStatisticWithOrder() throws Exception {
        Statistic one = new Statistic("test",11);
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(one);
        assertEquals(DBHandler.getInstance().getAllStatisticWithOrder().get(0).score,11);
    }

    @Test
    public void getAllStatistic() throws Exception {
        Statistic one = new Statistic("test",11);
        DBHandler.getInstance().add(new Statistic("test",0));
        DBHandler.getInstance().add(new Statistic("test",0));
        assertEquals(DBHandler.getInstance().getAllStatistic().size(),2);
    }


}