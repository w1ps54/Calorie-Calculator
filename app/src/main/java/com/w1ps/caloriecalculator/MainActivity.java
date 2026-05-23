package com.w1ps.caloriecalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.w1ps.caloriecalculator.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private final List<FoodItem> foodList =
            new ArrayList<>();

    private FoodAdapter adapter;

    private AppDatabase database;

    private int calorieLimit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =
                ActivityMainBinding.inflate(
                        getLayoutInflater()
                );

        setContentView(binding.getRoot());

        database = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "food_database"
                )
                .allowMainThreadQueries()
                .build();

        loadCalorieLimit();

        loadFoods();

        setupRecyclerView();

        updateTotalCalories();

        binding.btnAdd.setOnClickListener(
                v -> addFood()
        );
    }

    private void setupRecyclerView() {

        adapter = new FoodAdapter(
                foodList,
                item -> {

                    database.foodDao().delete(item);

                    foodList.remove(item);

                    adapter.notifyDataSetChanged();

                    updateTotalCalories();
                }
        );

        binding.recyclerViewFoods.setLayoutManager(
                new LinearLayoutManager(this)
        );

        binding.recyclerViewFoods.setAdapter(adapter);
    }

    private void addFood() {

        String name =
                binding.etFoodName.getText()
                        .toString()
                        .trim();

        String caloriesText =
                binding.etCalories.getText()
                        .toString()
                        .trim();

        if (TextUtils.isEmpty(name)) {

            binding.etFoodName.setError(
                    "Введите название"
            );

            return;
        }

        if (TextUtils.isEmpty(caloriesText)) {

            binding.etCalories.setError(
                    "Введите калории"
            );

            return;
        }

        int calories;

        try {

            calories =
                    Integer.parseInt(caloriesText);

        } catch (NumberFormatException e) {

            binding.etCalories.setError(
                    "Некорректное число"
            );

            return;
        }

        FoodItem item =
                new FoodItem(name, calories);

        database.foodDao().insert(item);

        foodList.add(item);

        adapter.notifyItemInserted(
                foodList.size() - 1
        );

        updateTotalCalories();

        clearInputs();
    }

    private void loadFoods() {

        foodList.clear();

        foodList.addAll(
                database.foodDao().getAllFoods()
        );
    }

    private void updateTotalCalories() {

        int total = 0;

        for (FoodItem item : foodList) {
            total += item.getCalories();
        }

        int remaining =
                calorieLimit - total;

        binding.tvLimitCalories.setText(
                "Лимит: " +
                        calorieLimit +
                        " ккал"
        );

        binding.tvTotalCalories.setText(
                "Съедено: " +
                        total +
                        " ккал"
        );

        binding.tvRemainingCalories.setText(
                "Осталось: " +
                        remaining +
                        " ккал"
        );
    }

    private void clearInputs() {

        binding.etFoodName.setText("");

        binding.etCalories.setText("");

        binding.etFoodName.requestFocus();
    }

    private void loadCalorieLimit() {

        SharedPreferences preferences =
                getSharedPreferences(
                        StartActivity.PREFS,
                        MODE_PRIVATE
                );

        calorieLimit =
                preferences.getInt(
                        StartActivity.KEY_LIMIT,
                        0
                );
    }
}