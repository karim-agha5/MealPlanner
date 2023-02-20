package com.example.mealplanner.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
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
import com.example.mealplanner.data.datasource.dbaccess.DatabaseAccess;
import com.example.mealplanner.data.localdb.UserDAO;
import com.example.mealplanner.helper.AlertDialogHelper;
import com.example.mealplanner.helper.ProgressDialogHelper;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.User;
import com.example.mealplanner.presenters.fragment.LoginPresenter;
import com.example.mealplanner.ui.Test;
import com.example.mealplanner.ui.activities.MainActivity;
import com.example.mealplanner.ui.activities.WelcomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LoginFragment extends Fragment{

    private LiveData<User> liveData;

    private User user;
    private LoginPresenter loginPresenter;
    private EditText etEmail;
    private EditText etPassword;
    private Button   btnLogin;
    private ProgressDialogHelper progressDialogHelper;
    private AlertDialogHelper alertDialogHelper;
    private final String TAG = "Exception";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenter((WelcomeActivity)getActivity());
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






        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);

            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                Log.i(TAG, "onLost: no internet");
                new Thread(
                        () -> {
                          liveData =   ((WelcomeActivity)getActivity()).getDatabaseAccess()
                                    .getUserByEmail("kiom_karim@hotmail.com");
                        }
                ).start();

                liveData.observe(LoginFragment.this,user1 -> {
                    Log.i(TAG, "onLost: user id -> " + user1.getId());
                });
            }

            @Override
            public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
                final boolean unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
            }
        };





        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();




        ConnectivityManager connectivityManager =
                (ConnectivityManager) getContext().getSystemService(ConnectivityManager.class);
        connectivityManager.requestNetwork(networkRequest, networkCallback);





        btnLogin.setOnClickListener(view1 -> {

            //TODO handle logging in if there's no internet connection

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if(!isUserInputValid(email,password)){
                startAlertDialog("Incomplete Credentials","Please fill all your credentials");
            }

            else{
               startLoginLoading();


                // Attempt to Login using Email and Password
                MutableLiveData<DataLayerResponse<LiveData<User>>> response =
                        loginPresenter.login(
                                email,
                                password
                        );


                // Observe over the response and react accordingly
                response.observe(this,userDataLayerResponse -> {


                    stopLoginLoading();

                    DataLayerResponse<LiveData<User>> dataLayerResponse = response.getValue();

                    Log.i(TAG, "status in observer -> " + dataLayerResponse.getStatus());

                    if(dataLayerResponse.getStatus() == Status.SUCCESS){

                        dataLayerResponse.getWrappedResponse().observe(this,user1 -> {
                            Toast.makeText(getActivity(),
                                    "Welcome back",
                                    Toast.LENGTH_SHORT).show();
                            homeScreenRedirection();
                        });

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

   /* public void notifyFragment() {
        homeScreenRedirection();
    }*/

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
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            homeScreenRedirection();
        }
    }
}