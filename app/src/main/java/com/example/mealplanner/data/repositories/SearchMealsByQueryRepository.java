package com.example.mealplanner.data.repositories;

import android.util.Log;

import com.example.mealplanner.data.api.ApiService;
import com.example.mealplanner.data.api.RetrofitManager;
import com.example.mealplanner.data.api.responses.MealsNetworkCallBack;
import com.example.mealplanner.data.api.responses.SearchMealByQueryResponse;
import com.example.mealplanner.model.AreaListResponse;
import com.example.mealplanner.model.EachCategoryModel;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.model.RootCategoriesList;
import com.example.mealplanner.presenters.contract.InterfaceAllCategories;
import com.example.mealplanner.presenters.contract.InterfaceMealFromSpecificCategory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchMealsByQueryRepository {

    private ApiService apiService;
    private Retrofit retrofit;

    private final String TAG = "Exception";

    private RootCategoriesList rootCategoriesList;


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

    public void getAllCategory(){

    }

}
