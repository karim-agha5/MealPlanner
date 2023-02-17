package com.example.mealplanner.data.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.api.responses.RandomMealsResponse;
import com.example.mealplanner.data.datasource.meals.RandomMealsRemoteService;
import com.example.mealplanner.data.datasource.meals.impl.RandomMealsRemoteServiceImpl;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.Meal;

import java.util.ArrayList;

import io.reactivex.Observable;

public class RandomMealsRepository {

    private RandomMealsRemoteService randomMealsRemoteService;
    private final String TAG = "Exception";

    public RandomMealsRepository(){
        randomMealsRemoteService = new RandomMealsRemoteServiceImpl();
    }

    public MutableLiveData<DataLayerResponse<ArrayList<Meal>>> getRandomMeals(){
        MutableLiveData<DataLayerResponse<ArrayList<Meal>>> response =
                new MutableLiveData<>();

        DataLayerResponse<ArrayList<Meal>> dataLayerResponse = new DataLayerResponse<>();

        Observable<RandomMealsResponse> randomMealsResponseObservable =
                randomMealsRemoteService.getRandomMeals();
        randomMealsResponseObservable.subscribe(
                e -> {
                    dataLayerResponse.setStatus(Status.SUCCESS);
                    dataLayerResponse.setWrappedResponse(e.getListOfRandomMeals());
                    response.postValue(dataLayerResponse);
                },
                error -> {
                    dataLayerResponse.setStatus(Status.FAILURE);
                    dataLayerResponse.setMessage("Unable to retrieve meals for you.");
                    response.postValue(dataLayerResponse);
                }
        );

        return response;
    }
}
