package com.example.mealplanner.network;

import com.example.mealplanner.model.AreaListResponse;
import com.example.mealplanner.model.Meal;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    @GET("list.php?a=list")
    Call<AreaResponse> getAllArea();

    @GET("filter.php?")
    Observable<AreaListResponse> getMealsOfSelectedArea(@Query("a") String country);
}
