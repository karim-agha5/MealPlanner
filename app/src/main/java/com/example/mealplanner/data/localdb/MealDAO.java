package com.example.mealplanner.data.localdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mealplanner.model.MealItem;
import com.example.mealplanner.model.Users;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {

        @Query("SELECT * FROM MealsItem")
        Flowable<List<Users>> getAllMealItem();


        @Insert
        void insertUser(MealItem mealItem);

        @Delete
        void deleteUser(MealItem mealItem);
    }
