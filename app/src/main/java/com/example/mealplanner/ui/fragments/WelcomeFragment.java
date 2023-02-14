package com.example.mealplanner.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.auth.RegistrationRemoteService;
import com.example.mealplanner.data.datasource.auth.impl.RegistrationRemoteServiceImpl;
import com.example.mealplanner.helper.AlertDialogHelper;
import com.example.mealplanner.helper.ProgressDialogHelper;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.User;
import com.example.mealplanner.ui.Test;
import com.example.mealplanner.ui.activities.MainActivity;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class WelcomeFragment extends Fragment implements Test {

    private final int GOOGLE_REQUEST_CODE = 5;
    private Button btnSignUp;
    private Button btnSignUpWithGoogle;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInOptions options;
    private TextView tvLogin;
    private final String TAG = "Exception";
    private RegistrationRemoteService registrationRemoteService;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        options = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        registrationRemoteService = new RegistrationRemoteServiceImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);

        
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnSignUp = view.findViewById(R.id.btn_sign_up);
        tvLogin = view.findViewById(R.id.tv_login);
        initUI(view);

        btnSignUpWithGoogle.setOnClickListener(view1 -> {
            // Open up the choose Google account / sign up with a certain Google email and password
            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent,GOOGLE_REQUEST_CODE);
        });


        btnSignUp.setOnClickListener(
                (view1) -> {
                    NavDirections directions =
                            WelcomeFragmentDirections.actionWelcomeFragmentToSignUpFragment();

                    Navigation.findNavController(view).navigate(directions);
                }
        );


        tvLogin.setOnClickListener(
                (view1) -> {
                    NavDirections directions =
                            WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment();
                    Navigation.findNavController(view).navigate(directions);
                }
        );



    }

    private void initUI(View view){
        btnSignUp = view.findViewById(R.id.btn_sign_up);
        btnSignUpWithGoogle = view.findViewById(R.id.btn_signWithGoogle);
        tvLogin = view.findViewById(R.id.tv_login);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        googleSignInClient = GoogleSignIn.getClient(getActivity(),options);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_REQUEST_CODE){

            try {

                ProgressDialogHelper progressDialogHelper =
                        new ProgressDialogHelper(
                                getActivity(),
                                "Signing you in",
                                "Hold on for a moment"
                        );

                progressDialogHelper.startProgressDialog();
                // Sign-in with Google and return the User model and the Status sometime in the future
                MutableLiveData<DataLayerResponse<User>> response =
                        registrationRemoteService.signUpWithGoogle(data);


                /*
                   TODO propogate the response somewhere else - possibly to fetch data from the db
                    if there's a user */
                response.observe(this,userDataLayerResponse -> {
                    Log.i(TAG, "onActivityResult: user status = " + response.getValue().getStatus());

                    progressDialogHelper.stopProgressDialog();

                    DataLayerResponse<User> dataLayerResponse = response.getValue();

                    /*
                    * If the login process succeeded.
                    * Re-direct the user to the Home screen
                    * */
                    if(dataLayerResponse.getStatus() == Status.SUCCESS){
                        //TODO propogate it to another repo to get the user's data if there's any
                        User user = dataLayerResponse.getWrappedResponse();
                        homeScreenRedirection();
                    }

                    else{
                        AlertDialogHelper alertDialogHelper =
                                new AlertDialogHelper(getActivity(),
                                        "Error",
                                        dataLayerResponse.getMessage() != null ?
                                                dataLayerResponse.getMessage() : "Unknown Error"
                                );
                        alertDialogHelper.startAlertDialog();
                    }

                });

            } catch (ApiException e) {
                //TODO handle not being able to sign to google exception
                Log.i(TAG, "onActivityResult: ApiException");
                AlertDialogHelper alertDialogHelper = new AlertDialogHelper(
                        getActivity(),"Error",
                        "Unable to connect to Google Services"
                );
                alertDialogHelper.startAlertDialog();
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

    }

    @Override
    public void notifyFragment() {
        homeScreenRedirection();
    }

    private void homeScreenRedirection(){
        Activity activity = getActivity();
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }


}








