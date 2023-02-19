package com.example.mealplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AreaListResponse {
    @SerializedName("meals")
    private ArrayList<Meal> meals;

    public ArrayList<Meal> getMeal() {
        return this.meals;
    }

    public void setMeal(final ArrayList<Meal> meal) {
        this.meals = meal;
    }

}
