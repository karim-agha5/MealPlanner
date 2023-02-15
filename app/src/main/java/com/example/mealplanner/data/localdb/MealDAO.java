package com.example.mealplanner.data.localdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mealplanner.model.Meal;

import java.util.List;

@Dao
public interface MealDAO {

      /*  @Query("SELECT * FROM MealsItem")
        Flowable<List<Users>> getAllMealItem();*/
        @Query("SELECT * FROM meal")
        List<Meal> getAllMeals();

        @Insert
        void insertMeal(Meal meal);

        @Delete
        void deleteMeal(Meal meal);
    }
