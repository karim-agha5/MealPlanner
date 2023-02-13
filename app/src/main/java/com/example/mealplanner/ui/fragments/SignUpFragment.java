package com.example.mealplanner.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.data.datasource.auth.impl.RegistrationRemoteServiceImpl;
import com.example.mealplanner.ui.Test;
import com.example.mealplanner.ui.activities.MainActivity;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFragment extends Fragment implements Test {

    private EditText signUp_name;
    private EditText signUp_password;
    private EditText signUp_email;
    private EditText confirmPassword;
    private FirebaseAuth mAuth;
    private Button signUp;
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;
    private RegistrationRemoteServiceImpl registrationRemoteService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = signUp_name.getText().toString();
                email = signUp_email.getText().toString();
                password = signUp_password.getText().toString();
                passwordConfirm = confirmPassword.getText().toString();

                if ((!name.isEmpty()) && (!email.isEmpty()) && (!password.isEmpty()) && (password.equals(passwordConfirm))) {

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(requireContext(), "Registration was successful", Toast.LENGTH_SHORT).show();
                               // Navigation.findNavController(view).navigate(R.id.HomeFragment);
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();

                            } else {
                                Exception exception = task.getException();
                                if (exception == null) {
                                    Toast.makeText(getContext(), "UnExpected error occurred", Toast.LENGTH_SHORT).show();
                                } else if (exception.getClass().equals(FirebaseNetworkException.class)) {
                                    Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();

                                } else {
                                    if (((FirebaseAuthException) exception).getErrorCode().equals("ERROR_WEAK_PASSWORD")) {

                                        Toast.makeText(getContext(), "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }


                            }
                        }
                    });

                } else {
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

            }
        });


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

    }
}