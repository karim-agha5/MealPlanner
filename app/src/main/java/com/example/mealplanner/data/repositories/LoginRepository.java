package com.example.mealplanner.data.repositories;


import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.auth.LoginRemoteService;
import com.example.mealplanner.data.datasource.auth.impl.LoginRemoteServiceImpl;
import com.example.mealplanner.model.User;

/**
 * TODO inject later
 * and change constructors access modifier -> package-protected
 */

public class LoginRepository {

    private LoginRemoteService loginRemoteService;

    public LoginRepository(){
        loginRemoteService = new LoginRemoteServiceImpl();
    }

    MutableLiveData<DataLayerResponse<User>> login(String email, String password){
        return loginRemoteService.login(email,password);
    }
}
