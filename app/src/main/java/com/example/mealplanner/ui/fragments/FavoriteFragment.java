package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.presenters.contract.FavoriteMealsPresenterContract;
import com.example.mealplanner.presenters.fragment.FavoriteMealsPresenter;
import com.example.mealplanner.ui.adapters.FavoriteMealsAdapter;
import com.example.mealplanner.ui.contract.DatabaseDelegate;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteMealsAdapter favoriteMealsAdapter;
    private FavoriteMealsPresenterContract favoriteMealsPresenterContract;
    private final String TAG = "Exception";

    public FavoriteFragment() {
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
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_favorite_meals);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        favoriteMealsPresenterContract = new FavoriteMealsPresenter((DatabaseDelegate) getActivity());

        // Attempt to retrieve the favorite meals list from the local db
       /* LiveData<List<Meal>> response =
                favoriteMealsPresenterContract.getUserMeals(
                        FirebaseAuth
                        .getInstance()
                        .getCurrentUser()
                        .getUid()
        );*/

        LiveData<List<Meal>> response =
                favoriteMealsPresenterContract.getFavoriteMeals();

        response.observe(this,meals -> {
            favoriteMealsAdapter = new FavoriteMealsAdapter(getActivity(),meals,favoriteMealsPresenterContract);
            recyclerView.setAdapter(favoriteMealsAdapter);
        });
    }

}
