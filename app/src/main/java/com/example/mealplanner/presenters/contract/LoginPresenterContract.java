package com.example.mealplanner.presenters.contract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.model.User;

import io.reactivex.Flowable;

public interface LoginPresenterContract {

    MutableLiveData<DataLayerResponse<LiveData<User>>> login(String email, String password);
}
