package com.example.mealplanner.data.datasource.meals;

import com.example.mealplanner.data.api.responses.AreaResponse;

import io.reactivex.Observable;

public interface AreasRemoteService {
    Observable<AreaResponse> getAllAreas();
}
