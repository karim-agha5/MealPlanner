package com.example.mealplanner.data.api;


import com.example.mealplanner.data.api.responses.AreaResponse;
import com.example.mealplanner.data.api.responses.RandomMealsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ApiService {

    @GET("list.php?a=list")
    Observable<AreaResponse> getAllArea();
    @GET("search.php?f=s")
    Observable<RandomMealsResponse> getRandomMeals();
}