package app.util.BD;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DBHandler {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:D:/myrecord.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DBHandler
    private static DBHandler instance = null;

    public static synchronized DBHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DBHandler();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private DBHandler() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
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

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Statistic> statistics = new ArrayList<Statistic>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT id, name, score FROM Statistics ORDER BY score DESC");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                statistics.add(new Statistic(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("score")));
            }
            // Возвращаем наш список
            return statistics;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    // Добавление продукта в БД
    public void addScore(Statistic statistic) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Statistics(`name`, `score`) " +
                        "VALUES(?, ?)")) {
            statement.setObject(1, statistic.name);
            statement.setObject(2, statistic.score);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление продукта по id
    public void deleteProduct(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Statistic WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
