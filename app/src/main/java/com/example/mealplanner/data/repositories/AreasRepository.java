package com.example.mealplanner.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.meals.AreasRemoteService;
import com.example.mealplanner.data.datasource.meals.impl.AreasRemoteServiceImpl;
import com.example.mealplanner.data.api.responses.AreaResponse;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.Area;

import java.util.ArrayList;

import io.reactivex.Observable;

public class AreasRepository {

    private AreasRemoteService areasRemoteService;

    public AreasRepository(){
        areasRemoteService = new AreasRemoteServiceImpl();
    }

    /**
     * @Returns a MutableLiveData that requires an observer to receive a response
     * wrapping the status and the actual response
     * */
    public MutableLiveData<DataLayerResponse<ArrayList<Area>>> getListOfAreas(){
        MutableLiveData<DataLayerResponse<ArrayList<Area>>> mutableListOfAreas =
                new MutableLiveData<>();

        DataLayerResponse<ArrayList<Area>> dataLayerResponse = new DataLayerResponse();

        Observable<AreaResponse> areaResponse = areasRemoteService.getAllAreas();

        areaResponse.subscribe(
                e -> {
                    dataLayerResponse.setStatus(Status.SUCCESS);
                    dataLayerResponse.setWrappedResponse(e.getAreasList());
                    mutableListOfAreas.postValue(dataLayerResponse);
                },
                error -> {
                    dataLayerResponse.setStatus(Status.FAILURE);
                    dataLayerResponse.setMessage("Unable to retrieve the areas");
                    error.printStackTrace();
                }
        );

        return mutableListOfAreas;
    }
}
