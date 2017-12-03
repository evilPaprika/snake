package app.util.BD;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DBHandler {

    private static final String CON_STR = "jdbc:sqlite:D:/myrecord.db";

    private static DBHandler instance = null;

    public static synchronized DBHandler getInstance() {
        if (instance == null)
            try {
                instance = new DBHandler();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return instance;
    }

    private Connection connection;

    private DBHandler() throws SQLException {

        DriverManager.registerDriver(new JDBC());

        this.connection = DriverManager.getConnection(CON_STR);
        try {
            createDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createDB() throws ClassNotFoundException, SQLException
    {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists 'Statistics' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'score' INT);");

        System.out.println("Таблица создана или уже существует.");
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

    public void deleteStatistic(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Statistic WHERE id = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
