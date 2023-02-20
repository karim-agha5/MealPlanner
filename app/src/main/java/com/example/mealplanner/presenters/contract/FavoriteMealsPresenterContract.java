package com.example.mealplanner.presenters.contract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.model.Meal;

import java.util.Date;
import java.util.List;

public interface FavoriteMealsPresenterContract {

     LiveData<Meal> getMeal(String mealId);
     LiveData<List<Meal>> getFavoriteMeals();
     LiveData<List<Meal>> getPlannedMeals();
     void deleteMeal(Meal meal);
     LiveData<List<Meal>> getUserMeals(String userId);
     MutableLiveData<DataLayerResponse> updateMealIsPlanned(String userId, String mealId, boolean isPlanned);
     MutableLiveData<DataLayerResponse> updateMealIsFavorite(String userId,String mealId,boolean isFavorite);

     void updateMealDate(String userId, String mealId, Date date);

}
