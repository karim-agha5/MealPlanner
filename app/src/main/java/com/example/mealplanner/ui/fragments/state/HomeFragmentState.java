package com.example.mealplanner.ui.fragments.state;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.model.Meal;
import com.example.mealplanner.ui.adapters.YouMayAlsoLikeAdapter;

import java.util.ArrayList;

public class HomeFragmentState{

    private ImageView ivMealImage;
    private TextView tvMealName;
    private TextView tvMealArea;
    private ArrayList<Meal> randomMealsList;
    private Drawable[] savedImages;
    private boolean hasSavedState;

    public HomeFragmentState(){savedImages = new Drawable[11];}


    public HomeFragmentState getHomeFragmentSavedState(){
        return this;
    }

    public boolean hasSavedState(){
        return hasSavedState;
    }

    public void setHasSavedState(final boolean hasSavedState) {
        this.hasSavedState = hasSavedState;
    }

    public ImageView getIvMealImage() {
        return this.ivMealImage;
    }

    public void setIvMealImage(final ImageView ivMealImage) {
        this.ivMealImage = ivMealImage != this.ivMealImage ? ivMealImage : this.ivMealImage;
    }

    public TextView getTvMealName() {
        return this.tvMealName;
    }

    public void setTvMealName(final TextView tvMealName) {
        this.tvMealName = tvMealName != this.tvMealName ? tvMealName : this.tvMealName;
    }

    public TextView getTvMealArea() {
        return this.tvMealArea;
    }

    public void setTvMealArea(final TextView tvMealArea) {
        this.tvMealArea = tvMealArea != this.tvMealArea ? tvMealArea : this.tvMealArea;
    }

    public ArrayList<Meal> getRandomMealsList() {
        Log.i("Exception", "getter -> " + this.randomMealsList.hashCode());

        return new ArrayList<>(this.randomMealsList);
    }

    public void setRandomMealsList(final ArrayList<Meal> randomMealsList) {
        this.randomMealsList = new ArrayList<>(randomMealsList);
    }

    public Drawable[] getSavedImages() {
        return this.savedImages;
    }

    public void setSavedImages(final Drawable[] savedImages) {
        this.savedImages = savedImages;
    }

}
