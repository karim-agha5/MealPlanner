package com.example.mealplanner.data.datasource.auth.impl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.helper.Status;
import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.datasource.auth.RegistrationRemoteService;
import com.example.mealplanner.model.User;
import com.example.mealplanner.ui.Test;
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


public class RegistrationRemoteServiceImpl implements RegistrationRemoteService {

  //  private Activity activity;
//    private Intent data;
    //private GoogleSignInAccount account;
    private Test welcomeFragment;
    private FirebaseAuth mAuth;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

  /*  public RegistrationRemoteServiceImpl(Activity activity,Intent data,Test welcomeFragment)
            throws ApiException {
        this.activity = activity;
        this.data = data;
        account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
        this.welcomeFragment = welcomeFragment;
        mAuth =FirebaseAuth.getInstance();
    }*/





    public RegistrationRemoteServiceImpl(Test welcomeFragment){
        this.welcomeFragment = welcomeFragment;
        mAuth = FirebaseAuth.getInstance();
    }






    /**
    * This method checks if the user has signed up before with their Google account
    * @Returns true if the user ahs signed up before
    * */

   /* private MutableLiveData<Boolean> isSignedUpWithGoogle(GoogleSignInAccount account){
        MutableLiveData<Boolean> isSignedUp = new MutableLiveData<>();
        Task<SignInMethodQueryResult>  resultTask =
                FirebaseAuth.getInstance().fetchSignInMethodsForEmail(account.getEmail());
        // returns > 0 if signed up before
        resultTask.addOnCompleteListener(e -> {
            //TODO make a callback somewhere to receive the boolean value so you can fetch data from the db
          //  isSignedUp.setValue(resultTask.getResult().getSignInMethods().size() != 0);
            Log.i("Exception", "The user has signed up before");
        });

        return isSignedUp;
    }
*/




    //TODO change the DataLayerResponse from a raw type to a parameterized type
    @Override
    public MutableLiveData<DataLayerResponse<User>> signUpWithGoogle(Intent data) throws ApiException {

        DataLayerResponse<User> dataLayerResponse = new DataLayerResponse();
        MutableLiveData<DataLayerResponse<User>> response = new MutableLiveData<>();

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

                            User user = new User();
                            user.setId(mAuth.getCurrentUser().getUid());
                            user.setName(mAuth.getCurrentUser().getEmail());
                            dataLayerResponse.setWrappedResponse(user);
                            dataLayerResponse.setStatus(Status.SUCCESS);
                            response.setValue(dataLayerResponse);

                            welcomeFragment.notifyFragment(); //TODO replace with Presenter
                        }
                        else{
                            dataLayerResponse.setStatus(Status.FAILURE);
                            dataLayerResponse.setMessage(task.getException().getMessage());
                            response.setValue(dataLayerResponse);
                        }
                    }
                });


        return response;
    }


    @Override
    public MutableLiveData<DataLayerResponse<User>> signUpWithEmailAndPassword(String email, String password, Context context){

        DataLayerResponse<User> dataLayerResponse = new DataLayerResponse();
        MutableLiveData<DataLayerResponse<User>> response = new MutableLiveData<>();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(context, "Registration was successful", Toast.LENGTH_SHORT).show();
                    User user = new User();
                    user.setId(mAuth.getCurrentUser().getUid());
                    user.setName(mAuth.getCurrentUser().getEmail());

                    dataLayerResponse.setWrappedResponse(user);
                    dataLayerResponse.setStatus(Status.SUCCESS);
                }

                // If an exception occurs.
                else {

                    Log.i("Exception", "Inside the else clause ");
                    Exception exception = task.getException();

                    if (exception.getClass().equals(FirebaseNetworkException.class)) {
                        dataLayerResponse.setMessage("Network Error");
                    }

                    else if (((FirebaseAuthException) exception).getErrorCode().equals("ERROR_WEAK_PASSWORD")){
                        dataLayerResponse.setMessage("Password should be at least 6 characters");
                    }
                    else if(!email.matches(emailPattern)){
                        dataLayerResponse.setMessage("bad format for your E-mail");
                    }

                    else{
                        dataLayerResponse.setMessage("You've signed up before");
                    }

                    dataLayerResponse.setStatus(Status.FAILURE);
                    Log.i("Exception", " FAILLLEEEDDD!!!");
                }


                response.postValue(dataLayerResponse);
            }


        });


        return response;
    }
}
