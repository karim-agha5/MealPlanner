package com.example.mealplanner.data.api;

import android.util.Log;

import com.example.mealplanner.model.Area;


import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/*
* TODO rename this class to AreaRemoteServiceImpl and make it implement the appropriate interface
* */
public class AreaManager {

    private ApiService apiService;
    private Retrofit retrofit;
    private ArrayList<Area> listOfAreas;
    private ArrayList<Area> remoteListOfAreas;
    private final String TAG = "Exception";

    public AreaManager() {
        retrofit = RetrofitManager.getRetrofitInstance();
        apiService = retrofit.create(ApiService.class);
        listOfAreas = new ArrayList<>();
    }

    public Observable<AreaResponse> getAllAreas(){
        Observable<AreaResponse> areaObservable =
                apiService.getAllArea()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

        return areaObservable;
    }
}
