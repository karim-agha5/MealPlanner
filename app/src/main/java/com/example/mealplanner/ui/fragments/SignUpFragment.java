package com.example.mealplanner.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.mealplanner.data.datasource.auth.RegistrationRemoteService;
import com.example.mealplanner.data.datasource.auth.impl.RegistrationRemoteServiceImpl;
import com.example.mealplanner.helper.AlertDialogHelper;
import com.example.mealplanner.helper.ProgressDialogHelper;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.User;
import com.example.mealplanner.ui.Test;
import com.example.mealplanner.ui.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFragment extends Fragment implements Test {

    private EditText signUp_name;
    private EditText signUp_password;
    private EditText signUp_email;
    private EditText confirmPassword;
    private Button signUp;
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;
    private RegistrationRemoteService registrationRemoteService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationRemoteService = new RegistrationRemoteServiceImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }



    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUp_name = view.findViewById(R.id.nameValue);
        signUp_password = view.findViewById(R.id.passwordValue);
        signUp_email = view.findViewById(R.id.emailValue);
        confirmPassword = view.findViewById(R.id.passwordReValue);
        signUp = view.findViewById(R.id.sign_up);

        handlingSignUpButton();

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void notifyFragment() {
        homeScreenRedirection();
    }

    private void homeScreenRedirection(){
        Activity currentActivity = getActivity();
        Intent intent = new Intent(currentActivity, MainActivity.class);
        currentActivity.startActivity(intent);
        currentActivity.finish();
    }

    private void getTextsFromEditTexts(){
        name = signUp_name.getText().toString();
        email = signUp_email.getText().toString();
        password = signUp_password.getText().toString();
        passwordConfirm = confirmPassword.getText().toString();
    }

    /**
     * @Returns true if all input the TextFields are valid
     * */
    private boolean isUserInputValid(){
        return (!name.isEmpty()) && (!email.isEmpty()) &&
                (!password.isEmpty()) && (password.equals(passwordConfirm));
    }

    private void handleUserInputInvalid(){
        if (name.isEmpty()) {
            Toast.makeText(getContext(), "Enter your name", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()) {
            Toast.makeText(getContext(), "Enter your email", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getContext(), "Enter your password", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(passwordConfirm)) {
            Toast.makeText(getContext(), "Password not identical", Toast.LENGTH_SHORT).show();

        }
    }
    private void handlingSignUpButton(){

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTextsFromEditTexts();

                if (isUserInputValid()) {

                    ProgressDialogHelper progressDialogHelper =
                            new ProgressDialogHelper(
                                    getActivity(),
                                    "Signing you up",
                                    "Hold on for a moment"
                            );

                    progressDialogHelper.startProgressDialog();

                    MutableLiveData<DataLayerResponse<User>> response =
                            registrationRemoteService.signUpWithEmailAndPassword(email,password,getActivity());


                    // Set an observer on the response and do some action
                    response.observe(SignUpFragment.this,userDataLayerResponse -> {

                        Log.i("Exception", "onClick() user status = " +
                                response.getValue().getStatus());

                        // Stop the dialog and handle both cases of the response
                        progressDialogHelper.stopProgressDialog();
                        DataLayerResponse<User> dataLayerResponse = response.getValue();


                        /*
                        * If the user has signed up before
                        * show a dialog containing the reason the user can't sign up
                        * The dialog also checks if the response returned has a message or not
                        * if not, it shows "Unknown Error"
                        * */
                        if(dataLayerResponse.getStatus() == Status.FAILURE){
                            AlertDialogHelper alertDialogHelper =
                                    new AlertDialogHelper(
                                            getActivity(),
                                            null,
                                            dataLayerResponse.getMessage() != null ?
                                                    dataLayerResponse.getMessage() : "Unknown Error"
                                    );
                            alertDialogHelper.startAlertDialog();
                        }


                        /*
                            If the user hasn't signed up before.
                            Destroy this activity and re-direct the user to the Home Screen
                         */
                        else{
                            Toast.makeText(getActivity(), "Thanks for signing up!",
                                    Toast.LENGTH_SHORT).show();
                            homeScreenRedirection();
                        }


                    });



                } else {
                    handleUserInputInvalid();
                }

            }
        });
    }
}