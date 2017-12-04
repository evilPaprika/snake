package tests;

import app.util.BD.DBHandler;
import app.util.BD.Statistic;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBHandlerTest {

    @Test
    void getInstance() {
        DBHandler original = DBHandler.getInstance();
        DBHandler dublicat = DBHandler.getInstance();
        assertTrue(original.equals(dublicat));
    }

    @Test
    void createDB() throws SQLException, ClassNotFoundException {
        assertTrue(DBHandler.getInstance().createDB());
    }

    @Test
    void getAllStatistics() {
        int oldSize = DBHandler.getInstance().getAllStatistics().size();
        DBHandler.getInstance().addScore(new Statistic("TESTINDEBUG",99));
        DBHandler.getInstance().addScore(new Statistic("TESTINDEBUG2",99));
        assertEquals(2,DBHandler.getInstance().getAllStatistics().size() - oldSize);
        DBHandler.getInstance().deleteStatistic("TESTINDEBUG");
        DBHandler.getInstance().deleteStatistic("TESTINDEBUG2");
    }

    @Test
    void isAdd() {
        assertTrue(DBHandler.getInstance().isAdd("TESTINDEBUG"));

        DBHandler.getInstance().addScore(new Statistic("TESTINDEBUG",99));
        assertFalse(DBHandler.getInstance().isAdd("TESTINDEBUG"));

        DBHandler.getInstance().deleteStatistic("TESTINDEBUG");
    }

    @Test
    void addScore() {
        DBHandler.getInstance().addScore(new Statistic("TESTINDEBUG",99));
        assertEquals("TESTINDEBUG", DBHandler.getInstance().getElementByName("TESTINDEBUG").name);
        assertEquals(99,DBHandler.getInstance().getElementByName("TESTINDEBUG").score);
        DBHandler.getInstance().deleteStatistic("TESTINDEBUG");
    }

    @Test
    void deleteStatistic() {
        DBHandler.getInstance().addScore(new Statistic("TESTINDEBUG",99));
        DBHandler.getInstance().deleteStatistic("TESTINDEBUG");
        assertNull(DBHandler.getInstance().getElementByName("TESTINDEBUG"));
    }

}