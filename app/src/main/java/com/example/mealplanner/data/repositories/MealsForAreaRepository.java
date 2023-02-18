package com.example.mealplanner.data.repositories;

import static androidx.fragment.app.FragmentManager.TAG;

import android.util.Log;

import com.example.mealplanner.model.Meal;
import com.example.mealplanner.network.RetrofitManager;
import com.example.mealplanner.presenters.contract.MealsForSpecificAreaContract;
import com.example.mealplanner.presenters.fragment.MealsForSpecificAreaPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MealsForAreaRepository {

    private static MealsForAreaRepository areaRepository = null;
    private MealsForSpecificAreaContract mealsForSpecificAreaContract;
    private MealsForSpecificAreaPresenter mealsForSpecificAreaPresenter;
    private List<Meal> meal = new ArrayList<>();
    private static final String TAG = "mealsForArea";
    private RetrofitManager retrofit;

    public MealsForAreaRepository(final MealsForSpecificAreaContract mealsForSpecificAreaContract) {
        this.mealsForSpecificAreaContract = mealsForSpecificAreaContract;
    }

    public void getAllMeals(String country) {
                 retrofit.getApi()
                 .getMealsOfSelectedArea(country)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        next -> {
                            meal = next.getMeal();
                        },
                        error -> {
                            Log.i(TAG, "onViewCreated: " + error.getMessage());
                        },
                        () -> {
                            mealsForSpecificAreaPresenter.onSuccessResult(meal);
                        }
                );
    }
}
