package com.example.mealplanner.presenters.contract;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.repositories.AreasRepository;
import com.example.mealplanner.model.Area;

import java.util.ArrayList;

public interface AreasPresenterContract {
    MutableLiveData<DataLayerResponse<ArrayList<Area>>> getListOfAreas();
}
