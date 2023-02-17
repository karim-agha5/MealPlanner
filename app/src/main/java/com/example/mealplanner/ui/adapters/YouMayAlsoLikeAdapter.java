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
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.mealplanner.R;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.ui.fragments.HomeFragment;

import java.util.ArrayList;

public class YouMayAlsoLikeAdapter extends RecyclerView.Adapter<YouMayAlsoLikeAdapter.CustomViewHolder>{


    private Context context;
    private ArrayList<Meal> listOfMeals;

    public YouMayAlsoLikeAdapter(Context context,ArrayList<Meal> listOfMeals){
        this.context = context;
        this.listOfMeals = listOfMeals;
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder{

        ImageView ivMealImagePreview;
        TextView tvYouMayAlsoLikeMealName;
        ImageButton ibMoreVert;
        ContentLoadingProgressBar imageLoadingProgressBar;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMealImagePreview = itemView.findViewById(R.id.iv_meal_image_preview);
            tvYouMayAlsoLikeMealName = itemView.findViewById(R.id.tv_you_may_also_like_meal_name);
            ibMoreVert = itemView.findViewById(R.id.ib_more_vert);
            imageLoadingProgressBar = itemView.findViewById(R.id.image_loading_progress_bar);
        }
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new CustomViewHolder(layoutInflater.inflate(R.layout.you_may_also_like_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvYouMayAlsoLikeMealName.setText(String.valueOf(listOfMeals.get(position).getName()));
        handleImageView(holder,position);
    }

    @Override
    public int getItemCount() {
        return listOfMeals.size();
    }

    private void handleImageView(CustomViewHolder holder,int position){

        Glide.with(context)
                .load(listOfMeals.get(position).getImageUrl())
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.imageLoadingProgressBar.setVisibility(View.GONE);
                        holder.ivMealImagePreview.setVisibility(View.VISIBLE);
                        holder.ivMealImagePreview.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }
}
