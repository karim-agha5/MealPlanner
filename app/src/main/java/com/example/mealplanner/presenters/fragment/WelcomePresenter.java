package com.example.mealplanner.presenters.fragment;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.repositories.RegistrationRepository;
import com.example.mealplanner.model.User;
import com.example.mealplanner.presenters.contract.WelcomePresenterContract;
import com.google.android.gms.common.api.ApiException;

public class WelcomePresenter implements WelcomePresenterContract {

    private RegistrationRepository registrationRepository;

    public WelcomePresenter(){
        registrationRepository = new RegistrationRepository();
    }

    public MutableLiveData<DataLayerResponse<User>> signUpWithGoogle(Intent data) throws ApiException {
        return registrationRepository.signUpWithGoogle(data);
    }
}
