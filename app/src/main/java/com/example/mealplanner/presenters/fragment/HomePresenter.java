package com.example.mealplanner.presenters.fragment;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.repositories.FavoriteMealsRepository;
import com.example.mealplanner.data.repositories.RandomMealsRepository;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.presenters.contract.FavoriteMealsPresenterContract;
import com.example.mealplanner.presenters.contract.HomePresenterContract;
import com.example.mealplanner.ui.activities.MainActivity;
import com.example.mealplanner.ui.contract.DatabaseDelegate;
import com.example.mealplanner.ui.fragments.state.HomeFragmentState;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Date;

public class HomePresenter implements HomePresenterContract {

    private RandomMealsRepository randomMealsRepository;
    private DatabaseDelegate databaseDelegate;
    private FavoriteMealsRepository favoriteMealsRepository;
    private FirebaseAuth mAuth;

    public HomePresenter(DatabaseDelegate databaseDelegate){
        this.databaseDelegate = databaseDelegate;
        randomMealsRepository = new RandomMealsRepository();
        favoriteMealsRepository = new FavoriteMealsRepository(databaseDelegate.getDatabaseAccess());
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public MutableLiveData<DataLayerResponse<ArrayList<Meal>>> getRandomMeals() {
        return randomMealsRepository.getRandomMeals();
    }
    @Override
    public  MutableLiveData<DataLayerResponse> addMealToLocalDatabase(Meal meal){
        return favoriteMealsRepository.insertMealIntoLocalDatabase(meal);
    }

    @Override
    public HomeFragmentState getHomeFragmentState() {
        return ((MainActivity)databaseDelegate).getHomeFragmentState();
    }
    @Override
    public MutableLiveData<DataLayerResponse> updateMealIsPlanned(String userId,String mealId,boolean isPlanned){
        return favoriteMealsRepository.updateMealIsPlanned(userId, mealId, isPlanned);
    }

    @Override
    public MutableLiveData<DataLayerResponse> updateMealIsFavorite(String userId,String mealId,boolean isFavorite){
        return favoriteMealsRepository.updateMealIsFavorite(userId, mealId, isFavorite);
    }

    public String getCurrentUserId(){
        return mAuth.getCurrentUser().getUid();
    }

    @Override
    public void updateMealDate(String userId, String mealId, Date date) {
        favoriteMealsRepository.updateMealDate(userId, mealId, date);
    }
}
