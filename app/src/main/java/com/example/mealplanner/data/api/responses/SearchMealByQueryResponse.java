package com.example.mealplanner.data.api.responses;

import com.example.mealplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class SearchMealByQueryResponse {
    private ArrayList<Meal> meals;

    public void setMeals(final ArrayList<Meal> meals) {
        this.meals = meals;
    }
    public ArrayList<Meal> getMeals() {
        return meals;
    }
}
