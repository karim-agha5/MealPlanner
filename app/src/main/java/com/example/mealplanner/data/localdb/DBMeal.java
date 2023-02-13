/*package com.example.mealplanner.data.localdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealplanner.model.MealItem;

@Database(entities = MealItem.class, version = 1)
public abstract class DBMeal extends RoomDatabase {

    private static DBMeal instance = null;

    public abstract MealDAO mealDAODAO();

    public static synchronized DBMeal getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DBMeal.class, "FoodPlannerdb").build();
        }
        return instance;
    }
}*/
package com.example.mealplanner.data.localdb;
public class DBMeal{}
