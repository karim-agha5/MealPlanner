package com.example.mealplanner.data.datasource.dbaccess;

import android.content.Context;

import com.example.mealplanner.data.localdb.DBUser;
import com.example.mealplanner.data.localdb.MealDAO;
import com.example.mealplanner.data.localdb.UserDAO;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.model.User;

public class DatabaseAccess {
    private DBUser dbUser;
    private UserDAO userDAO;
    private MealDAO mealDAO;

    public DatabaseAccess(Context context) {
        this.dbUser  = DBUser.getInstance(context);
        this.userDAO = dbUser.userDAO();
        this.mealDAO = dbUser.mealDAO();
    }


    public void insertUser(User user){
        userDAO.insertUser(user);
    }

    public void deleteUser(User user){
        userDAO.deleteUser(user);
    }

    public void insertMeal(Meal meal){
        mealDAO.insertMeal(meal);
    }

    public void deleteMeal(Meal meal){
        mealDAO.deleteMeal(meal);
    }

}
