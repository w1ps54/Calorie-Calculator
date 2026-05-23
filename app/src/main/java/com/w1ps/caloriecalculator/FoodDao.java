package com.w1ps.caloriecalculator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodDao {

    @Insert
    void insert(FoodItem foodItem);

    @Delete
    void delete(FoodItem foodItem);

    @Query("SELECT * FROM foods")
    List<FoodItem> getAllFoods();
}