package com.example.mealplanner.data.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.api.responses.RandomMealsResponse;
import com.example.mealplanner.data.datasource.meals.RandomMealsRemoteService;
import com.example.mealplanner.data.datasource.meals.impl.MealsForSpecificAreaRemoteRepo;
import com.example.mealplanner.data.datasource.meals.impl.MealsForSpecificAreaService;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.AreaListResponse;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.network.NetworkCallBack;
import com.example.mealplanner.presenters.contract.MealsForSpecificAreaContract;
import com.example.mealplanner.presenters.fragment.MealsForSpecificAreaPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MealsForAreaRepository {

    private MealsForSpecificAreaRemoteRepo remoteRepo;

    private final String TAG = "Exception";

    public MealsForAreaRepository() {
        remoteRepo = new MealsForSpecificAreaRemoteRepo();
    }


    public void getAllMeals(String country,  NetworkCallBack networkCallBack ){
        remoteRepo.getAllMeals(country, networkCallBack);

//        MutableLiveData<DataLayerResponse<ArrayList<Meal>>> response =
//                new MutableLiveData<>();
//
//        DataLayerResponse<ArrayList<Meal>> dataLayerResponse = new DataLayerResponse<>();
//
//        Observable<AreaListResponse> areaMealsResponseObservable =
//                mealsForSpecificAreaService.getAllMeals();
//        areaMealsResponseObservable.subscribe(
//                e -> {
//                    dataLayerResponse.setStatus(Status.SUCCESS);
//                    dataLayerResponse.setWrappedResponse(e.getMeal());
//                    response.postValue(dataLayerResponse);
//                    Log.i(TAG, "getAllMeals: "+e.getMeal().get(0).getName());
//                },
//                error -> {
//                    dataLayerResponse.setStatus(Status.FAILURE);
//                    dataLayerResponse.setMessage("Unable to retrieve meals for you.");
//                    response.postValue(dataLayerResponse);
//                }
//        );
//
//        return response;
    }
}
