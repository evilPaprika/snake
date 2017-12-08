package com.bigz.app.util.BD;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "Statistics")
public class Statistic implements Serializable {

    @DatabaseField(id = true, columnName = "NAME", canBeNull = false)
    public String name;

    @DatabaseField(columnName = "SCORE", canBeNull = false)
    public int score;

    public Statistic(){
        this.name = "Default";
        this.score = 0;
    }

    public Statistic(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("Имя: %s | Результат: %s",
                this.name, this.score);
    }
}
