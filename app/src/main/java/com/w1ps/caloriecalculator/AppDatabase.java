package com.w1ps.caloriecalculator;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {FoodItem.class},
        version = 1
)
public abstract class AppDatabase
        extends RoomDatabase {

    public abstract FoodDao foodDao();
}