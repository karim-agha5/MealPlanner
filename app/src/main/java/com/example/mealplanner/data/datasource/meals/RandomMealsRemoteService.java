package com.example.mealplanner.data.datasource.meals;

import com.example.mealplanner.data.api.responses.RandomMealsResponse;

import io.reactivex.Observable;

public interface RandomMealsRemoteService {
    Observable<RandomMealsResponse> getRandomMeals();
}
