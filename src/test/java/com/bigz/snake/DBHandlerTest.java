//package com.bigz.snake;
//
//import com.bigz.app.util.BD.DBHandler;
//import com.bigz.app.util.BD.Statistic;
//import org.junit.Test;
//
//import java.sql.SQLException;
//
//import static org.junit.Assert.*;
//
//public class DBHandlerTest {
//
//    @Test
//    public void getInstance() {
//        DBHandler original = DBHandler.getInstance();
//        DBHandler dublicat = DBHandler.getInstance();
//        assertTrue(original.equals(dublicat));
//    }
//
//    @Test
//    public void createDB() throws SQLException, ClassNotFoundException {
//        assertTrue(DBHandler.getInstance().createDB());
//    }
//
//    @Test
//    public void getAllStatistics() {
//        int oldSize = DBHandler.getInstance().getAllStatistics().size();
//        DBHandler.getInstance().addScore(new Statistic("TESTINDEBUG",99));
//        DBHandler.getInstance().addScore(new Statistic("TESTINDEBUG2",99));
//        assertEquals(2,DBHandler.getInstance().getAllStatistics().size() - oldSize);
//        DBHandler.getInstance().deleteStatistic("TESTINDEBUG");
//        DBHandler.getInstance().deleteStatistic("TESTINDEBUG2");
//    }
//
//    @Test
//    public void isAdd() {
//        assertTrue(DBHandler.getInstance().isAdd("TESTINDEBUG"));
//
//        DBHandler.getInstance().addScore(new Statistic("TESTINDEBUG",99));
//        assertFalse(DBHandler.getInstance().isAdd("TESTINDEBUG"));
//
//        DBHandler.getInstance().deleteStatistic("TESTINDEBUG");
//    }
//
//    @Test
//    public void addScore() {
//        DBHandler.getInstance().addScore(new Statistic("TESTINDEBUG",99));
//        assertEquals("TESTINDEBUG", DBHandler.getInstance().getElementByName("TESTINDEBUG").name);
//        assertEquals(99,DBHandler.getInstance().getElementByName("TESTINDEBUG").score);
//        DBHandler.getInstance().deleteStatistic("TESTINDEBUG");
//    }
//
//    @Test
//    public void deleteStatistic() {
//        DBHandler.getInstance().addScore(new Statistic("TESTINDEBUG",99));
//        DBHandler.getInstance().deleteStatistic("TESTINDEBUG");
//        assertNull(DBHandler.getInstance().getElementByName("TESTINDEBUG"));
//    }
//
//}