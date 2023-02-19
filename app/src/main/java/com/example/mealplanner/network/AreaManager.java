package com.example.mealplanner.network;

import com.example.mealplanner.data.api.ApiService;
import com.example.mealplanner.data.api.RetrofitManager;

public class AreaManager {

    private ApiService apiService;
    private RetrofitManager retrofitManager;

    public AreaManager(ApiService apiService,RetrofitManager retrofitManager) {
        this.apiService = apiService;
        this.retrofitManager = retrofitManager;
    }
}
