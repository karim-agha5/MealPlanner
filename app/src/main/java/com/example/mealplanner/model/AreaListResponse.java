package com.example.mealplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreaListResponse {
    @SerializedName("meal")
    private List<Meal> meal;

    public List<Meal> getMeal() {
        return this.meal;
    }

    public void setMeal(final List<Meal> meal) {
        this.meal = meal;
    }

}
