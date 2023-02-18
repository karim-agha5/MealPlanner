package com.example.mealplanner.presenters.contract;

import com.example.mealplanner.model.Meal;

public interface MealsForSpecificAreaContract {
     void getAllMeal(String country);
     void addToFavorite(Meal meals);


}
