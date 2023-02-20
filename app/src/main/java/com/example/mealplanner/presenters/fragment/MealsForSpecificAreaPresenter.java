package com.example.mealplanner.presenters.fragment;

import com.example.mealplanner.data.repositories.MealsForAreaRepository;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.network.MealsNetworkCallBack;
import com.example.mealplanner.presenters.contract.MealsForSpecificAreaContract;
import com.example.mealplanner.ui.fragments.MealsForSpecificAreaFragment;

import java.util.ArrayList;

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

         mealsForAreaRepository.getAllMeals(country, new MealsNetworkCallBack() {
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
