package com.example.mealplanner.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.dbaccess.DatabaseAccess;
import com.example.mealplanner.data.datasource.meals.FavoriteMealsLocalService;
import com.example.mealplanner.data.datasource.meals.impl.FavoriteMealsLocalServiceImpl;
import com.example.mealplanner.model.Meal;

import java.util.Date;
import java.util.List;

public class FavoriteMealsRepository {

    private FavoriteMealsLocalService favoriteMealsLocalService;

    public FavoriteMealsRepository(DatabaseAccess databaseAccess){
        favoriteMealsLocalService = new FavoriteMealsLocalServiceImpl(databaseAccess);
    }

    public MutableLiveData<DataLayerResponse> insertMealIntoLocalDatabase(Meal meal){
        return favoriteMealsLocalService.addMealToLocalDatabase(meal);
    }

    public LiveData<Meal> getMeal(String mealId){
        return favoriteMealsLocalService.getMeal(mealId);
    }
    public LiveData<List<Meal>> getUserMeals(String userId){
        return favoriteMealsLocalService.getUserMeals(userId);
    }

    public MutableLiveData<DataLayerResponse> updateMealIsPlanned(String userId,String mealId,boolean isPlanned){
        return favoriteMealsLocalService.updateMealIsPlanned(userId, mealId, isPlanned);
    }

    public MutableLiveData<DataLayerResponse> updateMealIsFavorite(String userId,String mealId,boolean isFavorite){
        return favoriteMealsLocalService.updateMealIsFavorite(userId, mealId, isFavorite);
    }

    public void updateMealDate(String userId, String mealId, Date date){
        favoriteMealsLocalService.updateMealDate(userId, mealId, date);
    }

    public LiveData<List<Meal>> getFavoriteMeals(){
        return favoriteMealsLocalService.getFavoritetMeals();
    }

    public LiveData<List<Meal>> getPlannedMeals(){
        return favoriteMealsLocalService.getPlannedMeals();
    }

    public void deleteMeal(Meal meal){
        favoriteMealsLocalService.deleteMeal(meal);
    }
}
