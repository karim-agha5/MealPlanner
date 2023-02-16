package com.example.mealplanner.presenters.contract;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.model.User;

public interface LoginPresenterContract {

    MutableLiveData<DataLayerResponse<User>> login(String email, String password);
}
