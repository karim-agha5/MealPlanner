package com.example.mealplanner.presenters.fragment;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.auth.LoginRemoteService;
import com.example.mealplanner.data.datasource.auth.impl.LoginRemoteServiceImpl;
import com.example.mealplanner.model.User;

public class LoginPresenter {

    private LoginRemoteService loginRemoteService;

    public LoginPresenter(){
        loginRemoteService = new LoginRemoteServiceImpl();
    }

    public MutableLiveData<DataLayerResponse<User>> login(String email, String password){
        return loginRemoteService.login(email, password);
    }
}
