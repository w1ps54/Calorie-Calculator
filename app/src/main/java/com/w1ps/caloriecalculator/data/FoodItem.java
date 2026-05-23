package com.w1ps.caloriecalculator.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "foods")
public class FoodItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int calories;

    public FoodItem(String name, int calories) {

        this.name = name;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }
}