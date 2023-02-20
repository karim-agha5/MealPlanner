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
import com.example.mealplanner.presenters.contract.PlannedMealsPresenterContract;
import com.example.mealplanner.presenters.fragment.FavoriteMealsPresenter;
import com.example.mealplanner.presenters.fragment.PlannedMealsPresenter;
import com.example.mealplanner.ui.adapters.FavoriteMealsAdapter;
import com.example.mealplanner.ui.adapters.PlannedMealsAdapter;
import com.example.mealplanner.ui.contract.DatabaseDelegate;

import java.util.List;


public class PlannedMealsFragment extends Fragment {

    private RecyclerView recyclerView;
    private PlannedMealsAdapter plannedMealsAdapter;
    private PlannedMealsPresenterContract plannedMealsPresenterContract;

    private List<Meal> plannedMealsList;

    public PlannedMealsFragment() {
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
        return inflater.inflate(R.layout.fragment_planned_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_planned_meals);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        plannedMealsPresenterContract = new PlannedMealsPresenter((DatabaseDelegate) getActivity());

        LiveData<List<Meal>> response =
                plannedMealsPresenterContract.getPlannedMeals();

        response.observe(this,meals -> {
            plannedMealsAdapter = new PlannedMealsAdapter(getActivity(),meals,plannedMealsPresenterContract);
            recyclerView.setAdapter(plannedMealsAdapter);
        });
    }
}