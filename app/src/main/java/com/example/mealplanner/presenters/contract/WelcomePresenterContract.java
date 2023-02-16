package com.example.mealplanner.presenters.contract;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.model.User;
import com.google.android.gms.common.api.ApiException;

public interface WelcomePresenterContract {

    MutableLiveData<DataLayerResponse<User>> signUpWithGoogle(Intent data) throws ApiException;
}
