package com.example.mealplanner.data.datasource.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.model.User;

import io.reactivex.Flowable;

public interface LoginRemoteService {
    MutableLiveData<DataLayerResponse<LiveData<User>>> login(String email, String password);
}

