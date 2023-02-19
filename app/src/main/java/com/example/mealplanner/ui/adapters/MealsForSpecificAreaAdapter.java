package com.example.mealplanner.ui.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mealplanner.R;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.ui.fragments.CountriesFragmentDirections;
import com.example.mealplanner.ui.fragments.MealsForSpecificAreaFragment;
import com.example.mealplanner.ui.fragments.MealsForSpecificAreaFragmentDirections;

import java.util.ArrayList;

import io.grpc.Context;

public class MealsForSpecificAreaAdapter extends RecyclerView.Adapter<MealsForSpecificAreaAdapter.ViewHolder> {

    private ArrayList<Meal> meals;
    private Context context;
    private View view;


    public MealsForSpecificAreaAdapter(final ArrayList<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealsForSpecificAreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_specific_area,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealsForSpecificAreaAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        Glide.with(holder.itemView).load(meal.getImageUrl()).into(holder.image);
        holder.name.setText(meal.getName());

        holder.itemView.setOnClickListener((view1)->{
            NavDirections directions =
                    MealsForSpecificAreaFragmentDirections.actionMealsForSpecificAreaFragmentToDetailsScreenFragment(meals.get(position));
            Navigation.findNavController(view).navigate(directions);


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
