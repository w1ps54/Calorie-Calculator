package com.w1ps.caloriecalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.w1ps.caloriecalculator.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding binding;

    public static final String PREFS = "user_data";

    public static final String KEY_LIMIT =
            "calorie_limit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =
                ActivityStartBinding.inflate(
                        getLayoutInflater()
                );

        setContentView(binding.getRoot());

        binding.btnContinue.setOnClickListener(
                v -> calculate()
        );
    }

    private void calculate() {

        String weightText =
                binding.etWeight.getText()
                        .toString()
                        .trim();

        String heightText =
                binding.etHeight.getText()
                        .toString()
                        .trim();

        String ageText =
                binding.etAge.getText()
                        .toString()
                        .trim();

        if (TextUtils.isEmpty(weightText)) {

            binding.etWeight.setError(
                    "Введите вес"
            );

            return;
        }

        if (TextUtils.isEmpty(heightText)) {

            binding.etHeight.setError(
                    "Введите рост"
            );

            return;
        }

        if (TextUtils.isEmpty(ageText)) {

            binding.etAge.setError(
                    "Введите возраст"
            );

            return;
        }

        double weight;
        double height;
        double age;

        try {

            weight =
                    Double.parseDouble(weightText);

            height =
                    Double.parseDouble(heightText);

            age =
                    Double.parseDouble(ageText);

        } catch (NumberFormatException e) {

            binding.etWeight.setError(
                    "Некорректные данные"
            );

            return;
        }

        double calories =
                88.362 +
                        (13.397 * weight) +
                        (4.799 * height) -
                        (5.677 * age);

        int calorieLimit = (int) calories;

        SharedPreferences preferences =
                getSharedPreferences(
                        PREFS,
                        MODE_PRIVATE
                );

        preferences.edit()
                .putInt(KEY_LIMIT, calorieLimit)
                .apply();

        startActivity(
                new Intent(this, MainActivity.class)
        );

        finish();
    }
}