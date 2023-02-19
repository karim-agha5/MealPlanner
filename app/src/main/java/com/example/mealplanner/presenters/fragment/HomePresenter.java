package com.example.mealplanner.presenters.fragment;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.repositories.RandomMealsRepository;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.presenters.contract.HomePresenterContract;
import com.example.mealplanner.ui.activities.MainActivity;
import com.example.mealplanner.ui.fragments.state.HomeFragmentState;

import java.util.ArrayList;

public class HomePresenter implements HomePresenterContract {

    private MainActivity activity;
    private RandomMealsRepository randomMealsRepository;

    public HomePresenter(MainActivity activity){
        this.activity = activity;
        randomMealsRepository = new RandomMealsRepository();
    }

    @Override
    public MutableLiveData<DataLayerResponse<ArrayList<Meal>>> getRandomMeals() {
        return randomMealsRepository.getRandomMeals();
    }

    @Override
    public HomeFragmentState getHomeFragmentState() {
        return activity.getHomeFragmentState();
    }
}
