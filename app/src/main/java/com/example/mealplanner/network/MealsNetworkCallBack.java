package com.example.mealplanner.network;

import com.example.mealplanner.model.Meal;

import java.util.ArrayList;

public interface MealsNetworkCallBack {

     void onSuccessResult(ArrayList<Meal> meals);
     void onFailureResult(String errorMsg);
}
