package com.example.mealplanner.network;

import com.example.mealplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface NetworkCallBack {

     void onSuccessResult(ArrayList<Meal> meals);
     void onFailureResult(String errorMsg);
}
