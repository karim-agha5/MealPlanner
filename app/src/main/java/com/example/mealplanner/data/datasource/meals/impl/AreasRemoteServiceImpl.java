package com.example.mealplanner.data.datasource.meals.impl;

import com.example.mealplanner.data.api.ApiService;
import com.example.mealplanner.data.api.RetrofitManager;
import com.example.mealplanner.data.api.responses.AreaResponse;
import com.example.mealplanner.data.datasource.meals.AreasRemoteService;
import com.example.mealplanner.model.Area;


import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/*
* TODO rename this class to AreaRemoteServiceImpl and make it implement the appropriate interface
*  This class should receive a Retrofit instance and an ApiService instance through the constructor
* */
public class AreasRemoteServiceImpl implements AreasRemoteService {

    private ApiService apiService;
    private Retrofit retrofit;
    private ArrayList<Area> listOfAreas;
    private ArrayList<Area> remoteListOfAreas;
    private final String TAG = "Exception";

    public AreasRemoteServiceImpl() {
        retrofit = RetrofitManager.getRetrofitInstance();
        apiService = retrofit.create(ApiService.class);
        listOfAreas = new ArrayList<>();
    }
    @Override
    public Observable<AreaResponse> getAllAreas(){
        Observable<AreaResponse> areaObservable =
                apiService.getAllArea()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

        return areaObservable;
    }
}
