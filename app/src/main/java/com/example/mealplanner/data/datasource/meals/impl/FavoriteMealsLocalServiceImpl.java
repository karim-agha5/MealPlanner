package com.example.mealplanner.data.datasource.meals.impl;

import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.dbaccess.DatabaseAccess;
import com.example.mealplanner.data.datasource.meals.FavoriteMealsLocalService;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.Meal;

import java.util.Date;
import java.util.List;

public class FavoriteMealsLocalServiceImpl implements FavoriteMealsLocalService {

    private DatabaseAccess databaseAccess;

    public FavoriteMealsLocalServiceImpl(DatabaseAccess databaseAccess){
        this.databaseAccess = databaseAccess;
    }

    @Override
    public LiveData<List<Meal>> getUserMeals(String userId){

       return databaseAccess.getUserMeals(userId);
    }

    @Override
    public MutableLiveData<DataLayerResponse> addMealToLocalDatabase(Meal meal){

        MutableLiveData<DataLayerResponse> response = new MutableLiveData<>();
        DataLayerResponse dataLayerResponse = new DataLayerResponse();
        //TODO replace with a method that inserts jobs into a thread pool
        new Thread(
                () -> {
                    try{
                        databaseAccess.insertMeal(meal);
                    }
                    catch(SQLiteConstraintException ex){
                        dataLayerResponse.setStatus(Status.FAILURE);
                        dataLayerResponse.setMessage("The meal is already added");
                        response.postValue(dataLayerResponse);
                    }
                    finally {
                        dataLayerResponse.setStatus(Status.SUCCESS);
                        response.postValue(dataLayerResponse);
                    }
                }
        ).start();

        return response;
    }

    @Override
    public MutableLiveData<DataLayerResponse> updateMealIsPlanned(String userId,String mealId,boolean isPlanned){
        MutableLiveData<DataLayerResponse> response = new MutableLiveData<>();
        DataLayerResponse  dataLayerResponse= new DataLayerResponse();

        new Thread(
                () -> {
                    try{
                        databaseAccess.updateMealIsPlanned(userId, mealId, isPlanned);
                    }
                    catch(SQLiteException ex){
                        Log.i("Exception", "updateMealIsPlanned: " + ex.getMessage());
                        dataLayerResponse.setStatus(Status.FAILURE);
                        dataLayerResponse.setMessage("Error");
                        response.postValue(dataLayerResponse);
                    }
                    finally {
                        dataLayerResponse.setStatus(Status.SUCCESS);
                        dataLayerResponse.setMessage("Done");
                        response.postValue(dataLayerResponse);
                    }
                }
        ).start();

        return response;
    }

    @Override
    public MutableLiveData<DataLayerResponse> updateMealIsFavorite(String userId,String mealId,boolean isFavorite){
        MutableLiveData<DataLayerResponse> response = new MutableLiveData<>();
        DataLayerResponse dataLayerResponse= new DataLayerResponse();


        new Thread(
                () -> {
                    try{
                        databaseAccess.updateMealIsFavorite(userId, mealId, isFavorite);
                    }
                    catch(SQLiteException ex){
                        Log.i("Exception", "updateMealIsFavorite: " + ex.getMessage());
                        dataLayerResponse.setStatus(Status.FAILURE);
                        dataLayerResponse.setMessage("Error");
                        response.postValue(dataLayerResponse);
                    }
                    finally {
                        dataLayerResponse.setStatus(Status.SUCCESS);
                        dataLayerResponse.setMessage("Done");
                        response.postValue(dataLayerResponse);
                    }
                }
        ).start();

        return response;
    }

    @Override
    public LiveData<Meal> getMeal(String mealId){
        return databaseAccess.getMeal(mealId);
    }

    @Override
    public void deleteMeal(final Meal meal) {
        new Thread(
                () -> {
                    databaseAccess.deleteMeal(meal);
                }
        ).start();
    }

    @Override
    public LiveData<List<Meal>> getFavoritetMeals() {
        return databaseAccess.getFavoriteMeals();
    }

    @Override
    public LiveData<List<Meal>> getPlannedMeals() {
        return databaseAccess.getPlannedMeals();
    }

    @Override
    public void updateMealDate(String userId, String mealId, Date date) {
        new Thread(
                () -> {
                    databaseAccess.updateMealDate(userId, mealId, date);
                }
        ).start();
    }
}

