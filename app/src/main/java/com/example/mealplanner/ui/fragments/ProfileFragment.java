package com.example.mealplanner.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.ui.activities.MainActivity;
import com.example.mealplanner.ui.activities.WelcomeActivity;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {

   private TextView favorite;
   private TextView planned;

   private TextView logOut;

   private NavController navController;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navController = Navigation.findNavController(getActivity(), R.id.container);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         favorite = view.findViewById(R.id.favoriteTxt);
         planned = view.findViewById(R.id.plannedTxt);
         logOut = view.findViewById(R.id.logOutTxt);


        favorite.setOnClickListener(
                (view1) -> {
                    NavDirections directions =
                            ProfileFragmentDirections.actionProfileFragmentToFavoriteFragment();
                    Navigation.findNavController(view).navigate(directions);
                }
        );

        planned.setOnClickListener(
                (view1) -> {
                    NavDirections directions =
                            ProfileFragmentDirections.actionProfileFragmentToPlannedMealsFragment();
                    Navigation.findNavController(view).navigate(directions);
                }
        );

        logOut.setOnClickListener(
                (view1) ->{
                    Activity activity = getActivity();
                    FirebaseAuth.getInstance().signOut();
                    if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                        while (navController.popBackStack() == true) {
                        }
                        Navigation.findNavController(activity, R.id.container).navigate(R.id.loginFragment2);
                    } else {
                        Toast.makeText(activity, R.string.logOut, Toast.LENGTH_SHORT).show();
                    }

                }
        );

    }
}