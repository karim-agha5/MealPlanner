package com.example.mealplanner.data.repositories;

import com.example.mealplanner.data.api.ApiService;
import com.example.mealplanner.data.api.responses.SearchMealByQueryResponse;
import com.example.mealplanner.network.MealsNetworkCallBack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchMealsByQueryRepository {

    private ApiService apiService;
    private Retrofit retrofit;


    public SearchMealsByQueryRepository() {

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void searchMealsByQuery(String query, MealsNetworkCallBack mealsNetworkCallBack) {
        apiService.searchMealsByQuery(query).enqueue(new Callback<SearchMealByQueryResponse>() {
            @Override
            public void onResponse(Call<SearchMealByQueryResponse> call, Response<SearchMealByQueryResponse> response) {
                SearchMealByQueryResponse searchMealByQueryResponse = response.body();
                mealsNetworkCallBack.onSuccessResult(searchMealByQueryResponse.getMeals());
            }

            @Override
            public void onFailure(Call<SearchMealByQueryResponse> call, Throwable t) {
                mealsNetworkCallBack.onFailureResult(t.getMessage());
            }
        });


    }

}
