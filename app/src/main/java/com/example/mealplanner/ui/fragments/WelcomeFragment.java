package com.example.mealplanner.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.mealplanner.ui.activities.MainActivity;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class WelcomeFragment extends Fragment {

    private final int GOOGLE_REQUEST_CODE = 5;
    private Button btnSignUp;
    private Button btnSignUpWithGoogle;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInOptions options;
    private TextView tvLogin;
    private final String TAG = "Exception";

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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        googleSignInClient = GoogleSignIn.getClient(getActivity(),options);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_REQUEST_CODE){

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getActivity(),MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                                else{
                                    Toast.makeText(
                                                    getActivity(),
                                                    "Sign-up failed!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        });
            } catch (ApiException e) {
                Log.i("Exception", "onActivityResult: ");
            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            Log.i(TAG, "onStart: user = " + currentUser);
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    private void initUI(View view){
        btnSignUp = view.findViewById(R.id.btn_sign_up);
        btnSignUpWithGoogle = view.findViewById(R.id.btn_signWithGoogle);
        tvLogin = view.findViewById(R.id.tv_login);
    }

}