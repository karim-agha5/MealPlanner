package com.example.mealplanner.network;

public class AreaManager {

    private ApiService apiService;
    private RetrofitManager retrofitManager;

    public AreaManager(ApiService apiService,RetrofitManager retrofitManager) {
        this.apiService = apiService;
        this.retrofitManager = retrofitManager;
    }
}
