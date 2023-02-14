package com.example.mealplanner.data.datasource.auth;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.model.User;

public interface LoginRemoteService {
    MutableLiveData<DataLayerResponse<User>> login(String email,String password);
}