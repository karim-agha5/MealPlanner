package com.example.mealplanner.data.datasource.auth.impl;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.auth.LoginRemoteService;
import com.example.mealplanner.data.datasource.dbaccess.DatabaseAccess;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.User;
import com.example.mealplanner.ui.Test;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Flowable;

public class LoginRemoteServiceImpl implements LoginRemoteService {

    private FirebaseAuth mAuth;
    private Test test;
    private DatabaseAccess databaseAccess;
    private final String TAG = "Exception";

    public LoginRemoteServiceImpl(DatabaseAccess databaseAccess) {
        mAuth = FirebaseAuth.getInstance();
        this.databaseAccess = databaseAccess;
    }

    @Override
    public MutableLiveData<DataLayerResponse<LiveData<User>>> login(String email, String password) {
        DataLayerResponse<LiveData<User>> dataLayerResponse = new DataLayerResponse<>();
        MutableLiveData<DataLayerResponse<LiveData<User>>> response = new MutableLiveData<>();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            User user = new User();
                            user.setName(currentUser.getEmail());
                            user.setId(currentUser.getUid());
                            new Thread(() -> {
                               dataLayerResponse.setWrappedResponse(databaseAccess.getUser());
                               dataLayerResponse.setStatus(Status.SUCCESS);
                               response.postValue(dataLayerResponse);
                            }).start();

                        } else {
                            dataLayerResponse.setStatus(Status.FAILURE);
                            response.setValue(dataLayerResponse);
                        }

                    }
                });

        return response;
    }
}

