package com.example.mealplanner.network;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {

    @GET("list.php?a=list")
    public Call<AreaResponse> getAllArea();
}
