package com.example.mealplanner.presenters.fragment;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.repositories.AreasRepository;
import com.example.mealplanner.model.Area;
import com.example.mealplanner.presenters.contract.AreasPresenterContract;

import java.util.ArrayList;

public class AreasPresenter implements AreasPresenterContract {

    private AreasRepository areasRepository;

    public AreasPresenter(){
        areasRepository = new AreasRepository();
    }

    public MutableLiveData<DataLayerResponse<ArrayList<Area>>> getListOfAreas(){
        return areasRepository.getListOfAreas();
    }
}
