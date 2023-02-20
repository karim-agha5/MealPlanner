package com.example.mealplanner.presenters.fragment;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.auth.RegistrationRemoteService;
import com.example.mealplanner.data.datasource.auth.impl.RegistrationRemoteServiceImpl;
import com.example.mealplanner.data.datasource.dbaccess.DatabaseAccess;
import com.example.mealplanner.data.repositories.RegistrationRepository;
import com.example.mealplanner.model.User;
import com.example.mealplanner.presenters.contract.SignUpPresenterContract;
import com.example.mealplanner.ui.contract.DatabaseDelegate;
import com.google.android.gms.common.api.ApiException;

public class SignUpPresenter implements SignUpPresenterContract {

    //private RegistrationRemoteService registrationRemoteService;
    private RegistrationRepository registrationRepository;
    private DatabaseDelegate databaseDelegate;

    public SignUpPresenter(DatabaseDelegate databaseDelegate){
        this.databaseDelegate = databaseDelegate;
     //   registrationRemoteService = new RegistrationRemoteServiceImpl(databaseDelegate.getDatabaseAccess());
        this.registrationRepository = new RegistrationRepository(databaseDelegate.getDatabaseAccess());
    }

    public MutableLiveData<DataLayerResponse<User>> signUpWithGoogle(Intent data) throws ApiException {
        return registrationRepository.signUpWithGoogle(data);
    }


    public MutableLiveData<DataLayerResponse<User>> signUpWithEmailAndPassword(String email, String password,
                                                                               Context context){
        return registrationRepository.signUpWithEmailAndPassword(email,password,context);
    }

    public DatabaseAccess getDatabaseAccess(){
       return databaseDelegate.getDatabaseAccess();
    }
}
