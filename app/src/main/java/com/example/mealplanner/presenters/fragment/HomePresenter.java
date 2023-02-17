package com.example.mealplanner.presenters.fragment;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.repositories.RandomMealsRepository;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.presenters.contract.HomePresenterContract;

import java.util.ArrayList;

public class HomePresenter implements HomePresenterContract {

    private RandomMealsRepository randomMealsRepository;

    public HomePresenter(){
        randomMealsRepository = new RandomMealsRepository();
    }

    @Override
    public MutableLiveData<DataLayerResponse<ArrayList<Meal>>> getRandomMeals() {
        return randomMealsRepository.getRandomMeals();
    }
}
