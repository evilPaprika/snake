package app.util.BD;

public class Statistic {

    public int id;

    public String name;

    public int score;

    public Statistic(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Имя: %s | Результат: %s",
                this.id, this.name, this.score);
    }
}
