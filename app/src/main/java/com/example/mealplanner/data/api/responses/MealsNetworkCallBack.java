package com.example.mealplanner.data.api.responses;

import com.example.mealplanner.model.Meal;

import java.util.ArrayList;

public interface MealsNetworkCallBack {

     void onSuccessResult(ArrayList<Meal> meals);
     void onFailureResult(String errorMsg);
}
