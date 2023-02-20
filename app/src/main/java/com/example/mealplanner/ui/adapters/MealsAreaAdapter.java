package com.example.mealplanner.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.example.mealplanner.R;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.ui.fragments.MealsForSpecificAreaFragmentDirections;

import java.util.ArrayList;

import io.grpc.Context;

public class MealsAreaAdapter extends RecyclerView.Adapter<MealsAreaAdapter.ViewHolder> {

    private ArrayList<Meal> meals;
    private Context context;
    private View view;


    public MealsAreaAdapter(final ArrayList<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealsAreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_specific_area,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealsAreaAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        Glide.with(holder.itemView).load(meal.getImageUrl()).into(holder.image);
        holder.name.setText(meal.getName());

        holder.itemView.setOnClickListener((view1)->{
            NavDirections directions = MealsForSpecificAreaFragmentDirections
                            .actionMealsForSpecificAreaFragmentToDetailsScreenFragment(meal);
            Navigation.findNavController(holder.itemView).navigate(directions);
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.area_meal);
            image = itemView.findViewById(R.id.meal_image);
        }
    }
}
