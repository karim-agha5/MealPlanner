package com.example.mealplanner.model;
import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Area {
    @SerializedName("strArea")
    private String name;
    //private ArrayList<Bitmap> imagesList;
    private int image;
    private ArrayList<Meal> mealsList;

    public Area(){}

    public Area(final String name, final int image, final ArrayList<Meal> mealsList) {
        this.name = name;
       // this.imagesList = imagesList;
        this.image = image;
        this.mealsList = mealsList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

   /* public ArrayList<Bitmap> getImagesList() {
        return this.imagesList;
    }

    public void setImagesList(final ArrayList<Bitmap> imagesList) {
        this.imagesList = imagesList;
    }
*/

    public int getImage() {
        return this.image;
    }

    public void setImage(final int image) {
        this.image = image;
    }

    public ArrayList<Meal> getMealsList() {
        return this.mealsList;
    }

    public void setMealsList(final ArrayList<Meal> mealsList) {
        this.mealsList = mealsList;

    }
}
