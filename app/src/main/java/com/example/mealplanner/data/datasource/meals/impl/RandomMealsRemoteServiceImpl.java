package com.example.mealplanner.data.datasource.meals.impl;

import com.example.mealplanner.data.api.ApiService;
import com.example.mealplanner.data.api.RetrofitManager;
import com.example.mealplanner.data.api.responses.RandomMealsResponse;
import com.example.mealplanner.data.datasource.meals.RandomMealsRemoteService;
import com.example.mealplanner.model.Area;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/*
 * TODO rename this class to RandomMealsRemoteServiceImpl and make it implement the appropriate interface
 *  This class should receive a Retrofit instance and an ApiService instance through the constructor
 * */

public class RandomMealsRemoteServiceImpl implements RandomMealsRemoteService {

    private ApiService apiService;
    private Retrofit retrofit;
    private ArrayList<Area> listOfRandomMeals;
    private final String TAG = "Exception";

    public RandomMealsRemoteServiceImpl() {
        retrofit = RetrofitManager.getRetrofitInstance();
        apiService = retrofit.create(ApiService.class);
        listOfRandomMeals = new ArrayList<>();
    }

    @Override
    public Observable<RandomMealsResponse> getRandomMeals(){
        Observable<RandomMealsResponse> randomMealsResponseObservable =
                apiService.getRandomMeals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

        return randomMealsResponseObservable;
    }

}
