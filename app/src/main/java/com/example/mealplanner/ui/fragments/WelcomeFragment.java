package com.example.mealplanner.ui.fragments;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.mealplanner.R;
import com.example.mealplanner.data.datasource.auth.RegistrationRemoteService;
import com.example.mealplanner.data.datasource.auth.impl.RegistrationRemoteServiceImpl;
import com.example.mealplanner.ui.Test;
import com.example.mealplanner.ui.activities.MainActivity;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;


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
                registrationRemoteService = new RegistrationRemoteServiceImpl(getActivity(),data,this);
                MutableLiveData<Boolean> isSignedUp = registrationRemoteService.isSignedUpWithGoogle();
                registrationRemoteService.signUpWithGoogle();
            } catch (ApiException e) {
                Log.i(TAG, "onActivityResult: ApiException");
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
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }


}








