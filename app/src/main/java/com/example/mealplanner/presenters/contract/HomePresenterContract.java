package com.example.mealplanner.presenters.contract;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.ui.fragments.state.HomeFragmentState;

import java.util.ArrayList;
import java.util.Date;

public interface HomePresenterContract {
    MutableLiveData<DataLayerResponse<ArrayList<Meal>>> getRandomMeals();
    HomeFragmentState getHomeFragmentState();
    MutableLiveData<DataLayerResponse> addMealToLocalDatabase(Meal meal);
    String getCurrentUserId();
    void updateMealDate(String userId, String mealId, Date date);
    MutableLiveData<DataLayerResponse> updateMealIsPlanned(String userId,String mealId,boolean isPlanned);
    MutableLiveData<DataLayerResponse> updateMealIsFavorite(String userId,String mealId,boolean isFavorite);
}
