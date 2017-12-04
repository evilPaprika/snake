package app.util.BD;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DBHandler {

    //вынести в настройки
    private static final String CON_STR = "jdbc:sqlite:D:/myrecord.db";

    private static DBHandler instance = null;
    private Connection connection;

    public static synchronized DBHandler getInstance() {
        if (instance == null)
            try {
                instance = new DBHandler();
            } catch (SQLException e) {
            //
                e.printStackTrace();
            }
        return instance;
    }

    private DBHandler() throws SQLException {

        DriverManager.registerDriver(new JDBC());

        this.connection = DriverManager.getConnection(CON_STR);
        createDB();
    }

    public boolean createDB() throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists 'Statistics' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'score' INT);");

        System.out.println("Таблица создана или уже существует.");
        return !statement.isClosed();
    }

    public Statistic getElementByName(String name){
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, score FROM Statistics WHERE name='"+name+"'");
            if(!resultSet.next()) return null;
            return new Statistic(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("score"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Statistic> getAllStatistics() {

        try (Statement statement = this.connection.createStatement()) {

            List<Statistic> statistics = new ArrayList<Statistic>();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, score FROM Statistics ORDER BY score DESC");
            while (resultSet.next()) {
                statistics.add(new Statistic(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("score")));
            }
            return statistics;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public boolean isAdd(String name){
        try (Statement statement = this.connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT id, name, score FROM Statistics WHERE name='" + name+"'");
            if (resultSet.next()){
                return false;
            }
        } catch (SQLException e) {
            return true;
        }
        return true;
    }

    public void addScore(Statistic statistic) {

        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Statistics(`name`, `score`) " +
                        "VALUES(?, ?)")) {
            statement.setObject(1, statistic.name);
            statement.setObject(2, statistic.score);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropDB(){
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DROP TABLE Statistics")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStatistic(String name) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Statistics WHERE name = ?")) {
            statement.setObject(1, name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDB() throws SQLException {
        connection.close();
    }
}
