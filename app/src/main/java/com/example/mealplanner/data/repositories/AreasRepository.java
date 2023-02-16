package com.example.mealplanner.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.api.AreaManager;
import com.example.mealplanner.data.api.AreaResponse;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.Area;

import java.util.ArrayList;

import io.reactivex.Observable;

public class AreasRepository {

    private AreaManager areaManager;

    public AreasRepository(){
        areaManager = new AreaManager();
    }

    /**
     * @Returns a MutableLiveData that requires an observer to receive a response
     * wrapping the status and the actual response
     * */
    public MutableLiveData<DataLayerResponse<ArrayList<Area>>> getListOfAreas(){
        MutableLiveData<DataLayerResponse<ArrayList<Area>>> mutableListOfAreas =
                new MutableLiveData<>();

        DataLayerResponse<ArrayList<Area>> dataLayerResponse = new DataLayerResponse();

        Observable<AreaResponse> areaResponse = areaManager.getAllAreas();

        areaResponse.subscribe(
                e -> {
                    dataLayerResponse.setStatus(Status.SUCCESS);
                    dataLayerResponse.setWrappedResponse(e.getAreasList());
                    mutableListOfAreas.postValue(dataLayerResponse);
                },
                error -> {
                    dataLayerResponse.setStatus(Status.FAILURE);
                    error.printStackTrace();
                }
        );

        return mutableListOfAreas;
    }
}
