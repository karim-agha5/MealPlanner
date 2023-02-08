package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealplanner.R;

public class WelcomeFragment extends Fragment {

    private Button btnSignUp;
    private TextView tvLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}