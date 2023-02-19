package com.example.mealplanner.data.datasource.meals.impl;

import android.util.Log;

import com.example.mealplanner.data.api.ApiService;
import com.example.mealplanner.data.api.RetrofitManager;
import com.example.mealplanner.model.AreaListResponse;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.network.NetworkCallBack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsForSpecificAreaRemoteRepo implements MealsForSpecificAreaService{

    private ApiService apiService;
    private Retrofit retrofit;

    private final String TAG = "Exception";

    public MealsForSpecificAreaRemoteRepo() {
        //retrofit = RetrofitManager.getRetrofitInstance();
        Gson gson = new GsonBuilder().setLenient().create();
         retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public void getAllMeals(String country, NetworkCallBack networkCallBack ) {

        Log.d(TAG, "getAllMeals: "+ country);
        apiService.getMealsOfSelectedArea(country).enqueue(new Callback<AreaListResponse>() {
            @Override
            public void onResponse(Call<AreaListResponse> call, Response<AreaListResponse> response) {
                AreaListResponse areaListResponse = response.body();
                networkCallBack.onSuccessResult(areaListResponse.getMeal());
                Log.d("mask", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<AreaListResponse> call, Throwable t) {
                networkCallBack.onFailureResult(t.getMessage());
                Log.d("mask", "onFailure: " + t.getMessage());
            }
        });
        
//        Observable<AreaListResponse> mealsObservable =
//                apiService.getMealsOfSelectedArea(country)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
    }
}
