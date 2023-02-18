package com.example.mealplanner.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.example.mealplanner.R;
import com.example.mealplanner.model.Meal;

import java.util.List;

import io.grpc.Context;

public class MealsForSpecificAreaAdapter extends RecyclerView.Adapter<MealsForSpecificAreaAdapter.ViewHolder> {

    private RecyclerView recyclerView;
    private List<Meal> meal;
    private LinearLayoutManager layoutManager;
    private Context context;



    public MealsForSpecificAreaAdapter( List<Meal> _meal){
        this.meal =  _meal;
    }
    @NonNull
    @Override
    public MealsForSpecificAreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_specific_area,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealsForSpecificAreaAdapter.ViewHolder holder, int position) {
        Meal meals = meal.get(position);
        Glide.with(holder.itemView).load(meals.getImageUrl()).into(holder.image);
        holder.name.setText(meals.getName());

    }

    @Override
    public int getItemCount() {
        return meal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;
        RelativeLayout layout;

        TextView area;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.area_meal);
            image = itemView.findViewById(R.id.meal_image);
            layout = itemView.findViewById(R.id.recycleViewArea);
            area = itemView.findViewById(R.id.areaTxt);
        }
    }
}
