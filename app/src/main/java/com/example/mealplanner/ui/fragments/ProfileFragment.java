package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mealplanner.R;


public class ProfileFragment extends Fragment {

   private TextView favorite;
   private TextView planned;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    }
}