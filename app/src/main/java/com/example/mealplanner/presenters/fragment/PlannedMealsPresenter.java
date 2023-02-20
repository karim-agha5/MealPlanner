package com.example.mealplanner.presenters.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.repositories.FavoriteMealsRepository;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.presenters.contract.PlannedMealsPresenterContract;
import com.example.mealplanner.ui.contract.DatabaseDelegate;

import java.util.Date;
import java.util.List;

public class PlannedMealsPresenter implements PlannedMealsPresenterContract {

    private FavoriteMealsRepository favoriteMealsRepository;

    public PlannedMealsPresenter(DatabaseDelegate databaseDelegate){
        favoriteMealsRepository = new FavoriteMealsRepository(databaseDelegate.getDatabaseAccess());
    }

    @Override
    public LiveData<List<Meal>> getUserMeals(String userId){
        return favoriteMealsRepository.getUserMeals(userId);
    }

    @Override
    public MutableLiveData<DataLayerResponse> updateMealIsPlanned(String userId, String mealId, boolean isPlanned) {
        return favoriteMealsRepository.updateMealIsPlanned(userId, mealId, isPlanned);
    }

    @Override
    public MutableLiveData<DataLayerResponse> updateMealIsFavorite(String userId, String mealId, boolean isFavorite) {
        return favoriteMealsRepository.updateMealIsFavorite(userId, mealId, isFavorite);
    }

    @Override
    public void updateMealDate(String userId, String mealId, Date date) {
        favoriteMealsRepository.updateMealDate(userId, mealId, date);
    }

    @Override
    public LiveData<Meal> getMeal(String mealId){
        return favoriteMealsRepository.getMeal(mealId);
    }

    @Override
    public LiveData<List<Meal>> getFavoriteMeals() {
        return favoriteMealsRepository.getFavoriteMeals();
    }

    @Override
    public LiveData<List<Meal>> getPlannedMeals() {
        return favoriteMealsRepository.getPlannedMeals();
    }

    @Override
    public void deleteMeal(final Meal meal) {
        favoriteMealsRepository.deleteMeal(meal);
    }
}
