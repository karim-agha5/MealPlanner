package com.example.mealplanner.data.repositories;


import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.auth.RegistrationRemoteService;
import com.example.mealplanner.data.datasource.auth.impl.RegistrationRemoteServiceImpl;
import com.example.mealplanner.model.User;
import com.google.android.gms.common.api.ApiException;

/**
 * TODO inject it later
 * and change constructors access modifier -> package-protected
 * */
public class RegistrationRepository {

    private RegistrationRemoteService registrationRemoteService;

     public RegistrationRepository(){
        registrationRemoteService = new RegistrationRemoteServiceImpl();
    }

    public MutableLiveData<DataLayerResponse<User>> signUpWithGoogle(Intent data) throws ApiException{
        return registrationRemoteService.signUpWithGoogle(data);
    }
    public MutableLiveData<DataLayerResponse<User>> signUpWithEmailAndPassword(String email, String password, Context context){
        return registrationRemoteService.signUpWithEmailAndPassword(email,password,context);
    }

}
