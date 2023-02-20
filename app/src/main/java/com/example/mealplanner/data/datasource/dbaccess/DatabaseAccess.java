package com.example.mealplanner.data.datasource.dbaccess;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;

import com.example.mealplanner.data.localdb.DBUser;
import com.example.mealplanner.data.localdb.MealDAO;
import com.example.mealplanner.data.localdb.UserDAO;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.model.User;

import java.util.Date;
import java.util.List;

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

    public LiveData<User> getUserByEmail(String email){return userDAO.getUserByEmail(email);}
    public LiveData<User> getUser(){return userDAO.getUsers();}
    public void deleteUser(User user){
        userDAO.deleteUser(user);
    }

    public LiveData<List<Meal>> getUserMeals(String userId){return mealDAO.getUserMeals(userId);}

    public LiveData<Meal> getMeal(String mealId){return mealDAO.getMeal(mealId);}
    public void insertMeal(Meal meal) {
        mealDAO.insertMeal(meal);
    }

    public void deleteMeal(Meal meal){
        mealDAO.deleteMeal(meal);
    }

    public void updateMealIsPlanned(String userId,String mealId,boolean isPlanned){
        mealDAO.updateMealIsPlanned(userId, mealId, isPlanned);
    }

    public void updateMealIsFavorite(String userId,String mealId,boolean isFavorite){
        mealDAO.updateMealIsFavorite(userId, mealId, isFavorite);
    }

    public void updateMealDate(String userId, String mealId, Date date){
        mealDAO.updateMealDate(userId, mealId, date);
    }

    public LiveData<List<Meal>> getFavoriteMeals(){
        return mealDAO.getFavoriteMeals();
    }

    public LiveData<List<Meal>> getPlannedMeals(){
        return mealDAO.getPlannedMeals();
    }

}