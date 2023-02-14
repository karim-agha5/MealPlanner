package com.example.mealplanner.data.datasource.auth.impl;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.auth.LoginRemoteService;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.User;
import com.example.mealplanner.ui.Test;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRemoteServiceImpl implements LoginRemoteService {

    private FirebaseAuth mAuth;
    private Test test;
    private final String TAG = "Exception";

    public LoginRemoteServiceImpl(Test test) {
        this.test = test;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public MutableLiveData<DataLayerResponse<User>> login(String email, String password) {
        DataLayerResponse<User> dataLayerResponse = new DataLayerResponse<>();
        MutableLiveData<DataLayerResponse<User>> response = new MutableLiveData<>();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Log.i(TAG, "task is successful! ");
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            User user = new User();
                            user.setName(currentUser.getEmail());
                            user.setId(currentUser.getUid());
                            dataLayerResponse.setStatus(Status.SUCCESS);
                            dataLayerResponse.setWrappedResponse(user);
                            response.setValue(dataLayerResponse);
                            Log.i(TAG, "status in task is successful-> " + dataLayerResponse.getStatus());
                        } else {
                            Log.i(TAG, "task is NOT successful! ");
                            dataLayerResponse.setStatus(Status.FAILURE);
                            response.setValue(dataLayerResponse);
                        }

                    }
                });

        return response;
    }
}

