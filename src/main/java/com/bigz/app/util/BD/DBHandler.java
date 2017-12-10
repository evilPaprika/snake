package com.bigz.app.util.BD;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.sqlite.JDBC;
import org.sqlite.SQLiteException;

import java.sql.*;
import java.util.*;

public class DBHandler extends BaseDaoImpl<Statistic, String>{

    //вынести в настройки
    private static final String CON_STR = "jdbc:sqlite:D:/myrecord.db";

    private static DBHandler instance = null;

    private static JdbcConnectionSource connectionSource = null;

    public static synchronized DBHandler getInstance() {
        if (instance == null)
            try {
                connectionSource = new JdbcConnectionSource(CON_STR);
                instance = new DBHandler();
                TableUtils.createTableIfNotExists(connectionSource,Statistic.class);

            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        return instance;
    }

    private DBHandler() throws SQLException {
        super(connectionSource, Statistic.class);

    }

    public void add(Statistic statistic) throws SQLException {
        System.out.print(this.countOf());
    }

    public List<Statistic> getAllStatisticWithOrder() throws SQLException {

        return queryBuilder().orderBy("SCORE",false).query();
    }

    public List<Statistic> getAllStatistic() throws SQLException {
        return queryForAll();
    }

    public Statistic getElementByName(String name) throws SQLException {
        return queryForId(name);
    }

}
