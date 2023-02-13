package com.example.mealplanner.data.datasource.auth.impl;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.Status;
import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.auth.RegistrationRemoteService;
import com.example.mealplanner.ui.Test;
import com.example.mealplanner.ui.fragments.WelcomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.List;

public class RegistrationRemoteServiceImpl implements RegistrationRemoteService {

    private Activity activity;
    private Intent data;
    private GoogleSignInAccount account;
    private Test welcomeFragment;
    private FirebaseAuth mAuth;

    public RegistrationRemoteServiceImpl(Activity activity,Intent data,Test welcomeFragment)
            throws ApiException {
        this.activity = activity;
        this.data = data;
        account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
        this.welcomeFragment = welcomeFragment;
        mAuth =FirebaseAuth.getInstance();
    }

    /**
    * This method checks if the user has signed up before with their Google account
    * @Returns true if the user ahs signed up before
    * */
    @Override
    public MutableLiveData<Boolean> isSignedUpWithGoogle(){
        MutableLiveData<Boolean> isSignedUp = new MutableLiveData<>();
        Task<SignInMethodQueryResult>  resultTask =
                FirebaseAuth.getInstance().fetchSignInMethodsForEmail(account.getEmail());
        // returns > 0 if signed up before
        resultTask.addOnCompleteListener(e -> {
            //TODO make a callback somewhere to receive the boolean value so you can fetch data from the db
            isSignedUp.setValue(resultTask.getResult().getSignInMethods().size() != 0);
        });

        return isSignedUp;
    }


    //TODO implement this method later on.
    @Override
    public MutableLiveData<Boolean> isSignedUpWithEmailAndPassword(){
        throw new RuntimeException();
    }


    //TODO change the DataLayerResponse from a raw type to a parameterized type
    @Override
    public MutableLiveData<DataLayerResponse> signUpWithGoogle() throws ApiException {

        DataLayerResponse response = new DataLayerResponse();

        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

        GoogleSignInAccount account = task.getResult(ApiException.class);
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);

        // Attempt to Sign-in the user
        FirebaseAuth.getInstance()
                .signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            response.setStatus(Status.SUCCESS);
                            //FirebaseAuth.getCurrentUser().getUid();
                            //TODO add a User model to the response wrapping its ID, as well.
                            welcomeFragment.notifyFragment(); //TODO replace with Presenter
                        }
                        else{
                            response.setStatus(Status.FAILURE);
                        }
                    }
                });

        return new MutableLiveData<>(response);
    }


    //TODO implement this method later on.
    @Override
    public DataLayerResponse signUpWithEmailAndPassword(String email,String password){

         DataLayerResponse response = new DataLayerResponse();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(activity, "Registration was successful", Toast.LENGTH_SHORT).show();
                    response.setStatus(Status.SUCCESS);
                    // Navigation.findNavController(view).navigate(R.id.HomeFragment);
                               /* Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();*/

                } else {
                    Exception exception = task.getException();
                    if (exception == null) {
                        Toast.makeText(activity, "UnExpected error occurred", Toast.LENGTH_SHORT).show();
                    } else if (exception.getClass().equals(FirebaseNetworkException.class)) {
                        Toast.makeText(activity, "Network error", Toast.LENGTH_SHORT).show();

                    } else {
                        if (((FirebaseAuthException) exception).getErrorCode().equals("ERROR_WEAK_PASSWORD")) {

                            Toast.makeText(activity, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }


                }
            }
        });
        return response;
    }
}
