package com.example.mealplanner.data.api.responses;

import com.example.mealplanner.model.Meal;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RandomMealsResponse {

    @SerializedName("meals")
    private ArrayList<Meal> listOfRandomMeals;

    public RandomMealsResponse(){}

    public RandomMealsResponse(final ArrayList<Meal> listOfRandomMeals) {
        this.listOfRandomMeals = listOfRandomMeals;
    }

    public ArrayList<Meal> getListOfRandomMeals() {
        return this.listOfRandomMeals;
    }

    public void setListOfRandomMeals(final ArrayList<Meal> listOfRandomMeals) {
        this.listOfRandomMeals = listOfRandomMeals;
    }
}
