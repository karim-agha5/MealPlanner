package com.example.mealplanner.presenters.contract;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.model.Meal;

import java.util.ArrayList;

public interface HomePresenterContract {
    MutableLiveData<DataLayerResponse<ArrayList<Meal>>> getRandomMeals();
}