package com.w1ps.caloriecalculator.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.w1ps.caloriecalculator.databinding.ItemFoodBinding;
import com.w1ps.caloriecalculator.data.FoodItem;

import java.util.List;

public class FoodAdapter
        extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    public interface OnDeleteClickListener {
        void onDelete(FoodItem item);
    }

    private final List<FoodItem> foodList;

    private final OnDeleteClickListener listener;

    public FoodAdapter(
            List<FoodItem> foodList,
            OnDeleteClickListener listener
    ) {

        this.foodList = foodList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        ItemFoodBinding binding =
                ItemFoodBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                );

        return new FoodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(
            @NonNull FoodViewHolder holder,
            int position
    ) {

        FoodItem item = foodList.get(position);

        holder.binding.tvFoodName.setText(item.getName());

        holder.binding.tvCalories.setText(
                item.getCalories() + " ккал"
        );

        holder.binding.btnDelete.setOnClickListener(v -> {
            listener.onDelete(item);
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder
            extends RecyclerView.ViewHolder {

        ItemFoodBinding binding;

        public FoodViewHolder(ItemFoodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}