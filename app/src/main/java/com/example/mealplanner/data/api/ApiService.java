package com.example.mealplanner.data.api;


import com.example.mealplanner.data.api.responses.AreaResponse;
import com.example.mealplanner.data.api.responses.SearchMealByQueryResponse;
import com.example.mealplanner.data.api.responses.RandomMealsResponse;
import com.example.mealplanner.model.AreaListResponse;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.model.RootCategoriesList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    @GET("list.php?a=list")
    Observable<AreaResponse> getAllArea();
    @GET("search.php?f=s")
    Observable<RandomMealsResponse> getRandomMeals();

    @GET("filter.php?")
    Call<AreaListResponse> getMealsOfSelectedArea(@Query("a") String country);

    @GET("search.php")
    Call<SearchMealByQueryResponse> searchMealsByQuery(@Query("s") String firstLetterOfMeal);

    @GET("list.php?c=list")
    Observable<RootCategoriesList> getRootCategoriesList();


    @GET("filter.php")
    Observable<Meal> getMealsOfSelectedCategory(@Query("c") String categorySelected);
}