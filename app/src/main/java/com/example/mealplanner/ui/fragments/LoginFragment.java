package com.example.mealplanner.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.auth.LoginRemoteService;
import com.example.mealplanner.data.datasource.auth.impl.LoginRemoteServiceImpl;
import com.example.mealplanner.helper.AlertDialogHelper;
import com.example.mealplanner.helper.ProgressDialogHelper;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.User;
import com.example.mealplanner.ui.Test;
import com.example.mealplanner.ui.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment implements Test {

    private LoginRemoteService loginRemoteService;
    private EditText etEmail;
    private EditText etPassword;
    private Button   btnLogin;
    private ProgressDialogHelper progressDialogHelper;
    private AlertDialogHelper alertDialogHelper;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final String TAG = "Exception";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginRemoteService = new LoginRemoteServiceImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);



        btnLogin.setOnClickListener(view1 -> {

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if(!isUserInputValid(email,password)){
                startAlertDialog("Incomplete Credentials","Please fill all your credentials");
            }

            else{
                startLoginLoading();
                // Attempt to Login using Email and Password
                MutableLiveData<DataLayerResponse<User>> response =
                        loginRemoteService.login(
                                email,
                                password
                        );


                // Observe over the response and react accordingly
                response.observe(this,userDataLayerResponse -> {
                    stopLoginLoading();

                    DataLayerResponse<User> dataLayerResponse = response.getValue();

                    Log.i(TAG, "status in observer -> " + dataLayerResponse.getStatus());

                    if(dataLayerResponse.getStatus() == Status.SUCCESS){
                        Log.i(TAG, "Your login should be successful");
                        User user = dataLayerResponse.getWrappedResponse();
                        Toast.makeText(getActivity(),
                                "Welcome back",
                                Toast.LENGTH_SHORT).show();
                        homeScreenRedirection();
                        //TODO propogate it to database to receive user's data if there's any
                    }
                    else{
                        startAlertDialog("Unable to login"
                                ,"Cannot find this E-mail or password");
                    }

                });

            }


        });


    }

    private void initUI(View view){
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnLogin = view.findViewById(R.id.btn_login);
    }

    private boolean isUserInputValid(String email,String password){
        return !email.isEmpty() && !password.isEmpty();
    }

    private void startLoginLoading(){
        progressDialogHelper =
                new ProgressDialogHelper(
                        getActivity(),
                        "Attempting to login",
                        "Hold on for a moment"
                );
        progressDialogHelper.startProgressDialog();
    }

    private void stopLoginLoading(){
        progressDialogHelper.stopProgressDialog();
    }

    private void startAlertDialog(String title,String message){
        alertDialogHelper = new AlertDialogHelper(
                getActivity(),
                title,
                message
        );
        alertDialogHelper.startAlertDialog();
    }

    public void notifyFragment() {
        homeScreenRedirection();
    }

    private void homeScreenRedirection(){
        Activity currentActivity = getActivity();
        Intent intent = new Intent(currentActivity, MainActivity.class);
        currentActivity.startActivity(intent);
        currentActivity.finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Get Current User if available
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            //TODO re-direct to Home Screen if the user already exists.
            //TODO it already re-directs the uer to the Home Screen by itself
        }
    }
}