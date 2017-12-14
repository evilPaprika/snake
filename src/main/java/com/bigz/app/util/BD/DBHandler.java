package com.bigz.app.util.BD;

import com.bigz.app.util.PropertiesHandler;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.sqlite.JDBC;
import org.sqlite.SQLiteException;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DBHandler extends BaseDaoImpl<Statistic, String>{

    private static DBHandler instance = null;

    private static JdbcConnectionSource connectionSource = null;

    public static synchronized DBHandler getInstance() throws IOException, SQLException {
        if (instance == null)
                connectionSource = new JdbcConnectionSource(PropertiesHandler.getInstance().getProperty("db"));
                instance = new DBHandler();
                TableUtils.createTableIfNotExists(connectionSource,Statistic.class);
        return instance;
    }

    private DBHandler() throws SQLException {
        super(connectionSource, Statistic.class);

    }

    public void add(Statistic statistic) throws SQLException {
        List<Statistic> statistics = getAllStatisticWithOrder();
        create(statistic);
        if (statistics.size() > 10){
            this.delete(statistics.get(10));
        }

    }

    public List<Statistic> getAllStatisticWithOrder() throws SQLException {

        return queryBuilder().orderBy("SCORE",false).query();
    }

    public List<Statistic> getAllStatistic() throws SQLException {
        return queryForAll();
    }


}
