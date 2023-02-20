package com.example.mealplanner.data.datasource.meals.impl;

import com.example.mealplanner.data.api.responses.RandomMealsResponse;
import com.example.mealplanner.model.AreaListResponse;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.network.MealsNetworkCallBack;

import io.reactivex.Observable;

public interface MealsForSpecificAreaService {

    void getAllMeals(String country, MealsNetworkCallBack networkCallBack);
}
