package com.example.mealplanner.data.localdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.example.mealplanner.model.Meal;

import java.util.Date;
import java.util.List;

@Dao
public interface MealDAO {

      /*  @Query("SELECT * FROM MealsItem")
        Flowable<List<Users>> getAllMealItem();*/
        @Query("SELECT * FROM meal")
        List<Meal> getAllMeals();

        /*@Query("SELECT * FROM user INNER JOIN meal on user.id = meal.user_id WHERE user.id = :id AND meal.user_id = :id")
        List<Meal> getUserMeals(String id);*/
        @Query("SELECT user_id,meal_id,meal.name,category,area,instructions,image_url,image_path,youtube_video_url,ingredients,measures,is_favorite,is_planned,date FROM user INNER JOIN meal on user.id = meal.user_id WHERE user.id = :id AND meal.user_id = :id")
        LiveData<List<Meal>> getUserMeals(String id);
        @Query("UPDATE meal SET is_planned = :isPlanned WHERE user_id = :userId AND meal_id = :mealId")
        void updateMealIsPlanned(String userId,String mealId,boolean isPlanned);
        @Query("UPDATE meal SET is_favorite = :isFavorite WHERE user_id = :userId AND meal_id = :mealId")
        void updateMealIsFavorite(String userId,String mealId,boolean isFavorite);

        @TypeConverters(ArrayConverter.class)
        @Query("UPDATE meal SET date = :date WHERE user_id = :userId AND meal_id = :mealId")
        void updateMealDate(String userId,String mealId,Date date);
        @Query("SELECT * FROM meal WHERE meal.meal_id = :mealId")
        LiveData<Meal> getMeal(String mealId);

        @Query("SELECT * FROM meal WHERE meal.is_favorite = 1")
        LiveData<List<Meal>> getFavoriteMeals();
        @Query("SELECT * FROM meal WHERE meal.is_planned = 1")
        LiveData<List<Meal>> getPlannedMeals();

        @Insert
        void insertMeal(Meal meal);

        @Delete
        void deleteMeal(Meal meal);
    }
