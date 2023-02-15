package com.example.mealplanner.data.localdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealplanner.model.Meal;
import com.example.mealplanner.model.User;


@Database(entities = {User.class, Meal.class}, version = 1)
public abstract class DBUser extends RoomDatabase {

    private static DBUser instance = null;

    public abstract UserDAO userDAO();
    public abstract MealDAO mealDAO();

    public static synchronized DBUser getInstance(Context context) {
        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(), DBUser.class, "FoodPlannerdb").build();
        }
        return instance;
    }

}