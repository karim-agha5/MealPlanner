package com.example.mealplanner.data.datasource.auth;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;

/*
* Supports signing up with Google and regular email and password only for the time being.
* */
public interface RegistrationRemoteService {
    //TODO Change the return type to wrap a User model
    MutableLiveData<Boolean> isSignedUpWithGoogle();
    MutableLiveData<Boolean> isSignedUpWithEmailAndPassword();
    MutableLiveData<DataLayerResponse> signUpWithGoogle() throws ApiException;
    DataLayerResponse signUpWithEmailAndPassword(String email,String password);

}
