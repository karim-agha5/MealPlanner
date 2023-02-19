package com.example.mealplanner.presenters.fragment;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.repositories.MealsForAreaRepository;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.network.NetworkCallBack;
import com.example.mealplanner.presenters.contract.MealsForSpecificAreaContract;
import com.example.mealplanner.ui.fragments.MealsForSpecificAreaFragment;

import java.util.ArrayList;
import java.util.List;

public class MealsForSpecificAreaPresenter implements MealsForSpecificAreaContract{

    private MealsForAreaRepository mealsForAreaRepository;
    private MealsForSpecificAreaFragment fragment;
    private String country;

    public MealsForSpecificAreaPresenter(MealsForSpecificAreaFragment mealsForSpecificAreaFragment) {
        fragment  = mealsForSpecificAreaFragment;
        mealsForAreaRepository = new MealsForAreaRepository();
    }



    @Override
    public void getAllMeals(String country) {

         mealsForAreaRepository.getAllMeals(country, new NetworkCallBack() {
            @Override
            public void onSuccessResult(ArrayList<Meal> meals) {
                fragment.showData(meals);
            }

            @Override
            public void onFailureResult(String errorMsg) {

            }
        });
    }
}
