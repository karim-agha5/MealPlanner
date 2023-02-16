package com.example.mealplanner.data.api;


import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ApiService {

    @GET("list.php?a=list")
    Observable<AreaResponse> getAllArea();
}