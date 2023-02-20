package com.example.mealplanner.data.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.auth.LoginRemoteService;
import com.example.mealplanner.data.datasource.auth.impl.LoginRemoteServiceImpl;
import com.example.mealplanner.data.datasource.dbaccess.DatabaseAccess;
import com.example.mealplanner.model.User;

import io.reactivex.Flowable;

/**
 * TODO inject later
 * and change constructors access modifier -> package-protected
 */

public class LoginRepository {

    private LoginRemoteService loginRemoteService;

    public LoginRepository(DatabaseAccess databaseAccess){
        loginRemoteService = new LoginRemoteServiceImpl(databaseAccess);
    }

    public MutableLiveData<DataLayerResponse<LiveData<User>>> login(String email, String password){
        return loginRemoteService.login(email,password);
    }
}
