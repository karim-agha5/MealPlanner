package com.example.mealplanner.network;

import com.example.mealplanner.model.Meal;

import java.util.List;

public interface NetworkCallBack {

     void onSuccessResult(List<Meal>  meals);
     void onFailureResult(String errorMsg);
}
