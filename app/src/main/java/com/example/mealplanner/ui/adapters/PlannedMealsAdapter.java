package com.example.mealplanner.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mealplanner.R;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.presenters.contract.FavoriteMealsPresenterContract;
import com.example.mealplanner.presenters.contract.PlannedMealsPresenterContract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlannedMealsAdapter extends RecyclerView.Adapter<PlannedMealsAdapter.CustomViewHolder>{

    private Context context;
    private List<Meal> plannedMealsList;
    private PlannedMealsPresenterContract plannedMealsPresenterContract;

    private final String TAG = "Exception";



    public PlannedMealsAdapter(Context context, List<Meal> plannedMealsList,
                                PlannedMealsPresenterContract plannedMealsPresenterContract){
        this.context = context;
        this.plannedMealsList = plannedMealsList;
        this.plannedMealsPresenterContract = plannedMealsPresenterContract;
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPlannedMealImagePreview;
        TextView tvPlannedMealName;
        TextView tvPlannedMealAreaName;
        TextView tvDate;
        ContentLoadingProgressBar imageLoadingProgressBar;
        ImageButton ibPlannedMealMoreVert;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPlannedMealImagePreview = itemView.findViewById(R.id.iv_planned_meal_preview);
            tvPlannedMealName = itemView.findViewById(R.id.tv_planned_meal_name);
            tvPlannedMealAreaName = itemView.findViewById(R.id.tv_planned_meal_area);
            imageLoadingProgressBar = itemView.findViewById(R.id.image_loading_progress_bar);
            tvDate = itemView.findViewById(R.id.tv_date);
            //ibPlannedMealMoreVert = itemView.findViewById(R.id.ib_planned_meal_more_vert);
        }
    }



    @NonNull
    @Override
    public PlannedMealsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new PlannedMealsAdapter.CustomViewHolder(layoutInflater.inflate(R.layout.planned_meal_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlannedMealsAdapter.CustomViewHolder holder, int position) {
        holder.tvPlannedMealName.setText(String.valueOf(plannedMealsList.get(position).getName()));
        holder.tvPlannedMealAreaName.setText(String.valueOf(plannedMealsList.get(position).getArea()));

        Calendar calendar = Calendar.getInstance();
        Date date =  plannedMealsList.get(position).getDate();
        calendar.setTime(date);
        DateFormat monthFormat = new SimpleDateFormat("MMM");
        String month = monthFormat.format(date);


        holder.tvDate.setText(String.valueOf(month + ", " + calendar.get(Calendar.DAY_OF_MONTH)));

        Glide.with(context)
                .load(plannedMealsList.get(position).getImageUrl())
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.imageLoadingProgressBar.setVisibility(View.GONE);
                        holder.ivPlannedMealImagePreview.setVisibility(View.VISIBLE);
                        holder.ivPlannedMealImagePreview.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });



    }

    @Override
    public int getItemCount() {
        return plannedMealsList.size();
    }
}
