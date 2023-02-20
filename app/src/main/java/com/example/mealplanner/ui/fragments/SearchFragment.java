package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.example.mealplanner.model.Meal;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    private CardView mealsCardView;
    private CardView igredientCardView;
    private CardView categoryCardView;

   public static final String categoryType = "category";
    public static final String mealsType = "meals";
   public static final String ingerdiantType = "ingerdiant";

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsCardView = view.findViewById(R.id.CardView_meals);
        mealsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // navigateToSearch(mealsType, view);
                NavDirections directions =
                        SearchFragmentDirections.actionSearchFragmentToSearchByQueryFragment(mealsType);
                Navigation.findNavController(view).navigate(directions);
            }
        });
        categoryCardView = view.findViewById(R.id.CardView_category);
        categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //navigateToSearch(categoryType, view);
                NavDirections directions =
                        SearchFragmentDirections.actionSearchFragmentToAllCategoryFragment();
                Navigation.findNavController(view).navigate(directions);
            }
        });
        igredientCardView = view.findViewById(R.id.cardView_ingrediant);
        igredientCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSearch(ingerdiantType, view);
            }
        });
    }

    private void navigateToSearch(@NonNull String type, View view) {
        NavDirections directions =
                SearchFragmentDirections.actionSearchFragmentToSearchByQueryFragment(type);
        Navigation.findNavController(view).navigate(directions);
    }
}